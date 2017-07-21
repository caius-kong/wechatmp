package com.kyh.rest;

import com.kyh.config.WechatMpProperties;
import com.kyh.utils.SignUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.*;

/**
 * Created by kongyunhui on 2017/7/20.
 */
@RestController
@RequestMapping("/wechat")
@EnableConfigurationProperties(WechatMpProperties.class)
public class WechatController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private WechatMpProperties properties;

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

        if (SignUtil.checkSignature(properties.getToken(), timestamp, nonce, signature)) {
            return echostr;
        }

        return "非法请求";
    }

    @RequestMapping(value = "security", method = RequestMethod.POST)
    public void doPost() {
        System.out.println("这是 post 方法！");
    }
}
