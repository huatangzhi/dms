package com.hikvision.dms.model;

import org.springframework.stereotype.Component;

@Component
public class HostHolder {

    private static ThreadLocal<User> users = new ThreadLocal<User>();

    private static ThreadLocal<Admin> admins = new ThreadLocal<>();

    public Admin getAdmin(){
        return admins.get();
    }

    public void setAdmin(Admin admin){
        admins.set(admin);
    }

    public void adminClear(){
        admins.remove();
    }

    public User getUser(){
        return users.get();
    }

    public void setUser(User user){
        users.set(user);
    }

    public void userClear(){
        users.remove();
    }
}
