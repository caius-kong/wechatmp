package com.kyh.service;

import com.alibaba.fastjson.JSONObject;
import com.kyh.config.ClientInitializer;
import com.kyh.utils.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kongyunhui on 2017/7/24.
 */
@Service
public class TokenService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientInitializer initializer;

    @Cacheable(value = "tokencache",keyGenerator = "keyGenerator")
    public String getToken(){
        logger.debug("无缓存，调用获取token的接口...");
        String access_token = null;
        try {
            Map<String, String> params = new HashMap<>();
            params.put("grant_type", "client_credential");
            params.put("appid", initializer.getProperties().getAppId());
            params.put("secret", initializer.getProperties().getSecret());
            String jsonString = HttpUtil.sendGet(initializer.getUrlProperties().getTokenUrl(), params);
            access_token = JSONObject.parseObject(jsonString, JSONObject.class).getString("access_token");
        }catch(Exception e){
            logger.error("error:{}", e.getMessage());
        }
        return access_token;
    }
}
