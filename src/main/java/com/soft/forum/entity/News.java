package com.soft.forum.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

@Data
public class News {
    @TableId(type = IdType.AUTO)
    private Integer newsId;
    private String newsTitle;
    private String newsLink;
    private String newsImage;
    private Date newsCreatedDate;
    private Integer newsState;
}
