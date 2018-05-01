package com.xingtan.habit.web;

import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.habit.entity.Habit;
import com.xingtan.habit.entity.HabitType;
import com.xingtan.habit.entity.UserHabitRecord;
import com.xingtan.habit.entity.UserHabitRelation;
import com.xingtan.habit.service.HabitService;
import com.xingtan.habit.service.HabitTypeService;
import com.xingtan.habit.service.UserHabitRecordService;
import com.xingtan.habit.service.UserHabitRelationService;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/habit", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class HabitController {

    @Autowired
    private HabitService habitService;
    @Autowired
    private HabitTypeService habitTypeService;
    @Autowired
    private UserHabitRecordService userHabitRecordService;
    @Autowired
    private UserHabitRelationService userHabitRelationService;

    @GetMapping("/allType")
    @ApiOperation(value = "获取所有习惯类型", notes = "获取所有习惯类型", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAllHabitTypes() {
        List<HabitType> habitTypes = null;
        try {
            habitTypes = habitTypeService.getAll();
        } catch (Exception e) {
            log.error("getAllHabitTypes error,causedBy:{}", e.getMessage());
            return new BaseResponse<List<HabitType>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<HabitType>>(HttpStatus.OK, habitTypes);
    }

    @PostMapping("/addType")
    @ApiOperation(value = "添加习惯类型", notes = "添加习惯类型", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addHabitTypes(@RequestBody HabitType habitType) {
        try {
            log.info("habitType:{}", habitType);
            habitTypeService.insertHabitType(habitType);
            log.info("habitType add SUCCESS");
        } catch (Exception e) {
            log.error("addHabitTypes error,causedBy:{}", e);
            return new BaseResponse<HabitType>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<HabitType>(HttpStatus.OK, habitType);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加习惯", notes = "添加习惯", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addHabit(@RequestBody Habit habit) {
        try {
            log.info("habit:{}", habit);
            habitService.insertHabit(habit);
            log.info("habit add SUCCESS");
        } catch (Exception e) {
            log.error("addHabit error,causedBy:{}", e);
            return new BaseResponse<Habit>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Habit>(HttpStatus.OK, habit);
    }

    @GetMapping("/getHabitsByType/{type}")
    @ApiOperation(value = "获取制定类型的习惯", notes = "获取制定类型的习惯", httpMethod = "GET")
    @ApiImplicitParam(name = "type", value = "type", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getHabitsByType(@PathVariable("type") Long typeId) {
        List<Habit> habits = null;
        try {
            habits = habitService.getHabitByTypeId(typeId);
        } catch (Exception e) {
            log.error("getHabitsByType error.typeId:{}, causedBy:{}", typeId, e.getMessage());
            return new BaseResponse<List<Habit>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<Habit>>(HttpStatus.OK, habits);
    }

    @PostMapping("/{userId}/{byUserId}")
    @ApiOperation(value = "给用户添加习惯", notes = "给用户添加习惯", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "byUserId", value = "byUserId", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addHabitToUser(@PathVariable("userId") Long userId,
                                       @PathVariable("byUserId") Long byUserId,
                                       @RequestBody String habitIds) {
        try {
            String[] habitArrays = habitIds.split(",");
            List<UserHabitRelation> relations = new ArrayList<>();
            for (int i = 0; i < habitArrays.length; i++) {
                relations.add(new UserHabitRelation(userId, Long.parseLong(habitArrays[i]), byUserId));
            }
            userHabitRelationService.insertBatchRelations(relations);
            log.info("addHabitToUser SUCCESS. userId:{}, byUserId:{}, habitsIds:{}",
                    userId, byUserId, habitIds);
        } catch (Exception e) {
            log.error("addHabitToUser error.userId:{}, byUserId:{}, habitsIds:{},causedBy:{}",
                    userId, byUserId, habitIds, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, habitIds);
    }

    @DeleteMapping("/{userId}/{byUserId}")
    @ApiOperation(value = "删除用户习惯", notes = "删除用户习惯", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "byUserId", value = "byUserId", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse deleteHabitFromUser(@PathVariable("userId") Long userId,
                                            @PathVariable("byUserId") Long byUserId,
                                            @RequestBody String habitIds) {
        try {
            String[] habitArrays = habitIds.split(",");
            List<Long> ids = new ArrayList<>();
            for (int i = 0; i < habitArrays.length; i++) {
                ids.add(Long.parseLong(habitArrays[i]));
            }
            userHabitRelationService.deleteBatchRecord(ids);
            log.info("deleteHabitFromUser SUCCESS. userId:{}, byUserId:{}, habitsIds:{}",
                    userId, byUserId, habitIds);
        } catch (Exception e) {
            log.error("deleteHabitFromUser error.userId:{}, byUserId:{}, habitsIds:{},causedBy:{}",
                    userId, byUserId, habitIds, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, habitIds);
    }

    @PostMapping("/{userId}/{byUserId}/{habitId}")
    @ApiOperation(value = "给习惯打分", notes = "给习惯打分", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "byUserId", value = "byUserId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "habitId", value = "habitId", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse scoreHabit(@PathVariable("userId") Long userId,
                                   @PathVariable("byUserId") Long byUserId,
                                   @PathVariable("habitId") Long habitId,
                                   @RequestBody Integer score) {
        try {
            userHabitRecordService.insertRecord(new UserHabitRecord(userId, habitId, score, byUserId));
            log.info("scoreHabit SUCCESS. userId:{}, byUserId:{}, habitsId:{}, score:{}",
                    userId, byUserId, habitId, score);
        } catch (Exception e) {
            log.error("scoreHabit error.userId:{}, byUserId:{}, habitsId:{},score:{},causedBy:{}",
                    userId, byUserId, habitId, score, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, habitId);
    }
}
