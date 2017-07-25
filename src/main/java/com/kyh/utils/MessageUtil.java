package com.kyh.utils;

import com.kyh.message.resp.BaseMessage;
import com.kyh.message.resp.NewsMessage;
import com.kyh.message.resp.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    /**
     * 返回消息类型：文本
     */
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型：图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 返回消息类型：音乐
     */
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 请求消息类型：文本
     */
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";

    /**
     * 请求消息类型：图片
     */
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型：链接
     */
    public static final String REQ_MESSAGE_TYPE_LINK = "link";

    /**
     * 请求消息类型：地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /**
     * 请求消息类型：视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * 请求消息类型：音频
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型：推送
     */
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * 事件类型：subscribe(订阅)
     */
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

    /**
     * 事件类型：unsubscribe(取消订阅)
     */
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型：scan(扫码)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";

    /**
     * 事件类型：scan(位置上报)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 事件类型：scan(自定义菜单点击跳转链接事件)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /**
     * 事件类型：CLICK(自定义菜单点击事件)
     */
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 解析微信发来的请求（XML）
     */
    @SuppressWarnings("unchecked")
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在 HashMap 中   
        Map<String, String> map = new HashMap<>();

        // 从 request 中取得输入流   
        InputStream inputStream = request.getInputStream();
        // 读取输入流   
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到 xml 根元素   
        Element root = document.getRootElement();
        // 得到根元素的所有子节点   
        List<Element> elementList = root.elements();

        // 遍历所有子节点   
        for (Element e : elementList)
            map.put(e.getName(), e.getText());

        // 释放资源   
        inputStream.close();
        inputStream = null;

        return map;
    }

    /**
     * 回复消息 转换成 xml
     *
     * @param message 回复消息实体对象
     * @param messageClazz 回复消息对象类型
     * @param subNodeClazz 回复消息对象的子属性(list)的元素类型。例如newsMessage的articles的元素类型Article
     * @return
     */
    public static String toXml(Object message, Class messageClazz, Class subNodeClazz) {
        xStream.alias("xml", messageClazz);
        if(subNodeClazz != null) xStream.alias("item", subNodeClazz);
        return xStream.toXML(message);
    }

    public static String toXml(Object message, Class messageClazz) {
        xStream.alias("xml", messageClazz);
        return xStream.toXML(message);
    }

    /**
     * 对象到 xml 的处理
     */
    public static XStream xStream = new XStream(new XppDriver(){
        @Override
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out){
                boolean cdata = false;

                @Override
                public void startNode(String name, Class clazz) {
                    cdata = "CreateTime".equals(name)? false:true; // CreateTime字段不需要加<![CDATA[xx]]
                    super.startNode(name, clazz);
                }

                @Override
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    } else {
                        writer.write(text);
                    }
                }
            };
        }
    });
}