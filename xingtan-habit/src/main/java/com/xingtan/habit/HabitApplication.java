package com.xingtan.habit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class HabitApplication {

    public static void main(String[] args) {
        SpringApplication.run(HabitApplication.class, args);
    }
}
