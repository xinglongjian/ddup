package com.xingtan.account.web;

import com.xingtan.account.entity.User;
import com.xingtan.account.entity.UserSexEnum;
import com.xingtan.account.service.UserService;
import com.xingtan.web.BaseResponse;
import com.xingtan.web.HttpStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequestMapping(value = "/user", produces = "application/json;charset=UTF-8")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    public String test() {
        User user = new User();
        user.setUserName("xinglongjian");
        user.setRealName("zhengweiliang");
        user.setBirthday(new Date());
        user.setEmail("xxx@wqwe.com");
        user.setPassword("111");
        user.setSex(UserSexEnum.FAMALE);
        user.setTelephone("2323232");
        userService.insertUser(user);
        return "hello";
    }

    @GetMapping("/{userName}")
    @ApiOperation(value = "通过用户名获取用户", notes = "通过用户名获取", httpMethod = "GET")
    @ApiImplicitParam(name = "userName",value = "用户名", required = true, dataType = "String", paramType = "path")
    public BaseResponse getUserByUserName(@PathVariable("userName") String userName) {
        User user= userService.getUserByUserName(userName);
        return new BaseResponse(HttpStatus.OK, user);
    }




}
