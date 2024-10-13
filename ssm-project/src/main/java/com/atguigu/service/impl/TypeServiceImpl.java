package com.atguigu.service.impl;

import com.atguigu.mapper.TypeMapper;
import com.atguigu.pojo.Type;
import com.atguigu.service.TypeService;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lenovo
 * @description 针对表【news_type】的数据库操作Service实现
 * @createDate 2024-10-11 16:25:32
 */
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
        implements TypeService {
    @Autowired
    private TypeMapper typeMapper;

    /**
     * 查询news_type数据库，获取类型tid和tname，放入数组后，封装成result对象返回
     *
     * @return
     */
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        if (types == null) {
            return Result.build(null, 500, "Error");
        }
        return Result.ok(types);
    }
}