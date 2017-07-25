package com.kyh.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * wechat mp properties 接口调用请求地址
 */
@ConfigurationProperties(prefix = "wechat.mp.url")
public class WechatMpUrlProperties {
    /**
     * 获取 token 的url
     */
    private String tokenUrl;

    /**
     * 上传 多媒体文件 的url
     */
    private String mediaUrl;

    /**
     * 获取 用户信息 的url
     */
    private String userInfoUrl;

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

    public String getUserInfoUrl() {
        return userInfoUrl;
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }
}
