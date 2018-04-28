package com.xingtan.account.bean;

import com.xingtan.common.entity.UserSexEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ChildForm implements Serializable {
    private String realName;
    private String nickName;
    private String enName;
    private Long createdUserId;
    private int gender;
    private Date birthday;
}
