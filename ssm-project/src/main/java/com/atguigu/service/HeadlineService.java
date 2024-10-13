package com.atguigu.service;

import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVO;
import com.atguigu.utils.Result;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author Lenovo
 * @description 针对表【news_headline】的数据库操作Service
 * @createDate 2024-10-11 16:25:32
 */
public interface HeadlineService extends IService<Headline> {
    /**
     * 根据传入的前端信息，查询对应的新闻
     *
     * @param portalVO
     * @return
     */
    Result findNewPage(PortalVO portalVO);

    /**
     * 根据传入的hid在数据库中查找对应的头条
     *
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 用户发布新闻
     *
     * @param token
     * @param headline
     * @return
     */
    Result publish(String token, Headline headline);

    /**
     * 根据hid查找对应头条
     *
     * @param hid
     * @return
     */
    Result findHeadlineByHid(Integer hid);

    /**
     * 更新头条
     *
     * @param headline
     * @return
     */
    Result updateHeadline(Headline headline);

    /**
     * 根据头条id删除对应头条
     * @param hid
     * @return
     */
    Result removeByHid(Integer hid);
}
