package com.xingtan.school.web;

import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.school.bean.GradeData;
import com.xingtan.school.entity.GradeAlbum;
import com.xingtan.school.service.GradeAlbumService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xinglongjian on 5/20 2018 10:33.
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/album", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GradeAlbumController {

    @Autowired
    private GradeAlbumService gradeAlbumService;

    @GetMapping("/{id}")
    @ApiOperation(value = "通过ID获取相册", notes = "通过ID获取相册", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "相册ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAlbumById(@PathVariable("id") long id) {
        GradeAlbum grade = null;
        try {
            grade = gradeAlbumService.getAlbumById(id);
        } catch (Exception e) {
            return new BaseResponse<GradeData>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<GradeAlbum>(HttpStatus.OK, grade);
    }

}
