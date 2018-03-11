package com.xingtan.account.web;

import com.xingtan.account.entity.Teacher;
import com.xingtan.account.service.TeacherService;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping("/{userName}")
    @ApiOperation(value = "通过用户名获取教师", notes = "通过用户名获取教师", httpMethod = "GET")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getStudentByUserName(@PathVariable("userName") String userName) {
        Teacher teacher = null;
        try {
            teacher = teacherService.getTeacherByUserName(userName);
        } catch (Exception e) {
            return new BaseResponse<Teacher>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Teacher>(HttpStatus.OK, teacher);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加教师", notes = "添加教师", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacher", value = "教师", required = true, dataType = "Teacher", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addTeacher(@RequestBody Teacher teacher) {
        if (teacher.getEmail() == null && teacher.getTelephone() == null && teacher.getUserName() == null) {
            return new BaseResponse<Teacher>(HttpStatus.BAD_REQUEST, "用户名、手机号、邮箱至少有一个",
                    null);
        }
        try {
            teacherService.insertTeacher(teacher);
        } catch (Exception e) {
            return new BaseResponse<Teacher>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Teacher>(HttpStatus.OK, teacher);
    }


}
