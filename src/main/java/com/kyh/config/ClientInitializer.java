package com.kyh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by kongyunhui on 2017/7/24.
 */
@Configuration
@EnableConfigurationProperties({WechatMpProperties.class, WechatMpUrlProperties.class})
public class ClientInitializer {
    @Autowired
    private WechatMpProperties properties;

    @Autowired
    private WechatMpUrlProperties urlProperties;

    public WechatMpProperties getProperties() {
        return properties;
    }

    public WechatMpUrlProperties getUrlProperties() {
        return urlProperties;
    }
}
