package com.xingtan.account.web;

import com.xingtan.account.entity.Student;
import com.xingtan.account.service.StudentService;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/{userName}")
    @ApiOperation(value = "通过用户名获取学生", notes = "通过用户名获取学生", httpMethod = "GET")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getStudentByUserName(@PathVariable("userName") String userName) {
        Student student = null;
        try {
            student = studentService.getStudentByUserName(userName);
        } catch (Exception e) {
            return new BaseResponse<Student>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Student>(HttpStatus.OK, student);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加学生", notes = "添加学生", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "Student", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addStudent(@RequestBody Student student) {
        if (student.getEmail() == null && student.getTelephone() == null && student.getUserName() == null) {
            return new BaseResponse<Student>(HttpStatus.BAD_REQUEST, "用户名、手机号、邮箱至少有一个",
                    null);
        }
        try {
            studentService.insertStudent(student);
        } catch (Exception e) {
            return new BaseResponse<Student>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Student>(HttpStatus.OK, student);
    }


}
