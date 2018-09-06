package com.hikvision.dms.configuration;

import com.hikvision.dms.interceptor.AdminLoginRequiredInterceptor;
import com.hikvision.dms.interceptor.UserLoginRequiredInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Component
public class DMSConfiguration  extends WebMvcConfigurerAdapter {

    @Autowired
    AdminLoginRequiredInterceptor adminLoginRequiredInterceptor;

    @Autowired
    UserLoginRequiredInterceptor userLoginRequiredInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(adminLoginRequiredInterceptor).addPathPatterns("/admin/addUser","/admin/delUser", "/admin/updateUser", "/admin/viewUsers");
        registry.addInterceptor(userLoginRequiredInterceptor).addPathPatterns("/user/addDevice","/user/delDevice", "/user/viewDevices","/user/updateDevice");
        super.addInterceptors(registry);
    }
}
