package com.xingtan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class XingtanApplication {
    public static void main(String[] args) {
        String originName = "3232.erqr.qwer.JGP";
        String suffix = originName.substring(originName.lastIndexOf(".")+1);
        System.out.printf(suffix);
        SpringApplication.run(XingtanApplication.class, args);
    }
}
