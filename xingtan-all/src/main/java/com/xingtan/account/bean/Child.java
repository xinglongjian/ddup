package com.xingtan.account.bean;

import com.xingtan.common.entity.UserSexEnum;
import com.xingtan.habit.bean.HabitData;
import com.xingtan.habit.entity.Habit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author zhengweiliang
 * @Date 2018/4/26 13:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Child implements Serializable {
    private Long id;
    private String headImage;
    private String realName;
    private String nickName;
    private String enName;
    private int sex;
    private String birthday;

    // 拥有的习惯列表
    private List<Habit> habits;
}
