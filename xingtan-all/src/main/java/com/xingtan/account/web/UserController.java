package com.xingtan.account.web;

import com.xingtan.account.bean.Child;
import com.xingtan.account.bean.ChildForm;
import com.xingtan.account.bean.WeixinUser;
import com.xingtan.account.entity.StudentParentRelation;
import com.xingtan.account.entity.User;
import com.xingtan.account.entity.UserBaseData;
import com.xingtan.account.service.StudentParentRelationService;
import com.xingtan.account.service.UserBaseDataService;
import com.xingtan.account.service.UserService;
import com.xingtan.common.constants.FileConstants;
import com.xingtan.common.entity.FromSource;
import com.xingtan.common.entity.ImageSuffix;
import com.xingtan.common.entity.UserStatus;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    @GetMapping("/{userName}")
    @ApiOperation(value = "通过用户名获取学生", notes = "通过用户名获取学生", httpMethod = "GET")
    @ApiImplicitParam(name = "userName", value = "用户名", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getUserByUserName(@PathVariable("userName") String userName) {
        if (StringUtils.isEmpty(userName)) {
            return new BaseResponse<User>(HttpStatus.BAD_REQUEST, "userName is not exist", null);
        }
        User user = null;
        try {
            user = userService.getUserByUserName(userName);
        } catch (Exception e) {
            return new BaseResponse<User>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        if (user == null) {
            return new BaseResponse<User>(HttpStatus.OK, "no data", user);
        }
        return new BaseResponse<User>(HttpStatus.OK, user);
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
                                        @RequestBody ChildForm child) {
        long userId = 0;
        try {
            User user = new User();
            user.setUserName(child.getRealName());
            user.setNickName(child.getNickName());
            user.setRealName(child.getRealName());
            user.setEnName(child.getEnName());
            user.setCreatedUserId(child.getCreatedUserId());
            user.setFromSource(FromSource.WEIXIN.name());
            user.setStatus(UserStatus.ENABLE.ordinal());
            userService.insertUser(user);

            String realPath = request.getSession().getServletContext().getRealPath("/");
            String fileName = user.getId() + ImageSuffix.JPG.getName();
            if (imageFile != null) {
                File dir = new File(realPath + FileConstants.HEAD_IMAGE_PATH);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                File file = new File(dir, fileName);
                imageFile.transferTo(file);
            }
            UserBaseData parent = userBaseDataService.getDataByUserId(child.getCreatedUserId());
            UserBaseData baseData = new UserBaseData();
            baseData.setUserId(user.getId());
            baseData.setSex(child.getSex());
            baseData.setHeadImage(fileName);
            if(parent!=null) {
                baseData.setCountry(parent.getCountry());
                baseData.setProvince(parent.getProvince());
                baseData.setCity(parent.getCity());
            }
            userBaseDataService.insertUserBaseData(baseData);
            userId = user.getId();
            log.info("addUserByParent SUCCESS. baseInfo:{}", child);
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, userId);
    }


    @GetMapping("/getByParent/{userId}")
    @ApiOperation(value = "获取孩子", notes = "获取孩子", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_BAD_REQUEST, message = "参数不全"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部错误"),
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getChildsByParent(@PathVariable("userId") Long userId) {
        List<Child> childs = new ArrayList<>();
        try {
            List<StudentParentRelation> relations = studentParentRelationService.getRelationsByParentId(userId);
            if (relations == null) {
                return new BaseResponse(HttpStatus.OK, Collections.emptyList());
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
                    child.setBirthday(userBaseData.getBirthday());
                    child.setHeadImage(userBaseData.getHeadImage());
                    child.setSex(userBaseData.getSex());
                }
                childs.add(child);
            }
            log.info("getChildsByParent, parentId:{}, childs:{}", userId, childs);
        } catch (Exception e) {
            return new BaseResponse<List<Child>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<Child>>(HttpStatus.OK, childs);
    }

}
