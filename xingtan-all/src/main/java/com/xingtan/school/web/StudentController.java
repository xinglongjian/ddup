package com.xingtan.school.web;

import com.google.common.collect.Lists;
import com.xingtan.common.entity.AdminType;
import com.xingtan.school.bean.StudentSchool;
import com.xingtan.school.entity.*;
import com.xingtan.account.entity.StudentParentRelation;
import com.xingtan.account.entity.User;
import com.xingtan.school.service.*;
import com.xingtan.account.service.StudentParentRelationService;
import com.xingtan.account.service.UserService;
import com.xingtan.common.entity.FamilyRelation;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {

    @Autowired
    private UserService userService;
    @Autowired
    private SchoolService schoolService;
    @Autowired
    private StudentParentRelationService studentParentRelationService;
    @Autowired
    private GradeValidateMessageService gradeValidateMessageService;
    @Autowired
    private StudentGradeRelationService studentGradeRelationService;
    @Autowired
    private StudentSchoolRelationService studentSchoolRelationService;


    @PostMapping("/addByParent")
    @ApiOperation(value = "通过家长添加学生", notes = "通过家长添加学生", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addStudent(@RequestParam("userId") Long createdUserId,
                                   @RequestParam("relation") String relation,
                                   @RequestBody User user) {
        if (createdUserId == null || user == null) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "params is not exist", null);
        }
        StudentParentRelation studentParentRelation;
        try {
            user.setCreatedUserId(createdUserId);
            Long studentId = userService.insertUser(user);
            studentParentRelation = new StudentParentRelation(studentId, createdUserId,
                    FamilyRelation.valueOf(relation));
            studentParentRelationService.insertRelation(studentParentRelation);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<StudentParentRelation>(HttpStatus.OK, studentParentRelation);
    }

    @PostMapping("/addToGrade")
    @ApiOperation(value = "学生添加到班级", notes = "学生添加到班级", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addStudentToGrade(@RequestParam("isNeedValidate") boolean isNeedValidate,
                                          @RequestBody GradeValidateMessage message) {
        if (message == null) {
            return new BaseResponse<>(HttpStatus.BAD_REQUEST,
                    "params is not exist", null);
        }
        try {
            if (isNeedValidate) {
                gradeValidateMessageService.insertMessage(message);
            } else {
                StudentGradeRelation relation = new StudentGradeRelation(message.getStudentId(), message.getGradeId());
                studentGradeRelationService.insertRelation(relation);
            }
            return new BaseResponse<>(HttpStatus.OK, null);
        } catch (Exception e) {
            return new BaseResponse<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(), null);
        }
    }

    @PostMapping("/addToSchool")
    @ApiOperation(value = "学生添加到学校", notes = "学生添加到学校", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addToSchool(@RequestBody StudentSchool studentSchool) {
        try {
            StudentSchoolRelation relation = new StudentSchoolRelation();
            relation.setSchoolId(studentSchool.getSchoolId());
            relation.setStudentId(studentSchool.getStudentId());
            relation.setStartDate(new Date());
            Long id = studentSchoolRelationService.insertRelation(relation);
            log.info("addToSchool info:{}", studentSchool);
            return new BaseResponse<Long>(HttpStatus.OK, id);
        } catch (Exception e) {
            log.error("addToSchool info:{},error:{}", studentSchool, e);
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(), null);
        }
    }

    @PostMapping("/relations/{userId}")
    @ApiOperation(value = "通过家长ID获取学生", notes = "通过家长ID获取学生", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getStudentParents(@PathVariable("userId") Long userId) {
        if (userId == null) {
            return new BaseResponse<List<User>>(HttpStatus.BAD_REQUEST,
                    "params is not exist", null);
        }
        try {
            List<StudentParentRelation> studentParentRelations =
                    studentParentRelationService.getRelationsByParentId(userId);
            List<Long> studentIds = new ArrayList<>();
            for (StudentParentRelation relation : studentParentRelations) {
                studentIds.add(relation.getStudentId());
            }
            List<User> users = userService.getUsersByIds(studentIds);
            return new BaseResponse<List<User>>(HttpStatus.OK, users);
        } catch (Exception e) {
            return new BaseResponse<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(), null);
        }
    }

    @GetMapping("/schools/{studentId}")
    @ApiOperation(value = "获取学生的关联学校", notes = "获取学生的关联学校", httpMethod = "GET")
    @ApiImplicitParam(name = "studentId", value = "学生ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getSchoolsByTeacherId(@PathVariable("studentId") long studentId) {
        List<School> schools = Lists.newArrayList();
        try {
            List<StudentSchoolRelation> studentSchoolRelations =
                    studentSchoolRelationService.getRelationsByStudentId(studentId);
            if(!CollectionUtils.isEmpty(studentSchoolRelations)) {
                List<Long> ids =  Lists.newArrayList();
                for(StudentSchoolRelation relation: studentSchoolRelations) {
                    ids.add(relation.getSchoolId());
                }
                schools = schoolService.getSchoolsByIds(ids);
            }

        } catch (Exception e) {
            return new BaseResponse<List<School>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<School>>(HttpStatus.OK, schools);
    }
}
