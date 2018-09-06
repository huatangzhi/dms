package com.hikvision.dms.controller;


import io.swagger.annotations.ApiOperation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {


    @ApiOperation(value="用户登录首页")
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(){
        return "login";
    }


}
