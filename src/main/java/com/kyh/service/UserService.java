package com.kyh.service;

import com.kyh.entity.User;
/**
 * Created by kongyunhui on 2017/7/25.
 */
public interface UserService {
    User getUserInfo(String openid) throws Exception;
}
