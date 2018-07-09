package com.xingtan.habit.web;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.xingtan.common.entity.PageEntity;
import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.habit.bean.*;
import com.xingtan.habit.entity.*;
import com.xingtan.habit.service.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.naming.ldap.PagedResultsControl;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private HabitQuestionService habitQuestionService;
    @Autowired
    private HabitQuestionItemService habitQuestionItemService;
    @Autowired
    private HabitQuestionRelationService habitQuestionRelationService;

    public LoadingCache<Long, HabitType> habitTypeCache = CacheBuilder.newBuilder()
            .refreshAfterWrite(1, TimeUnit.HOURS).maximumSize(1000).build(new CacheLoader<Long, HabitType>() {
                @Override
                public HabitType load(Long id) throws Exception {
                    return habitTypeService.getHabitTypeById(id);
                }
            });

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

    @GetMapping("/{id}")
    @ApiOperation(value = "获取特定ID习惯", notes = "获取特定ID习惯", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getHabitById(@PathVariable("id") long id) {
        HabitDetail habitDetail = null;
        try {
            Habit habit = habitService.getHabitById(id);
            if (habit != null) {
                habitDetail = new HabitDetail(habit);
                HabitType type = habitTypeCache.get(habitDetail.getHabitTypeId());
                habitDetail.setHabitTypeName(type.getName());
                habitDetail.setCount(userHabitRelationService.getCountOfHabit(habitDetail.getId()));
            }

            log.info("Get HabitDetail: {}", habitDetail);
        } catch (Exception e) {
            log.error("getAllHabitTypes error,causedBy:{}", e.getMessage());
            return new BaseResponse<HabitDetail>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, habitDetail);
    }

    @GetMapping("/allByParams")
    @ApiOperation(value = "获取所有习惯", notes = "获取所有习惯", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getAllHabits() {
        List<HabitData> habitDatas = null;
        int start = 0;
        int num = 10;
        try {
            habitDatas = habitService.getMostHabitsByLimit(start, num);
            for (HabitData data : habitDatas) {
                HabitType type = habitTypeCache.get(data.getHabitTypeId());
                data.setHabitTypeName(type == null ? "" : type.getName());
            }
            log.info("Get 10 Habits: {}", habitDatas);
        } catch (Exception e) {
            log.error("getAllHabitTypes error,causedBy:{}", e.getMessage());
            return new BaseResponse<List<HabitData>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, habitDatas);
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

    @GetMapping("/type/{typeId}/name/{name}")
    @ApiOperation(value = "获取制定类型的习惯", notes = "获取制定类型的习惯", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "typeId", value = "typeId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "name", value = "name", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getHabitsByTypeAndName(@PathVariable("typeId") Long typeId,
                                               @PathVariable("name") String name,
                                               @RequestParam("pageNum") int pageNum) {
        PageEntity page = null;
        try {
            if (name.equals("all")) {
                name = null;
            }
            page = habitService.getPageEntity(typeId, name, pageNum, PageEntity.PAGESIZE);
        } catch (Exception e) {
            log.error("getHabitsByTypeAndName error.typeId:{}, name:{},pageNum:{},causedBy:{}",
                    typeId, name, pageNum, e.getMessage());
            return new BaseResponse<PageEntity>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, page);
    }

    @PostMapping("/to/{userId}/by/{byUserId}")
    @ApiOperation(value = "给用户添加习惯", notes = "给用户添加习惯", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "byUserId", value = "byUserId", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addManyHabitToUser(@PathVariable("userId") Long userId,
                                           @PathVariable("byUserId") Long byUserId,
                                           @RequestBody String habitIds) {
        try {
            String[] habitArrays = habitIds.split(",");
            List<UserHabitRelation> relations = new ArrayList<>();
            for (int i = 0; i < habitArrays.length; i++) {
                relations.add(new UserHabitRelation(userId, Long.parseLong(habitArrays[i]), byUserId));
            }
            userHabitRelationService.insertBatchRelations(relations);
            log.info("addManyHabitToUser SUCCESS. userId:{}, byUserId:{}, habitsIds:{}",
                    userId, byUserId, habitIds);
        } catch (Exception e) {
            log.error("addManyHabitToUser error.userId:{}, byUserId:{}, habitsIds:{},causedBy:{}",
                    userId, byUserId, habitIds, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, habitIds);
    }

    @PostMapping("/{habitId}/{byUserId}")
    @ApiOperation(value = "给用户添加习惯", notes = "给用户添加习惯", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "habitId", value = "habitId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "byUserId", value = "byUserId", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addHabitToManyUser(@PathVariable("habitId") Long habitId,
                                           @PathVariable("byUserId") Long byUserId,
                                           @RequestBody Map<String, Object> userIds) {
        try {
            String[] userArrays = userIds.get("userIds").toString().split(",");
            List<UserHabitRelation> relations = new ArrayList<>();
            for (int i = 0; i < userArrays.length; i++) {
                relations.add(new UserHabitRelation(Long.parseLong(userArrays[i]), habitId, byUserId));
            }
            userHabitRelationService.insertBatchRelations(relations);
            log.info("addHabitToManyUser SUCCESS. habitId:{}, byUserId:{}, userIds:{}",
                    habitId, byUserId, userIds);
        } catch (Exception e) {
            log.error("addHabitToManyUser error.habitId:{}, byUserId:{}, userIds:{},causedBy:{}",
                    habitId, byUserId, userIds, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<>(HttpStatus.OK, userIds);
    }

    @GetMapping("/isExist/{habitId}/{userId}")
    @ApiOperation(value = "该用户是否已添加该习惯", notes = "该用户是否已添加该习惯", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "habitId", value = "habitId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "userId", value = "userId", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse isHabitContainUser(@PathVariable("habitId") Long habitId,
                                           @PathVariable("userId") Long userId) {
        try {
            UserHabitRelation relation = userHabitRelationService.getRelationByUserIdAndHabitId(userId, habitId);
            return new BaseResponse<>(HttpStatus.OK, relation);
        } catch (Exception e) {
            log.error("isHabitContainUser error.habitId:{}, userId:{},causedBy:{}",
                    habitId, userId, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

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

    /**
     * =================================Habit question==============================================================
     */
    @PostMapping("/question/add")
    @ApiOperation(value = "添加习惯问题", notes = "添加习惯问题", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addHabitQuestion(@RequestBody HabitQuestion question) {
        try {

            if (question.getId() == 0) {
                long id = habitQuestionService.insertHabitQuestion(question);
                log.info("addHabitQuestion Success,question:{}", question);
                return new BaseResponse<>(HttpStatus.OK, id);
            } else {
                habitQuestionService.updateHabitQuestion(question);
                log.info("updateHabitQuestion Success,question:{}", question);
                return new BaseResponse<>(HttpStatus.OK, question.getId());
            }

        } catch (Exception e) {
            log.error("addHabitQuestion error.question:{},causedBy:{}", question, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @PostMapping("/question/item/add")
    @ApiOperation(value = "添加习惯问题选项", notes = "添加习惯问题选项", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addHabitQuestionItem(@RequestBody HabitQuestionItem questionItem) {
        try {

            if (questionItem.getId() == 0) {
                long id = habitQuestionItemService.insertHabitItem(questionItem);
                log.info("addHabitQuestionItem Success,item:{}", questionItem);
                return new BaseResponse<>(HttpStatus.OK, id);
            } else {
                habitQuestionItemService.updateHabitItem(questionItem);
                log.info("editHabitQuestionItem Success,item:{}", questionItem);
                return new BaseResponse<>(HttpStatus.OK, questionItem.getId());
            }

        } catch (Exception e) {
            log.error("addHabitQuestionItem error.item:{},causedBy:{}", questionItem, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
    }

    @PostMapping("/question/relation/add/{habitId}")
    @ApiOperation(value = "添加习惯问题关联", notes = "添加习惯问题关联", httpMethod = "POST")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "habitId", value = "habitId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "questionId", value = "questionId", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addHabitQuestionRelation(@PathVariable("habitId") Long habitId,
                                                 @RequestBody String questionIds) {
        try {

            if (StringUtils.isEmpty(questionIds)) {
                return new BaseResponse<String>(HttpStatus.BAD_REQUEST, "there is no questionIds", null);
            }
            String[] ids = questionIds.split(",");
            for (int i = 0; i < ids.length; i++) {
                HabitQuestionRelation relation = new HabitQuestionRelation(habitId, Long.parseLong(ids[i]));
                habitQuestionRelationService.insertRelation(relation);
            }
            log.info("addHabitQuestionRelation Success,habitId:{},questionIds:{}", habitId, questionIds);
            return new BaseResponse<>(HttpStatus.OK, questionIds);
        } catch (Exception e) {
            log.error("addHabitQuestionItem error.habitId:{},questionIds:{},causedBy:{}",
                    habitId, questionIds, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @DeleteMapping("/question/relation/{habitId}/{questionId}")
    @ApiOperation(value = "删除习惯问题关联", notes = "删除习惯问题关联", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "habitId", value = "habitId", required = true, dataType = "Long", paramType = "path"),
            @ApiImplicitParam(name = "questionId", value = "questionId", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse deleteHabitQuestionRelation(@PathVariable("habitId") Long habitId,
                                                    @PathVariable("questionId") Long questionId) {
        try {

            habitQuestionRelationService.deleteRelation(habitId, questionId);
            log.info("deleteHabitQuestionRelation Success,habitId:{},questionId:{}", habitId, questionId);
            return new BaseResponse<>(HttpStatus.OK, habitId + "--" + questionId);
        } catch (Exception e) {
            log.error("deleteHabitQuestionRelation error.habitId:{},questionId:{},causedBy:{}", habitId, questionId, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @GetMapping("/question/search/{value}")
    @ApiOperation(value = "通过题目搜索习惯问题", notes = "通过题目搜索习惯问题", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "value", value = "value", required = true, dataType = "String", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse searchHabitQuestion(@PathVariable("value") String title) {
        try {
            List<HabitQuestion> habits = habitQuestionService.getHabitQuestionByTitle(title);
            return new BaseResponse<>(HttpStatus.OK, habits);
        } catch (Exception e) {
            log.error("searchHabitQuestion error.title:{},causedBy:{}", title, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @GetMapping("/question/items/{questionId}")
    @ApiOperation(value = "显示问题的选项", notes = "显示问题的选项", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "questionId", value = "questionId", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse toHabitQuestionItem(@PathVariable("questionId") Long questionId) {
        try {
            HabitQuestionData data = habitQuestionService.getHabitQuestionDataById(questionId);
            return new BaseResponse<>(HttpStatus.OK, data);
        } catch (Exception e) {
            log.error("toHabitQuestionItem error.questionId:{},causedBy:{}", questionId, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @GetMapping("/question/item/{id}")
    @ApiOperation(value = "检索单个选项", notes = "检索单个选项", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getHabitQuestionItem(@PathVariable("id") Long id) {
        try {
            HabitQuestionItem item = habitQuestionItemService.getHabitItemById(id);
            return new BaseResponse<>(HttpStatus.OK, item);
        } catch (Exception e) {
            log.error("getHabitQuestionItem error.id:{},causedBy:{}", id, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @GetMapping("/question/details/{habitId}")
    @ApiOperation(value = "查询该习惯所有问题的详情", notes = "查询该习惯所有问题的详情", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "habitId", value = "habitId", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getHabitQuestionsDetails(@PathVariable("habitId") Long habitId) {
        try {
            HabitQuestionDetails data = habitQuestionService.getHabitQuestionDetails(habitId);
            return new BaseResponse<>(HttpStatus.OK, data);
        } catch (Exception e) {
            log.error("getHabitQuestionsDetails error.habitId:{},causedBy:{}", habitId, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @GetMapping("/question/{id}")
    @ApiOperation(value = "检索单个问题", notes = "检索单个问题", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getHabitQuestion(@PathVariable("id") Long id) {
        try {
            HabitQuestion item = habitQuestionService.getHabitQuestionById(id);
            return new BaseResponse<>(HttpStatus.OK, item);
        } catch (Exception e) {
            log.error("getHabitQuestion error.id:{},causedBy:{}", id, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @GetMapping("/question/relations/{habitId}")
    @ApiOperation(value = "检索单个习惯的问题列表", notes = "检索单个习惯的问题列表", httpMethod = "GET")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "habitId", value = "habitId", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getHabitRelationsByHabitId(@PathVariable("habitId") Long habitId) {
        try {
            HabitQuestionRelationList item = habitQuestionService.getHabitRelationList(habitId);
            return new BaseResponse<>(HttpStatus.OK, item);
        } catch (Exception e) {
            log.error("getHabitQuestion error.habitId:{},causedBy:{}", habitId, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

    @DeleteMapping("/question/item/{id}")
    @ApiOperation(value = "删除单个选项", notes = "删除单个选项", httpMethod = "DELETE")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Long", paramType = "path")
    })
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse deleteHabitQuestionItem(@PathVariable("id") Long id) {
        try {
            habitQuestionItemService.deleteHabitItem(id);
            log.info("deleteHabitQuestionItem SUCCESS, id:{}", id);
            return new BaseResponse<>(HttpStatus.OK, id);
        } catch (Exception e) {
            log.error("deleteHabitQuestionItem error.id:{},causedBy:{}", id, e.getMessage());
            return new BaseResponse<>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }

    }

}
