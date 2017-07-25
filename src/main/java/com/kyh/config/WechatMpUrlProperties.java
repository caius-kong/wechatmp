package com.kyh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wechat mp properties 接口调用请求地址
 */
@ConfigurationProperties(prefix = "wechat.mp.url")
public class WechatMpUrlProperties {
    /**
     * 获取 token 的 url
     */
    private String tokenUrl;

    /**
     * 上传 多媒体文件 的 url
     */
    private String mediaUrl;

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }

    public void setMediaUrl(String mediaUrl) {
        this.mediaUrl = mediaUrl;
    }
}
