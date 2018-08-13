package com.hikvision.dms.interceptor;

import com.hikvision.dms.mapper.AdminLoginTicketMapper;
import com.hikvision.dms.mapper.AdminMapper;
import com.hikvision.dms.mapper.LoginTicketMapper;
import com.hikvision.dms.mapper.UserMapper;
import com.hikvision.dms.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class UserLoginRequiredInterceptor implements HandlerInterceptor {


    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String ticket = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("userTicket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket != null) {
            LoginTicket loginTicket = loginTicketMapper.selectByTicket(ticket);

            if (loginTicket == null || loginTicket.getExpired().before(new Date()) || loginTicket.getStatus() != 0) {
                httpServletResponse.sendRedirect("/reglogin?" + httpServletRequest.getRequestURI());
                return false;
            }
            User user = userMapper.selectById(loginTicket.getUserId());
            hostHolder.setUser(user);
            return true;
        }
        httpServletResponse.sendRedirect("/reglogin?" + httpServletRequest.getRequestURI());
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
