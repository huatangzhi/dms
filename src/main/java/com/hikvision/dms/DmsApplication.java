package com.hikvision.dms;import org.springframework.boot.SpringApplication;import org.springframework.boot.autoconfigure.SpringBootApplication;import org.springframework.transaction.annotation.EnableTransactionManagement;@SpringBootApplication@EnableTransactionManagementpublic class DmsApplication {    public static void main(String[] args) {        SpringApplication.run(DmsApplication.class, args);    }}