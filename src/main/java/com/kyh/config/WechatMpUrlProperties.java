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

    public void setTokenUrl(String tokenUrl) {
        this.tokenUrl = tokenUrl;
    }

    public String getTokenUrl() {
        return tokenUrl;
    }
}
