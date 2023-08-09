package com.target.target_manager_api.security;

import com.alibaba.fastjson.JSON;

import com.target.target_common.result.BaseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MyLogoutSuccessHandler implements LogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("text/json;charset=utf-8");
        BaseResult result = new BaseResult(200, "Log out success", null);
        response.getWriter().write(JSON.toJSONString(result));
    }
}
