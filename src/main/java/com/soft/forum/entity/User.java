package com.soft.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String userName;
    private String userPassword;
    private String userMail;
    private Integer userState;
    private Integer userRole;
}
