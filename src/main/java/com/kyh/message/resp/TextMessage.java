package com.kyh.message.resp;

/**
 * 文本消息（普通消息）
 */
public class TextMessage extends BaseMessage {
    // 回复的消息内容   
    private String Content;

    public TextMessage(){}

    public TextMessage(String toUserName, String fromUserName, long createTime, String msgType, String content){
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.setMsgType(msgType);
        this.setContent(content);
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String Content) {
        this.Content = Content;
    }
}