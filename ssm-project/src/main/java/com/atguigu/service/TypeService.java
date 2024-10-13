package com.atguigu.service;

import com.atguigu.pojo.Type;
import com.atguigu.pojo.vo.PortalVO;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Lenovo
 * @description 针对表【news_type】的数据库操作Service
 * @createDate 2024-10-11 16:25:32
 */
public interface TypeService extends IService<Type> {

    /**
     * 查询所有的头条类型
     *
     * @return
     */
    Result findAllTypes();


}
