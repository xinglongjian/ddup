package com.xingtan.choose.web;

import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.choose.entity.Classes;
import com.xingtan.choose.service.ClassesService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(value = "/classes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClassesController {

    @Autowired
    private ClassesService classesService;

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID获取班级", notes = "通过ID获取班级", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getClassesById(@PathVariable("id") long id) {
        Classes classes = null;
        try {
            classes = classesService.getClassesById(id);
        } catch (Exception e) {
            return new BaseResponse<Classes>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Classes>(HttpStatus.OK, classes);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加班级", notes = "添加班级", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "classes", value = "班级", required = true, dataType = "Classes", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addClasses(@RequestBody Classes classes) {
        try {
            classesService.insertClasses(classes);
        } catch (Exception e) {
            return new BaseResponse<Classes>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Classes>(HttpStatus.OK, classes);
    }


}
