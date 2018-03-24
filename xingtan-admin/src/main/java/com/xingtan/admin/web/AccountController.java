package com.xingtan.admin.web;

import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableEurekaClient
@EnableFeignClients
public class AccountController {

}
