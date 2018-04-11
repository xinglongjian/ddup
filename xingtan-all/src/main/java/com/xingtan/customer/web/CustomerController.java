package com.xingtan.customer.web;

import com.xingtan.customer.service.QuestionService;
import com.xingtan.customer.service.QuestionTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping(value = "/api/customer", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CustomerController {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionTypeService questionTypeService;


}
