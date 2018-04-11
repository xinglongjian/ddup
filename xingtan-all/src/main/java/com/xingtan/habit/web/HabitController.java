package com.xingtan.habit.web;

import com.xingtan.habit.service.HabitService;
import com.xingtan.habit.service.HabitTypeService;
import com.xingtan.habit.service.UserHabitRecordService;
import com.xingtan.habit.service.UserHabitRelationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
