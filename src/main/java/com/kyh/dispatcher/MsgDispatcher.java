package com.kyh.dispatcher;

import com.google.common.collect.Lists;
import com.kyh.message.resp.NewsMessage;
import com.kyh.message.resp.TextMessage;
import com.kyh.utils.MessageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * 普通消息处理分发器
 */
public class MsgDispatcher {
    public static String processMessage(Map<String, String> map) {
        String openId = map.get("FromUserName"); // 用户openId
        String mpId = map.get("ToUserName"); // 公众号原始 ID


        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息
            System.out.println("==============这是文本消息！");
            // 根据业务逻辑封装回复消息实体
            String content = "这里是孔昀晖的个人订阅号！";
            TextMessage textMessage = new TextMessage(openId, mpId, new Date().getTime(),
                    MessageUtil.RESP_MESSAGE_TYPE_TEXT,
                    content);
            // 解析成xml并返回
            return MessageUtil.toXml(textMessage, TextMessage.class, null);
        }

        if (map.get("MsgType").equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息
            System.out.println("==============这是图片消息！");

            NewsMessage.Article article1 = new NewsMessage.Article("微信公众号开发源码(java版)", "基于springboot的微信公众号开发项目，可以将该项目作为开发脚手架！", "http://f8e1b8e4.ngrok.io/images/logo.jpg", "https://github.com/kongyunhui/wechatmp");
            ArrayList<NewsMessage.Article> articles = Lists.newArrayList(article1);
            NewsMessage newsMessage = new NewsMessage(openId, mpId, new Date().getTime(),
                    MessageUtil.RESP_MESSAGE_TYPE_NEWS,
                    articles.size(), articles);

            return MessageUtil.toXml(newsMessage, NewsMessage.class, NewsMessage.Article.class);
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