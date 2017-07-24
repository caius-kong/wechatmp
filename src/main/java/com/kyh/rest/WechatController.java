package com.kyh.rest;

import com.kyh.config.ClientInitializer;
import com.kyh.dispatcher.EventDispatcher;
import com.kyh.dispatcher.MsgDispatcher;
import com.kyh.utils.MessageUtil;
import com.kyh.utils.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by kongyunhui on 2017/7/20.
 */
@RestController
@RequestMapping("/wechat")
public class WechatController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientInitializer initializer;

    @RequestMapping("")
    public String index(){
        return "wechatmp";
    }

    @RequestMapping(value = "security", method = RequestMethod.GET)
    public String doGet(
            @RequestParam(name = "signature", required = false) String signature,
            @RequestParam(name = "timestamp", required = false) String timestamp,
            @RequestParam(name = "nonce", required = false) String nonce,
            @RequestParam(name = "echostr", required = false) String echostr) {

        logger.info("接收到来自微信服务器的认证消息：[{}, {}, {}, {}]", signature, timestamp, nonce, echostr);

        if (StringUtils.isAnyBlank(signature, timestamp, nonce, echostr)) {
            throw new IllegalArgumentException("请求参数非法，请核实!");
        }

        if (SignUtil.checkSignature(initializer.getProperties().getToken(), timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @RequestMapping(value = "security", method = RequestMethod.POST)
    public String doPost(HttpServletRequest request) {
        System.out.println("这是 post 方法！");
        String respXML = "success";
        try {
            Map<String, String> map=MessageUtil.parseXml(request);
            System.out.println("-requestBody-->" + map);
            String msgType=map.get("MsgType");
            if(MessageUtil.REQ_MESSAGE_TYPE_EVENT.equals(msgType)){
                respXML = EventDispatcher.processEvent(map);//进入事件处理
            }else{
                respXML = MsgDispatcher.processMessage(map);//进入消息处理
            }
        }catch(Exception e){
            logger.error("error:{}", e.getMessage());
        }
        System.out.println("-requestBody-->" + respXML);
        return respXML;
    }
}
