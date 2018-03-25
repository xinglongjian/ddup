package com.xingtan.account.web;

import com.xingtan.account.bean.LoginError;
import com.xingtan.account.bean.LoginType;
import com.xingtan.account.entity.User;
import com.xingtan.account.service.UserService;
import com.xingtan.common.entity.OperationStatus;
import com.xingtan.common.utils.MD5Utils;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录
 */
@RestController
@Slf4j
@RequestMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @ApiOperation(value = "通过用户名登陆", notes = "通过用户名登陆", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "登录类型", required = true, dataType = "LoginType", paramType = "query")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse<User> by(String userName, String password, LoginType type) {
        if (StringUtils.isEmpty(userName)) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "userName is not empty", null);
        }
        try {
            User user = queryUser(userName, type);
            if (null == user) {
                return new BaseResponse<User>(HttpStatus.OK, LoginError.USERNAME_ERROR.name(), null);
            }
            if (MD5Utils.md5(password).equals(user.getPassword())) {
                return new BaseResponse<User>(HttpStatus.OK, OperationStatus.SUCCESS.name(), user);
            } else {
                return new BaseResponse<User>(HttpStatus.OK, LoginError.PASSWORD_ERROR.name(), null);
            }
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    private User queryUser(String username, LoginType type) {
        switch (type) {
            case USERNAME:
                return userService.getUserByUserName(username);
            case EMAIL:
                return userService.getUserByEmail(username);
            case PHONE:
                return userService.getUserByPhone(username);
            default:
                return null;

        }
    }
}
