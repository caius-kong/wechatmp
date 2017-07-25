package com.kyh.dispatcher;

import com.google.common.collect.Lists;
import com.kyh.message.resp.Article;
import com.kyh.message.resp.NewsMessage;
import com.kyh.message.resp.TextMessage;
import com.kyh.utils.MessageUtil;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * 事件推送处理分发器
 */
@Component
public class EventDispatcher {
    public String processEvent(Map<String, String> map) throws Exception{
        String openId = map.get("FromUserName"); // 用户openId
        String mpId = map.get("ToUserName"); // 公众号原始 ID

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { //关注事件
            System.out.println("==============这是关注事件！");

            TextMessage textMessage = new TextMessage(openId, mpId, new Date().getTime(), MessageUtil.RESP_MESSAGE_TYPE_TEXT,
                    "回复'1'-获取文本消息；回复'2'-获取图文消息；回复'3'-获取图片消息");
            return MessageUtil.toXml(textMessage, TextMessage.class);
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { //取消关注事件
            System.out.println("==============这是取消关注事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_SCAN)) { //扫描二维码事件
            System.out.println("==============这是扫描二维码事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_LOCATION)) { //位置上报事件
            System.out.println("==============这是位置上报事件！");
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_CLICK)) { //自定义菜单点击事件
            System.out.println("==============这是自定义菜单点击事件！");

            String eventKey = map.get("EventKey");
            switch (eventKey) {
                case "history":
                    Article article1 = new Article("微信公众号开发源码(java版)", "基于springboot的微信公众号开发项目，可以将该项目作为开发脚手架！", "http://443e958d.ngrok.io/images/logo.jpg", "https://github.com/kongyunhui/wechatmp");
                    ArrayList<Article> articles = Lists.newArrayList(article1);

                    NewsMessage newsMessage = new NewsMessage(openId, mpId, new Date().getTime(), MessageUtil.RESP_MESSAGE_TYPE_NEWS, articles);
                    return MessageUtil.toXml(newsMessage, NewsMessage.class, Article.class);
                case "support":
                    TextMessage textMessage = new TextMessage(openId, mpId, new Date().getTime(),
                            MessageUtil.RESP_MESSAGE_TYPE_TEXT,
                            "谢谢您的支持，给我留言吧！1032316751@qq.com");
                    return MessageUtil.toXml(textMessage, TextMessage.class);
            }
        }

        if (map.get("Event").equals(MessageUtil.EVENT_TYPE_VIEW)) { //自定义菜单VIEW事件
            System.out.println("==============这是自定义菜单VIEW事件！");
        }
        return null;
    }
}