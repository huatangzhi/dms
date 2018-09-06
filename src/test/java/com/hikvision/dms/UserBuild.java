package com.hikvision.dms;

import java.util.UUID;

public class UserBuild {

    private int id;

    private String name;

    private String password;

    private String salt;

    static class UserBuilder{

        int id = -1;
        String name = "root";
        String password = "123456";
        String salt = "hikvision";

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setName(String name){
            this.name = name;
            return this;
        }

        public UserBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder setSalt(String salt) {
            this.salt = salt;
            return this;
        }

        public UserBuild build(){
            return new UserBuild(this);
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public UserBuild(UserBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.password = builder.password;
        this.salt = builder.salt;
    }

    @Override
    public String toString() {
        return "UserBuild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                '}';
    }
}
