package com.target.target_manager_api.security;

import com.alibaba.fastjson.JSON;
import com.target.target_common.result.BaseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyEntryPointHandler implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setContentType("text/json;charset=utf-8");
        BaseResult res = new BaseResult(401, "user not sign in", null);
        response.getWriter().write(JSON.toJSONString(res));
    }
}

