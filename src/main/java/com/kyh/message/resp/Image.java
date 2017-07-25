package com.kyh.message.resp;

/**
 * 图片消息体
 */
public class Image {
    private String MediaId;

    public Image() {
    }

    public Image(String mediaId) {
        MediaId = mediaId;
    }

    public String getMediaId() {
        return MediaId;
    }

    public void setMediaId(String mediaId) {
        MediaId = mediaId;
    }
}
