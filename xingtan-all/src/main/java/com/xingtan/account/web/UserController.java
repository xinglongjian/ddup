package com.xingtan.account.web;

import com.xingtan.account.bean.WeixinUser;
import com.xingtan.account.entity.User;
import com.xingtan.account.service.UserService;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userName}")
    @ApiOperation(value = "通过用户名获取学生", notes = "通过用户名获取学生", httpMethod = "GET")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getUserByUserName(@PathVariable("userName") String userName) {
        if(StringUtils.isEmpty(userName)) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "userName is not exist", null);
        }
        User user = null;
        try {
            user = userService.getUserByUserName(userName);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        if(user == null) {
            return new BaseResponse<User>(HttpStatus.OK, "no data", user);
        }
        return new BaseResponse<User>(HttpStatus.OK, user);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "添加用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addUser(@RequestBody User user) {
        if (user.getEmail() == null && user.getTelephone() == null && user.getUserName() == null) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "用户名、手机号、邮箱至少有一个",
                    null);
        }
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<User>(HttpStatus.OK, user);
    }

    @PostMapping("/addByWeixin")
    @ApiOperation(value = "添加用户", notes = "添加用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addUserByWeixin(@RequestBody WeixinUser user) {
//        if (user.getEmail() == null && user.getTelephone() == null && user.getUserName() == null) {
//            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "用户名、手机号、邮箱至少有一个",
//                    null);
//        }
//        try {
//            userService.insertUser(user);
//        } catch (Exception e) {
//            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
//        }
        return new BaseResponse<WeixinUser>(HttpStatus.OK, user);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse deleteUser(@PathVariable("id") Long id) {
        if (id == null) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "ID is empty",
                    null);
        }
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, "SUCCESS", id);
    }

}
