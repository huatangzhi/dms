package com.hikvision.dms.interceptor;

import com.hikvision.dms.mapper.AdminLoginTicketMapper;
import com.hikvision.dms.mapper.AdminMapper;
import com.hikvision.dms.model.Admin;
import com.hikvision.dms.model.AdminLoginTicket;
import com.hikvision.dms.model.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Component
public class AdminLoginRequiredInterceptor implements HandlerInterceptor {


    @Autowired
    private HostHolder hostHolder;

    @Autowired
    private AdminLoginTicketMapper adminLoginTicketMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {

        String ticket = null;
        if (httpServletRequest.getCookies() != null) {
            for (Cookie cookie : httpServletRequest.getCookies()) {
                if (cookie.getName().equals("ticket")) {
                    ticket = cookie.getValue();
                    break;
                }
            }
        }
        if (ticket != null) {
            AdminLoginTicket adminLoginTicket = adminLoginTicketMapper.selectByTicket(ticket);

            if (adminLoginTicket == null || adminLoginTicket.getExpired().before(new Date()) || adminLoginTicket.getStatus() != 0) {
                httpServletResponse.sendRedirect("/reglogin?" + httpServletRequest.getRequestURI());
                return false;
            }
            Admin admin = adminMapper.selectById(adminLoginTicket.getUserId());
            hostHolder.setAdmin(admin);
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
