package com.xingtan.account.web;

import com.xingtan.account.entity.Group;
import com.xingtan.account.service.GroupService;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/group", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/{userName}")
    @ApiOperation(value = "通过用户名获取集团", notes = "通过用户名获取集团", httpMethod = "GET")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getGroupByUserName(@PathVariable("userName") String userName) {
        Group group = null;
        try {
            group = groupService.getGroupByUserName(userName);
        } catch (Exception e) {
            return new BaseResponse<Group>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Group>(HttpStatus.OK, group);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加集团", notes = "添加集团", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "Group", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addGroup(@RequestBody Group group) {
        if (group.getEmail() == null && group.getTelephone() == null && group.getUserName() == null) {
            return new BaseResponse<Group>(HttpStatus.BAD_REQUEST, "用户名、手机号、邮箱至少有一个",
                    null);
        }
        try {
            groupService.insertGroup(group);
        } catch (Exception e) {
            return new BaseResponse<Group>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Group>(HttpStatus.OK, group);
    }


}
