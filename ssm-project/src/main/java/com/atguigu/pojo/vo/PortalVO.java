package com.atguigu.pojo.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-13
 * @Description: 接收findNewPage的前端信息
 * @Version: 1.0
 */
@Data
public class PortalVO {
    private String keyWords;    // 搜索标题关键字
    private Integer type = 0;   // 新闻类型
    private Integer pageNum = 1;    // 页码数
    private Integer pageSize = 10;   // 页大小
}
