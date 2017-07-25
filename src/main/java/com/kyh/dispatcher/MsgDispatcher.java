package com.kyh.dispatcher;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.kyh.config.ClientInitializer;
import com.kyh.entity.User;
import com.kyh.message.resp.*;
import com.kyh.service.TokenService;
import com.kyh.service.UserService;
import com.kyh.utils.HttpPostUploadUtil;
import com.kyh.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 普通消息处理分发器
 */
@Component
public class MsgDispatcher {

    @Autowired
    private ClientInitializer initializer;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserService userService;

    public String processMessage(Map<String, String> map) throws Exception{
        String openId = map.get("FromUserName"); // 用户openId
        String mpId = map.get("ToUserName"); // 公众号原始 ID


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            System.out.println("==============这是文本消息！");

            String content = map.get("Content");
            switch (content) {
                case "1":
                    // 回复文本消息
                    TextMessage textMessage = new TextMessage(openId, mpId, new Date().getTime(),
                            MessageUtil.RESP_MESSAGE_TYPE_TEXT,
                            "这里是孔昀晖的公众号！");
                    return MessageUtil.toXml(textMessage, TextMessage.class);
                case "2":
                    // 回复图文消息
                    Article article1 = new Article("微信公众号开发源码(java版)", "基于springboot的微信公众号开发项目，可以将该项目作为开发脚手架！", "http://443e958d.ngrok.io/images/logo.jpg", "https://github.com/kongyunhui/wechatmp");
                    ArrayList<Article> articles = Lists.newArrayList(article1);

                    NewsMessage newsMessage = new NewsMessage(openId, mpId, new Date().getTime(), MessageUtil.RESP_MESSAGE_TYPE_NEWS, articles);
                    return MessageUtil.toXml(newsMessage, NewsMessage.class, Article.class);
                case "3":
                    // 回复图片消息
                    String urlStr = initializer.getUrlProperties().getMediaUrl() + tokenService.getToken() + "&type=image";

                    Map<String, String> textMap = new HashMap<>();
                    textMap.put("name", "testname");

                    Map<String, String> fileMap = new HashMap<>();
                    fileMap.put("userfile", "/Users/kongyunhui/Desktop/car.jpg");

                    String mediaIdRs = HttpPostUploadUtil.formUpload(urlStr, textMap, fileMap);

                    String mediaId = JSONObject.parseObject(mediaIdRs, JSONObject.class).getString("media_id");
                    Image image = new Image(mediaId);
                    ImageMessage imageMessage = new ImageMessage(openId, mpId, new Date().getTime(), MessageUtil.RESP_MESSAGE_TYPE_IMAGE, image);
                    return MessageUtil.toXml(imageMessage, ImageMessage.class);
                case "4":
                    // 显示用户信息
                    User userInfo = userService.getUserInfo(openId);
                    System.out.println("userInfo:" + userInfo);
                default:
                    return "";
            }
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息
            System.out.println("==============这是链接消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 位置消息
            System.out.println("==============这是位置消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息
            System.out.println("==============这是视频消息！");
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
            System.out.println("==============这是语音消息！");
        }

        return null;
    }
}