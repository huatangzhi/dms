package com.hikvision.dms.service;

import com.hikvision.dms.mapper.DeviceMapper;
import com.hikvision.dms.mapper.LoginTicketMapper;
import com.hikvision.dms.mapper.UserDeviceMapper;
import com.hikvision.dms.mapper.UserMapper;
import com.hikvision.dms.model.LoginTicket;
import com.hikvision.dms.model.User;
import com.hikvision.dms.model.UserDeviceRecord;
import com.hikvision.dms.util.SHA256Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.lang3.StringUtils;

import javax.jws.soap.SOAPBinding;
import javax.xml.crypto.Data;
import java.util.*;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private LoginTicketMapper loginTicketMapper;

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private UserDeviceMapper userDeviceMapper;

    public User selectByName(String name) {
        return userMapper.selectByName(name);
    }

    public Map<String, Object> addUser(String userName, String password) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (StringUtils.isBlank(userName)) {
            resultMap.put("msg", "用户名不能为空");
            return resultMap;
        }

        if (StringUtils.isBlank(password)) {
            resultMap.put("msg", "用户密码不能为空");
            return resultMap;
        }

        User user = userMapper.selectByName(userName);

        if (user != null) {
            resultMap.put("msg", "用户名已经存在");
            return resultMap;
        }

        user = new User();
        user.setName(userName);
        user.setSalt(UUID.randomUUID().toString().substring(0, 5));
        user.setPassword(SHA256Util.SHA256(password + user.getSalt()));
        userMapper.addUser(user);

        resultMap.put("msg", "用户添加成功");
        return resultMap;
    }



    public Map<String, Object> deleteUser(String userName) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (StringUtils.isBlank(userName)) {
            resultMap.put("msg", "用户名不能为空");
            return resultMap;
        }

        User user = userMapper.selectByName(userName);
        if (user != null) {
            List<Integer> deviceIds = userDeviceMapper.getUserDevicesById(user.getId());
            if (deviceIds.size() == 1) {
                deviceService.deleteById(deviceIds.get(0));
                userDeviceMapper.deleteDeviceByUserId(userMapper.selectByName(userName).getId());
            } else if (deviceIds.size() > 1) {
                deviceService.deleteById(deviceIds);
                userDeviceMapper.deleteDeviceByUserId(userMapper.selectByName(userName).getId());
            }
            if (loginTicketMapper.selectByUserId(user.getId()) != null) {
                loginTicketMapper.delTicket(user.getId());
            }
            userMapper.deleteByName(userName);
            resultMap.put("msg", "用户删除成功");
        } else {
            resultMap.put("msg", "没有该用户");
        }
        return resultMap;
    }

    public Map<String, Object> updateUserPassword(String userName, String password) {
        Map<String, Object> resultMap = new HashMap<String, Object>();

        if (StringUtils.isBlank(userName)) {
            resultMap.put("msg", "用户名不能为空");
            return resultMap;
        }

        if (StringUtils.isBlank(password)) {
            resultMap.put("msg", "用户密码不能为空");
            return resultMap;
        }

        if (selectByName(userName) != null) {
            User user = selectByName(userName);
            user.setSalt(UUID.randomUUID().toString().substring(0, 5));
            user.setPassword(SHA256Util.SHA256(password + user.getSalt()));
            userMapper.updatePassword(user);
            resultMap.put("msg", "更新密码成功");
        } else {
            resultMap.put("msg", "查无此用户");
        }
        return resultMap;
    }

    public List<User> getAllUsers(){
        List<User> users = new ArrayList<>();

        users = userMapper.selectAllUser();

        if (users != null) {
            return users;
        }
        return null;
    }

    public Map<String, Object> login(String userName, String password) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (StringUtils.isBlank(userName)) {
            map.put("msg", "用户名不能为空");
            return map;
        }

        if (StringUtils.isBlank(password)) {
            map.put("msg", "密码不能为空");
            return map;
        }

        User user = userMapper.selectByName(userName);

        if (user == null) {
            map.put("msg", "用户名或密码不正确");
            return map;
        }

        if (!SHA256Util.SHA256(password+user.getSalt()).equals(user.getPassword())) {
            map.put("msg", "用户名或密码不正确");
            return map;
        }

        String ticket1 = getLoginTicket(user.getId());

        if (ticket1 != null) {
            loginTicketMapper.delTicket(user.getId());
        }
//        if (getLoginTicket(user.getId()) != null){
//
//        }
        String ticket = addLoginTicket(user.getId());
        map.put("userTicket", ticket);
        map.put("userId", user.getId());
        return map;
    }

    private String addLoginTicket(int userId) {
        LoginTicket ticket = new LoginTicket();
        ticket.setUserId(userId);
        Date date = new Date();
        date.setTime(date.getTime() + 1000 * 3600 * 24);
        ticket.setExpired(date);
        ticket.setStatus(0);
        ticket.setTicket(UUID.randomUUID().toString().replaceAll("-", ""));
        loginTicketMapper.addTicket(ticket);
        return ticket.getTicket();
    }

    private String getLoginTicket(int userId) {
        LoginTicket ticket = loginTicketMapper.selectByUserId(userId);
        if (ticket != null) {
            return ticket.getTicket();
        } else{
            return null;
        }
    }

    public void logout(String ticket) {
        loginTicketMapper.updateStatus(ticket, 1);
    }
}
