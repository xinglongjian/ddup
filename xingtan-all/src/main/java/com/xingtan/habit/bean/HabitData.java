package com.xingtan.habit.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.naming.ldap.PagedResultsControl;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HabitData implements Serializable {
    private long id;
    private String name;
    private String description;
    private String image;
    private int sexScope;
    private int ageStart;
    private int ageEnd;
    //类型
    private long habitTypeId;
    private String habitTypeName;
    // 参加人的数量
    private int count;

}
