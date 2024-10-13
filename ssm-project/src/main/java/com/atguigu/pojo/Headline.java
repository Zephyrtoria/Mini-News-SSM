package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName news_headline
 */
@TableName(value ="news_headline")
@Data
public class Headline implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer hid;

    private String title;

    private String article;

    private Integer type;

    private Integer publisher;

    private Integer pageViews;

    private Date createTime;

    private Date updateTime;

    @Version
    private Integer version;

    private Integer isDeleted;

    @Serial
    private static final long serialVersionUID = 1L;
}