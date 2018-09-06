package com.hikvision.dms.controller;


import com.hikvision.dms.model.Admin;
import com.hikvision.dms.model.HostHolder;
import com.hikvision.dms.model.User;
import com.hikvision.dms.service.AdminService;
import com.hikvision.dms.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value="/admin", tags="用户管理界面")
@RequestMapping(value = "/admin")
@Controller
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    UserService userService;

    @Autowired
    AdminService adminService;

    @Autowired
    HostHolder hostHolder;

    @ApiOperation(value="管理员注册", notes = "管理员注册")
    @RequestMapping(value = "/addAdmin", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> addAdmin(@RequestBody Admin admin) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = adminService.addAdmin(admin.getName(), admin.getPassword());
        } catch (Exception e) {
            logger.error("添加管理员用户异常" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
        }
        return resultMap;
    }

    @ApiOperation(value="管理员登录", notes = "管理员登录界面")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String adminLogin(Model model, @RequestParam("username") String username,
                             @RequestParam("password") String password, HttpServletResponse response) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = adminService.login(username, password);
            if (resultMap.containsKey("ticket")) {
                Cookie cookie = new Cookie("ticket", resultMap.get("ticket").toString());
                cookie.setPath("/");
                response.addCookie(cookie);
                return "usersDetail";
            } else {
                model.addAttribute("msg", resultMap.get("msg"));
                return "login";
            }
        } catch (Exception e) {
            logger.error("管理员用户登录异常" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
            return "/login";
        }
    }

    @ApiOperation(value="添加用户", notes = "管理员添加新用户")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> addUser(@RequestBody User user) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
             resultMap = userService.addUser(user.getName(), user.getPassword());
        } catch (Exception e) {
            logger.error("添加用户异常" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
        }
        return resultMap;
    }

    @ApiOperation(value="删除用户",notes = "管理员删除用户")
    @RequestMapping(value = "/delUser", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delUser(@RequestParam String name) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            resultMap = userService.deleteUser(name);
        } catch (Exception e) {
            logger.error("删除用户异常" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
        }
        return resultMap;
    }

    @ApiOperation(value = "修改用户信息", notes = "管理员修改用户信息")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, Object> updateUser(@RequestBody User user) {
        Map<String, Object> resultMap = new HashMap<>();
        try {
            if (hostHolder.getAdmin() != null) {
                userService.deleteUser(user.getName());
                resultMap = userService.addUser(user.getName(), user.getPassword());
            }

        } catch (Exception e) {
            logger.error("修改用户信息" + e.getMessage());
            resultMap.put("errorMsg", "服务器错误");
        }
        return resultMap;
    }

    @ApiOperation(value = "查看所有用户", notes = "管理员查看所有用户")
    @RequestMapping(value = "/viewUsers", method = RequestMethod.GET)
    @ResponseBody
    public List<User> viewAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            if (hostHolder.getAdmin() != null) {
                users = userService.getAllUsers();
            }
        } catch (Exception e) {
            logger.error("查看所有用户" + e.getMessage());
        }
        return users;
    }


    @ApiOperation(value = "管理员登出")
    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(@CookieValue("ticket") String ticket){
        adminService.logout(ticket);
        return "redirect:/";
    }




}
