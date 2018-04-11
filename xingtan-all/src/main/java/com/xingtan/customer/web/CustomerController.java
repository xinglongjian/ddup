package com.xingtan.customer.web;

import com.xingtan.common.web.BaseResponse;
import com.xingtan.common.web.HttpStatus;
import com.xingtan.customer.entity.Question;
import com.xingtan.customer.entity.QuestionType;
import com.xingtan.customer.service.QuestionService;
import com.xingtan.customer.service.QuestionTypeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/api/customer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionTypeService questionTypeService;

    @PostMapping("/question")
    @ApiOperation(value = "添加反馈问题", notes = "添加反馈问题", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addQuestion(@RequestBody Question question) {
        Long id = null;
        try {
            id = questionService.insertQuestion(question);
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, id);
    }

    @PostMapping("/questionType/add")
    @ApiOperation(value = "添加问题类型", notes = "添加问题类型", httpMethod = "POST")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse addQuestionType(@RequestBody QuestionType questionType) {
        Long id = null;
        try {
            id = questionTypeService.insertQuestionType(questionType);
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, id);
    }

    @DeleteMapping("/question/{id}")
    @ApiOperation(value = "删除问题", notes = "删除问题", httpMethod = "POST")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse deleteQuestion(@PathVariable("id") Long id) {
        try {
            questionService.deleteQuestion(id);
        } catch (Exception e) {
            return new BaseResponse<Long>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Long>(HttpStatus.OK, id);
    }

    @GetMapping("/question/{id}")
    @ApiOperation(value = "获取一个问题", notes = "获取一个问题", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getQuestion(@PathVariable("id") Long id) {
        Question question = null;
        try {
            question = questionService.getQuestionById(id);
        } catch (Exception e) {
            return new BaseResponse<Question>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<Question>(HttpStatus.OK, question);
    }

    @GetMapping("/questions/type/{id}")
    @ApiOperation(value = "获取一类问题", notes = "获取一类问题", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getQuestionByType(@PathVariable("id") Long id) {
        List<Question> questions = null;
        try {
            questions = questionService.getQuestionsByTypeId(id);
        } catch (Exception e) {
            return new BaseResponse<List<Question>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<Question>>(HttpStatus.OK, questions);
    }

    @GetMapping("/questionType/all")
    @ApiOperation(value = "获取所有问题类型", notes = "获取所有问题类型", httpMethod = "GET")
    @ApiImplicitParam(name = "id", value = "ID", required = true, dataType = "String", paramType = "path")
    @ApiResponses({
            @ApiResponse(code = org.apache.http.HttpStatus.SC_OK, message = "操作成功")
    })
    public BaseResponse getQuestionTypes() {
        List<QuestionType> questionTypes = null;
        try {
            questionTypes = questionTypeService.getAll();
        } catch (Exception e) {
            return new BaseResponse<List<QuestionType>>(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage(), null);
        }
        return new BaseResponse<List<QuestionType>>(HttpStatus.OK, questionTypes);
    }

}
