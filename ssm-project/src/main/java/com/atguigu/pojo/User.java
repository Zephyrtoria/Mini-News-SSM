package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

/**
 * @TableName news_user
 */

@Data
public class User implements Serializable {
    @TableId(type= IdType.AUTO)
    private Integer uid;

    private String username;

    private String userPwd;

    private String nickName;

    @Version
    private Integer version;

    private Integer isDeleted;

    @Serial
    private static final long serialVersionUID = 1L;
}