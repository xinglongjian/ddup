package com.xingtan.school.web;

import com.google.common.collect.Lists;
import com.xingtan.school.entity.Grade;
import com.xingtan.school.entity.StudentGradeRelation;
import com.xingtan.school.entity.TeacherGradeRelation;
import com.xingtan.account.entity.User;
import com.xingtan.school.service.GradeService;
import com.xingtan.school.service.StudentGradeRelationService;
import com.xingtan.school.service.TeacherGradeRelationService;
import com.xingtan.account.service.UserService;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 班级
 */

@RestController
@Slf4j
@RequestMapping(value = "/api/grade", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class GradeController {

    @Autowired
    private GradeService gradeService;

    @Autowired
    private UserService userService;

    @Autowired
    private TeacherGradeRelationService teacherGradeRelationService;
    @Autowired
    private StudentGradeRelationService studentGradeRelationService;

    @GetMapping("/{schoolId}")
    @ApiOperation(value = "通过学校ID获取", notes = "通过学校ID获取", httpMethod = "GET")
    @ApiImplicitParam(name = "schoolId", value = "学校ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getGradesBySchoolId(@PathVariable("schoolId") long schoolId) {
        List<Grade> grades = null;
        try {
            grades = gradeService.getGradesBySchoolId(schoolId);
        } catch (Exception e) {
            return new BaseResponse<Grade>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<Grade>>(HttpStatus.OK, grades);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加班级", notes = "添加班级", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "grade", value = "班级", required = true, dataType = "Grade", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addGrade(@RequestBody Grade grade) {
        try {
            gradeService.insertGrade(grade);
        } catch (Exception e) {
            return new BaseResponse<Grade>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Grade>(HttpStatus.OK, grade);
    }

    @GetMapping("/teachers/{gradeId}")
    @ApiOperation(value = "通过班级ID获取教师", notes = "通过班级ID获取教师", httpMethod = "GET")
    @ApiImplicitParam(name = "gradeId", value = "班级ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getTeachersByGradeId(@PathVariable("gradeId") long gradeId) {
        List<User> teachers = Lists.newArrayList();
        try {
            List<TeacherGradeRelation> teacherGradeRelations =
                    teacherGradeRelationService.getRelationsByGradeId(gradeId);
            if(!CollectionUtils.isEmpty(teacherGradeRelations)) {
                List<Long> ids =  Lists.newArrayList();
                for(TeacherGradeRelation relation: teacherGradeRelations) {
                    ids.add(relation.getTeacherId());
                }
                teachers = userService.getUsersByIds(ids);
            }

        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<User>>(HttpStatus.OK, teachers);
    }
    @GetMapping("/students/{gradeId}")
    @ApiOperation(value = "通过班级ID获取学生", notes = "通过班级ID获取学生", httpMethod = "GET")
    @ApiImplicitParam(name = "gradeId", value = "班级ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getStudentsByGradeId(@PathVariable("gradeId") long gradeId) {
        List<User> students = Lists.newArrayList();
        try {
            List<StudentGradeRelation> studentGradeRelations =
                    studentGradeRelationService.getRelationsByGradeId(gradeId);
            if(!CollectionUtils.isEmpty(studentGradeRelations)) {
                List<Long> ids =  Lists.newArrayList();
                for(StudentGradeRelation relation: studentGradeRelations) {
                    ids.add(relation.getStudentId());
                }
                students = userService.getUsersByIds(ids);
            }

        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<User>>(HttpStatus.OK, students);
    }

}
