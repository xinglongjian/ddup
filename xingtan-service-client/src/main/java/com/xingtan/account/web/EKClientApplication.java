package com.xingtan.account.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EKClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(EKClientApplication.class, args);
    }
}
