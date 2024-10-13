package com.atguigu.mapper;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
 * @author Lenovo
 * @description 针对表【news_headline】的数据库操作Mapper
 * @createDate 2024-10-11 16:25:32
 * @Entity com.atguigu.pojo.Headline
 */
@Mapper
public interface HeadlineMapper extends BaseMapper<Headline> {
    @MapKey("hid")
    IPage<Map> selectMyPage(IPage<Map> page, @Param("portalVo") PortalVO portalVO);

@MapKey("hid")
Map<Integer, Map<String, Object>> selectHeadlineByHid(Integer hid);
}