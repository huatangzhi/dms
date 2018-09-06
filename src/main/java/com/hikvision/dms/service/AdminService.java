package com.hikvision.dms.service;

import com.hikvision.dms.mapper.AdminLoginTicketMapper;
import com.hikvision.dms.mapper.AdminMapper;
import com.hikvision.dms.model.Admin;
import com.hikvision.dms.model.AdminLoginTicket;
import com.hikvision.dms.util.SHA256Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminLoginTicketMapper adminLoginTicketMapper;

    public Map<String, Object> addAdmin(String userName, String password) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (StringUtils.isBlank(userName)) {
            resultMap.put("msg", "管理员用户名不能为空");
            return resultMap;
        }

        if (StringUtils.isBlank(password)) {
            resultMap.put("msg", "管理员用户密码不能为空");
            return resultMap;
        }

        Admin admin = adminMapper.selectByName(userName);

        if (admin != null) {
            resultMap.put("msg", "管理员用户名已经存在");
            return resultMap;
        }

        admin = new Admin();
        admin.setName(userName);
        admin.setSalt(UUID.randomUUID().toString().substring(0, 5));
        admin.setPassword(SHA256Util.SHA256(password + admin.getSalt()));
        adminMapper.addAdmin(admin);

        resultMap.put("msg", "管理员用户添加成功");
        return resultMap;
    }

    public Map<String, Object> login(String userName, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(userName)) {
            map.put("msg", "管理员用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "管理员密码不能为空");
            return map;
        }

        Admin admin = adminMapper.selectByName(userName);

        if (admin == null || !SHA256Util.SHA256(password + admin.getSalt()).equals(admin.getPassword())) {
            map.put("msg", "管理员用户名或密码不存在");
            return map;
        }

        if (getLoginTicket(admin.getId()) != null) {
            adminLoginTicketMapper.delTicket(admin.getId());
        }
        String ticket = addLoginTicket(admin.getId());

        map.put("ticket", ticket);
        map.put("userId", admin.getId());
        map.put("msg", "管理员登录成功");
        return map;
    }

    public Admin getAdminByName(String userName) {
        if (adminMapper.selectByName(userName) != null) {
            return adminMapper.selectByName(userName);
        } else {
            return null;
        }

    }

    private String addLoginTicket(int userId) {
        AdminLoginTicket ticket = new AdminLoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000*3600*24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        adminLoginTicketMapper.addTicket(ticket);
        return ticket.getTicket();
    }

    private String getLoginTicket(int userId) {
        AdminLoginTicket ticket = adminLoginTicketMapper.selectByUserId(userId);
        if (ticket != null) {
            return ticket.getTicket();
        } else{
            return null;
        }
    }

    public void logout(String ticket) {
        adminLoginTicketMapper.updateStatus(ticket, 1);
    }

}
