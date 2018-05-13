package com.xingtan.school.web;

import com.google.common.collect.Lists;
import com.xingtan.account.entity.User;
import com.xingtan.account.service.UserService;
import com.xingtan.common.entity.AdminType;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.school.bean.TeacherSchool;
import com.xingtan.school.entity.Grade;
import com.xingtan.school.entity.School;
import com.xingtan.school.entity.TeacherGradeRelation;
import com.xingtan.school.entity.TeacherSchoolRelation;
import com.xingtan.school.service.GradeService;
import com.xingtan.school.service.SchoolService;
import com.xingtan.school.service.TeacherGradeRelationService;
import com.xingtan.school.service.TeacherSchoolRelationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by xinglongjian on 5/7 0007 22:55.
 */
@RestController
@Slf4j
@RequestMapping(value = "/api/teacher", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TeacherController {
    @Autowired
    private UserService userService;
    @Autowired
    private TeacherSchoolRelationService teacherSchoolRelationService;
    @Autowired
    private TeacherGradeRelationService teacherGradeRelationService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private GradeService gradeService;

    @PostMapping("/addToSchool")
    @ApiOperation(value = "学生添加到学校", notes = "学生添加到学校", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addToSchool(@RequestBody TeacherSchool teacherSchool) {
        try {
            Long id = null;
            User user = userService.getUserById(teacherSchool.getTeacherId());
            TeacherSchoolRelation relation = new TeacherSchoolRelation();
            if (user != null) {
                relation.setAlias(user.getNickName());
            } else {
                relation.setAlias(StringUtils.EMPTY);
            }
            relation.setSchoolId(teacherSchool.getSchoolId());
            relation.setTeacherId(teacherSchool.getTeacherId());
            relation.setType(AdminType.OTHER);
            id = teacherSchoolRelationService.insertRelation(relation);
            log.info("addToSchool info:{}", teacherSchool);
            return new BaseResponse<Long>(HttpStatus.OK, id);
        } catch (Exception e) {
            log.error("addToSchool info:{},error:{}", teacherSchool, e);
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(), null);
        }
    }

    @GetMapping("/schools/{teacherId}")
    @ApiOperation(value = "获取老师的关联学校", notes = "获取老师的关联学校", httpMethod = "GET")
    @ApiImplicitParam(name = "teacherId", value = "教师ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getSchoolsByTeacherId(@PathVariable("teacherId") long teacherId) {
        List<School> schools = Lists.newArrayList();
        try {
            List<TeacherSchoolRelation> teacherSchoolRelations =
                    teacherSchoolRelationService.getRelationsByTeacherId(teacherId);
            if(!CollectionUtils.isEmpty(teacherSchoolRelations)) {
                List<Long> ids =  Lists.newArrayList();
                for(TeacherSchoolRelation relation: teacherSchoolRelations) {
                    ids.add(relation.getSchoolId());
                }
                schools = schoolService.getSchoolsByIds(ids);
            }

        } catch (Exception e) {
            return new BaseResponse<List<School>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<School>>(HttpStatus.OK, schools);
    }

    @GetMapping("/grades/{teacherId}")
    @ApiOperation(value = "获取老师的关联班级", notes = "获取老师的关联班级", httpMethod = "GET")
    @ApiImplicitParam(name = "teacherId", value = "教师ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getGradesByTeacherId(@PathVariable("teacherId") long teacherId) {
        List<Grade> grades = Lists.newArrayList();
        try {
            List<TeacherGradeRelation> teacherGradeRelations =
                    teacherGradeRelationService.getRelationByTeacherId(teacherId);
            if(!CollectionUtils.isEmpty(teacherGradeRelations)) {
                List<Long> ids =  Lists.newArrayList();
                for(TeacherGradeRelation relation: teacherGradeRelations) {
                    ids.add(relation.getGradeId());
                }
                grades = gradeService.getGradesByIds(ids);
            }

        } catch (Exception e) {
            return new BaseResponse<List<Grade>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, grades);
    }
}
