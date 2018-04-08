package com.xingtan.account.web;

import com.xingtan.account.entity.StudentParentRelation;
import com.xingtan.account.entity.User;
import com.xingtan.account.service.StudentParentRelationService;
import com.xingtan.account.service.UserService;
import com.xingtan.common.entity.FamilyRelation;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/student", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class StudentController {

    @Autowired
    private UserService userService;
    @Autowired
    private StudentParentRelationService studentParentRelationService;

    @PostMapping("/add")
    @ApiOperation(value = "通过家长添加学生", notes = "通过家长添加学生", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addStudent(@RequestParam("userId") Long createdUserId,
                                    @RequestParam("relation") String relation,
                                    @RequestBody User user) {
        if(createdUserId == null || user == null) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "params is not exist", null);
        }
        StudentParentRelation studentParentRelation;
        try {
            user.setCreatedBy(createdUserId);
            Long studentId  = userService.insertUser(user);
            studentParentRelation = new StudentParentRelation(studentId, createdUserId,
                    FamilyRelation.valueOf(relation));
            studentParentRelationService.insertRelation(studentParentRelation);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<StudentParentRelation>(HttpStatus.OK, studentParentRelation);
    }

    @GetMapping("/relations/{userId}")
    @ApiOperation(value = "通过家长ID获取学生", notes = "通过家长ID获取学生", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getStudentParents(@PathVariable("userId") Long userId) {
        if(userId == null) {
            return new BaseResponse<List<User>>(HttpStatus.BAD_REQUEST,
                    "params is not exist", null);
        }
        try {
            List<StudentParentRelation> studentParentRelations =
                    studentParentRelationService.getRelationsByParentId(userId);
            List<Long> studentIds = new ArrayList<>();
            for(StudentParentRelation relation : studentParentRelations) {
                studentIds.add(relation.getStudentId());
            }
            List<User> users = userService.getUsersByIds(studentIds);
            return new BaseResponse<List<User>>(HttpStatus.OK, users);
        } catch (Exception e) {
            return new BaseResponse<List<User>>(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(), null);
        }

    }
}
