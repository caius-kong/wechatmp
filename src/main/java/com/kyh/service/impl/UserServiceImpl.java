package com.kyh.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kyh.config.ClientInitializer;
import com.kyh.entity.User;
import com.kyh.service.TokenService;
import com.kyh.service.UserService;
import com.kyh.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;

/**
 * Created by kongyunhui on 2017/7/25.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private ClientInitializer initializer;

    @Autowired
    private TokenService tokenService;

    /**
     * 通过 openid 获取用户微信信息
     */
    @Override
    public User getUserInfo(String openid) throws Exception {
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("access_token", tokenService.getToken());
        params.put("openid", openid);
        params.put("lang", "zh_CN");
        String subscribers = HttpUtil.sendGet(initializer.getUrlProperties().getUserInfoUrl(), params);

        return JSON.parseObject(subscribers, User.class);
    }
}
