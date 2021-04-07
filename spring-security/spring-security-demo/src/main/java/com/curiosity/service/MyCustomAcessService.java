package com.curiosity.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname MyCustomAcessService
 * @Description
 * @Date 2021/4/7 10:20
 * @Created by curiosity
 */
@Service
@Slf4j
public class MyCustomAcessService {

    public boolean checkIp(HttpServletRequest request, Authentication authentication) {
        log.info(request.getRemoteAddr());
        return true;
    }
}
