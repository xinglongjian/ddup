package com.xingtan.school.web;

import com.xingtan.account.entity.User;
import com.xingtan.account.service.UserService;
import com.xingtan.common.entity.AdminType;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.school.entity.StudentSchoolRelation;
import com.xingtan.school.entity.TeacherSchoolRelation;
import com.xingtan.school.service.TeacherSchoolRelationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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

    @PostMapping("/addToSchool")
    @ApiOperation(value = "学生添加到学校", notes = "学生添加到学校", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addToSchool(@RequestParam("teacherId") long teacherId,
                                    @RequestParam("schoolId") long schoolId) {
        try {
            Long id = null;
            User user = userService.getUserById(teacherId);
            TeacherSchoolRelation relation = new TeacherSchoolRelation();
            if (user != null) {
                relation.setAlias(user.getNickName());
            } else {
                relation.setAlias(StringUtils.EMPTY);
            }
            relation.setSchoolId(schoolId);
            relation.setTeacherId(teacherId);
            relation.setType(AdminType.OTHER);
            id = teacherSchoolRelationService.insertRelation(relation);
            log.info("addToSchool teacherId:{},schoolId:{}", teacherId, schoolId);
            return new BaseResponse<Long>(HttpStatus.OK, id);
        } catch (Exception e) {
            log.error("addToSchool teacherId:{},schoolId:{},error:{}", teacherId, schoolId, e);
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR,
                    e.getMessage(), null);
        }
    }
}
