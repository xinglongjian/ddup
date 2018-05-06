package com.xingtan.admin.web;

import com.xingtan.account.entity.User;
import com.xingtan.admin.entity.Area;
import com.xingtan.admin.service.AreaService;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xinglongjian on 5/5 2018 13:58.
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/area", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AreaController {

    @Autowired
    private AreaService areaService;

    @GetMapping("/byParentNo/{parentNo}/{level}")
    @ApiOperation(value = "通过父区码获取子区域", notes = "通过父区码获取子区域", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "parentNo", value = "父区码", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "level", value = "级别", required = true, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAreasByParentNo(@PathVariable("parentNo") String parentNo,
                                           @PathVariable("level") int level) {
        if (StringUtils.isEmpty(parentNo)) {
            return new BaseResponse<List<Area>>(HttpStatus.BAD_REQUEST, "parentNo is not exist", null);
        }
        List<Area> areas;
        try {
            areas = areaService.getAreasByParentNo(parentNo, level);
        } catch (Exception e) {
            return new BaseResponse<List<Area>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, areas);
    }

    @GetMapping("/levelOne")
    @ApiOperation(value = "获取一级区域", notes = "获取一级区域", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAreasOfLevelOne() {
        List<Area> areas;
        try {
            areas = areaService.getAllLevelOne();
        } catch (Exception e) {
            return new BaseResponse<List<Area>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, areas);
    }


}
