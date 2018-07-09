package com.xingtan.account.web;

import com.xingtan.account.bean.Child;
import com.xingtan.account.bean.WeixinUser;
import com.xingtan.account.entity.StudentParentRelation;
import com.xingtan.account.entity.User;
import com.xingtan.account.entity.UserBaseData;
import com.xingtan.account.service.StudentParentRelationService;
import com.xingtan.account.service.UserBaseDataService;
import com.xingtan.account.service.UserService;
import com.xingtan.common.constants.FileConstants;
import com.xingtan.common.entity.*;
import com.xingtan.common.utils.DateUtils;
import com.xingtan.common.utils.HeadImageUtils;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.habit.entity.Habit;
import com.xingtan.habit.service.HabitService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@Slf4j
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserBaseDataService userBaseDataService;
    @Autowired
    private StudentParentRelationService studentParentRelationService;
    @Autowired
    private HabitService habitService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/{userId}")
    @ApiOperation(value = "通过Id获取学生", notes = "通过Id获取学生", httpMethod = "GET")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getUserById(@PathVariable("userId") Long userId) {
        User user = null;
        try {
            user = userService.getUserById(userId);
        } catch (Exception e) {
            log.error("getUserById Error, userId:{},causedBy:{}", userId, e.getMessage());
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        if (user == null) {
            return new BaseResponse<>(HttpStatus.NOT_FOUND, "no data", user);
        }
        return new BaseResponse<>(HttpStatus.OK, user);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加用户", notes = "添加用户", httpMethod = "POST")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addUser(@RequestBody User user) {
        if (user.getEmail() == null && user.getTelephone() == null && user.getUserName() == null) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "用户名、手机号、邮箱至少有一个",
                    null);
        }
        try {
            userService.insertUser(user);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<User>(HttpStatus.OK, user);
    }

    @PostMapping("/addByWx")
    @ApiOperation(value = "通过微信添加用户", notes = "通过微信添加用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse<User> addUserByWeixin(@RequestBody WeixinUser wxUser) {
        User user = null;
        try {
            log.info("addUserByWeixin, user:{}", wxUser);
            UserBaseData baseData = userBaseDataService.getDataByOpenId(wxUser.getOpenId());
            if (baseData == null) {
                user = userService.saveByWxUser(wxUser);
            } else {
                user = userService.getUserById(baseData.getUserId());
            }
            log.info("addUserByWeixin Success.");
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<User>(HttpStatus.OK, user);
    }

    @PostMapping("/delete/{id}")
    @ApiOperation(value = "删除用户", notes = "删除用户", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse deleteUser(@PathVariable("id") Long id) {
        if (id == null) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "ID is empty",
                    null);
        }
        try {
            userService.deleteUser(id);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, "SUCCESS", id);
    }

    @PostMapping("/addByParent")
    @ApiOperation(value = "添加父母添加用户", notes = "添加父母添加用户", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "user", value = "用户", required = true, dataType = "User", paramType = "body")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addUserByParent(HttpServletRequest request,
                                        @RequestParam(value = "imgFile", required = false) MultipartFile imageFile,
                                        @RequestParam("realName") String realName,
                                        @RequestParam("nickName") String nickName,
                                        @RequestParam("enName") String enName,
                                        @RequestParam("sex") String sex,
                                        @RequestParam("birthday") String birthday,
                                        @RequestParam("createdUserId") String createdUserId,
                                        @RequestParam("relation") String relation) {
        long userId = 0;
        log.info("addUserByParent,imgFile:{},realName:{}, nickName:{}, enName:{}, sex:{}, birthday:{}, " +
                "createdUserId:{}", imageFile, realName, nickName, enName, sex, birthday, createdUserId);
        try {
            User user = userService.saveByParent(nickName, realName, enName, createdUserId);
            String fileName = String.format("%s.%s", user.getId(), ImageSuffix.JPG.getName());
            if (imageFile != null) {
                HeadImageUtils.saveHeadImage(uploadPath, fileName, imageFile.getBytes());
            }
            UserBaseData parent = userBaseDataService.getDataByUserId(Long.parseLong(createdUserId));
            UserBaseData baseData = new UserBaseData();
            baseData.setUserId(user.getId());
            baseData.setSex(UserSexEnum.of(Integer.parseInt(sex)));
            baseData.setBirthday(DateUtils.formatYYYYMMDD.parse(birthday));
            baseData.setHeadImage(fileName);
            if (parent != null) {
                baseData.setCountry(parent.getCountry());
                baseData.setProvince(parent.getProvince());
                baseData.setCity(parent.getCity());
            }
            userBaseDataService.insertUserBaseData(baseData);
            // 插入关系表
            StudentParentRelation studentParentRelation =
                    new StudentParentRelation(user.getId(), Long.parseLong(createdUserId), FamilyRelation.valueOf(relation));
            studentParentRelation.setAlias("");
            studentParentRelationService.insertRelation(studentParentRelation);
            userId = user.getId();
            log.info("addUserByParent SUCCESS. userInfo:{}", user);
        } catch (Exception e) {
            log.error("addUserByParent FAIL, error:{}", e);
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, userId);
    }


    @GetMapping("/getByParent/{userId}")
    @ApiOperation(value = "获取孩子", notes = "获取孩子", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse<List<Child>> getChildsByParent(@PathVariable("userId") Long userId) {
        List<Child> childs = new ArrayList<>();
        try {
            List<StudentParentRelation> relations = studentParentRelationService.getRelationsByParentId(userId);
            if (relations == null) {
                return new BaseResponse<List<Child>>(HttpStatus.OK, childs);
            }
            for (StudentParentRelation r : relations) {
                User user = userService.getUserById(r.getStudentId());
                UserBaseData userBaseData = userBaseDataService.getDataByUserId(r.getStudentId());
                Child child = new Child();
                if (child != null) {
                    child.setId(user.getId());
                    child.setRealName(user.getRealName());
                    child.setEnName(user.getEnName());
                }
                if (userBaseData != null) {
                    child.setBirthday(DateUtils.formatYYYYMMDD.format(userBaseData.getBirthday()));
                    child.setSex(userBaseData.getSex().ordinal());
                }
                List<Habit> habits = habitService.getHabitsByUserId(user.getId());
                child.setHabits(habits);
                childs.add(child);
            }
            log.info("getChildsByParent, parentId:{}, childs:{}", userId, childs);
        } catch (Exception e) {
            log.error("getChildsByParent, parentId:{}, childs:{},causedBy:{}", userId, childs, e);
            return new BaseResponse<List<Child>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<Child>>(HttpStatus.OK, childs);
    }

    @GetMapping("/getHeadImage/{userId}/{size}")
    @ApiOperation(value = "获取用户头像", notes = "获取用户头像", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "size", value = "大小", required = false, dataType = "Integer", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public void getHeadImage(@PathVariable("userId") Long userId,
                             @PathVariable("size") Integer size,
                             HttpServletResponse res) {
        ServletOutputStream writer = null;
        try {
            String sizeform = "";
            if (size != null) {
                sizeform = String.format("%s*%s", size, size);
            }

            String fileName = String.format("%s_%s.%s", userId, sizeform, ImageSuffix.JPG.getName());
            File file = new File(uploadPath + HeadImageUtils.HEAD_IMAGE_PATH + "/" + fileName);
            byte[] fileByte = HeadImageUtils.getHeadImage(file);
            String fileType = new MimetypesFileTypeMap().getContentType(file);
            res.setContentType(fileType);
            res.setContentLength(fileByte.length);
            writer = res.getOutputStream();
            writer.write(fileByte);
            writer.flush();
        } catch (Exception e) {
            log.error("getHeadImage error, userId:{}", userId);
            e.printStackTrace();
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (writer != null)
                    writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
