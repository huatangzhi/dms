package com.hikvision.dms.controller;


import com.hikvision.dms.model.Admin;
import com.hikvision.dms.model.Device;
import com.hikvision.dms.model.HostHolder;
import com.hikvision.dms.model.User;
import com.hikvision.dms.service.AdminService;
import com.hikvision.dms.service.DeviceService;
import com.hikvision.dms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Api(value="/user", tags="用户管理设备界面")
@RequestMapping(value = "/user")
@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    HostHolder hostHolder;

    @ApiOperation(value="用户登录", notes = "用户登录界面")
    @RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String userLogin(Model model, @RequestBody User user, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = userService.login(user.getName(), user.getPassword());
            if (resultMap.containsKey("userTicket")) {
                Cookie cookie = new Cookie("userTicket", resultMap.get("userTicket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "redirect:/DevicesDetail";
            } else {
                model.addAttribute("msg", resultMap.get("msg"));
                return "/userLogin";
            }
        } catch (Exception e) {
            logger.error("管理员用户登录异常" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
            return "/userLogin";
        }
    }

    @ApiOperation(value="用户添加设备")
    @RequestMapping(value = "/addDevice", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> addDevice(@RequestBody Device device) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (hostHolder.getUser() != null) {
                resultMap = deviceService.addDevice(device.getName(), device.getIndexCode(),device.getResourceType(), hostHolder.getUser().getId());
            } else {
                resultMap.put("userMsg", "当前用户无权限");
            }
        } catch (Exception e) {
            logger.error("添加管理员用户异常" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
        }
        return resultMap;
    }

    @ApiOperation(value="删除设备",notes = "用户删除设备")
    @RequestMapping(value = "/delDevice", method = RequestMethod.POST)
    public Map<String, Object> delUser(@RequestParam String deviceName) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {
            if (hostHolder.getUser() != null) {
                resultMap = deviceService.deleteByName(deviceName, hostHolder.getUser().getId());
            } else {
                resultMap.put("userMsg", "当前的用户无权限");
            }
        } catch (Exception e) {
            logger.error("删除用户异常" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
        }
        return resultMap;
    }
//
//    @ApiOperation(value = "修改用户密码", notes = "管理员修改用户密码")
//    @RequestMapping(value = "/updateUserPassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Map<String, Object> updateUserPassword(@RequestBody User user) {
//        Map<String, Object> resultMap = new HashMap<>();
//        try {
//            resultMap = userService.updateUserPassword(user.getName(), user.getPassword());
//        } catch (Exception e) {
//            logger.error("更新密码失败" + e.getMessage());
//            resultMap.put("errorMsg", "服务器错误");
//        }
//        return resultMap;
//    }
//


}
