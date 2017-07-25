package com.kyh.message.resp;

/**
 * 图片消息（多媒体消息）
 */
public class ImageMessage extends BaseMessage{

    private Image Image;

    public ImageMessage(String toUserName, String fromUserName, long createTime, String msgType, Image image) {
        this.setToUserName(toUserName);
        this.setFromUserName(fromUserName);
        this.setCreateTime(createTime);
        this.setMsgType(msgType);
        this.Image = image;
    }
    public Image getImage() {
        return Image;
    }

    public void setImage(Image image) {
        Image = image;
    }


}