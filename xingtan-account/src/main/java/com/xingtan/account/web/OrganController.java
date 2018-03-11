package com.xingtan.account.web;

import com.xingtan.account.entity.Organ;
import com.xingtan.account.service.OrganService;
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
public class OrganController {

    @Autowired
    private OrganService organService;

    @GetMapping("/{userName}")
    @ApiOperation(value = "通过用户名获取机构", notes = "通过用户名获取机构", httpMethod = "GET")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getOrganByUserName(@PathVariable("userName") String userName) {
        Organ organ = null;
        try {
            organ = organService.getOrganByUserName(userName);
        } catch (Exception e) {
            return new BaseResponse<Organ>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Organ>(HttpStatus.OK, organ);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加机构", notes = "添加机构", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "Organ", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addOrgan(@RequestBody Organ organ) {
        if (organ.getEmail() == null && organ.getTelephone() == null && organ.getUserName() == null) {
            return new BaseResponse<Organ>(HttpStatus.BAD_REQUEST, "用户名、手机号、邮箱至少有一个",
                    null);
        }
        try {
            organService.insertOrgan(organ);
        } catch (Exception e) {
            return new BaseResponse<Organ>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Organ>(HttpStatus.OK, organ);
    }


}
