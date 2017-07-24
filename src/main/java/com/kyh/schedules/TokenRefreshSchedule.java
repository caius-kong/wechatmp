package com.kyh.schedules;

import com.kyh.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Created by kongyunhui on 2017/7/24.
 *
 * 中控服务器：token定时刷新
 */
@Component
public class TokenRefreshSchedule {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public final static long ONE_Minute =  60 * 1000;

    @Autowired
    private TokenService tokenService;

    @Scheduled(fixedDelay = 120*ONE_Minute)
    public void getTokenTask(){
        String access_token = tokenService.getToken();
        logger.debug("access_token:{} / {}", access_token, LocalDate.now() + " " + LocalTime.now());
    }
}
