package com.xingtan.choose;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * Course
 */
@SpringBootApplication
@EnableEurekaClient
public class ChooseApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChooseApplication.class, args);
    }
}
