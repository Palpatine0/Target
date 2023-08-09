package com.target.target_manager_api.security;

import com.alibaba.fastjson.JSON;
import com.target.target_common.result.BaseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyLoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("text/json;charset=utf-8");
        BaseResult res = new BaseResult(402, "Incorrect username or pwd", null);
        response.getWriter().write(JSON.toJSONString(res));
    }
}

