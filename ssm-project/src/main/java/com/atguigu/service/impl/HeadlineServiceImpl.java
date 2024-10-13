package com.atguigu.service.impl;

import com.atguigu.mapper.HeadlineMapper;
import com.atguigu.pojo.Headline;
import com.atguigu.pojo.vo.PortalVO;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Lenovo
 * @description 针对表【news_headline】的数据库操作Service实现
 * @createDate 2024-10-11 16:25:32
 */
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
        implements HeadlineService {
    @Autowired
    private HeadlineMapper headlineMapper;
    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 1. keyWords设置(like)
     * 2. 新闻类型设置
     * 3. 分页查询设置
     * 4. 封装结果返回
     * <p>
     * 注意：
     * 1. 查询需要自定义语句，自定义mapper方法，携带分页
     * 2. 返回的结果：List<Map>
     *
     * @param portalVO
     * @return
     */
    @Override
    public Result findNewPage(PortalVO portalVO) {
        // 因为没有对应的实体类接收来自数据库的数据，所以直接使用一个Map<String, Object>来做接收类
        IPage<Map> page = new Page<>(portalVO.getPageNum(), portalVO.getPageSize());
        headlineMapper.selectMyPage(page, portalVO);

        Map<String, Object> data = new HashMap<>();
        data.put("pageData", page.getRecords());
        data.put("pageNum", page.getCurrent());
        data.put("pageSize", page.getSize());
        data.put("totalPage", page.getPages());
        data.put("totalSize", page.getTotal());

        Map<String, Object> pageInfo = new HashMap<>();
        pageInfo.put("pageInfo", data);
        return Result.ok(pageInfo);
    }

    /**
     * 1. 根据hid查询头条信息
     * 2. 该头条的访问次数+1
     * 2. 将查询到的头条信息封装后返回
     *
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map<Integer, Map<String, Object>> map = headlineMapper.selectHeadlineByHid(hid);
        Map<String, Object> data = new HashMap<>();
        // 注意：由于版本不同，这里在HeadlineMapper的方法强制添加@MayKey()，得到的是一个hid:data的形式

        data.put("headline", map.get(hid));

        // 注意：查询和修改操作需要乐观锁参与，所以需要判断版本号
        LambdaUpdateWrapper<Headline> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Headline::getHid, hid)
                .set(Headline::getPageViews, (Integer) map.get(hid).get("pageViews") + 1);
        headlineMapper.update(null, wrapper);    // 会自动比对version来决定是否修改

        return Result.ok(data);
    }

    /**
     * 1. 根据token检查用户登录状态，如果未登录返回504
     * 2. 如果已登录，将传入的headline插入到数据库中，返回200
     *
     * @param token
     * @param headline
     * @return
     */
    @Override
    public Result publish(String token, Headline headline) {
        if (jwtHelper.isExpiration(token)) {
            return Result.build(null, ResultCodeEnum.NOT_LOGIN);
        }

        headline.setPublisher(jwtHelper.getUserId(token).intValue());
        headline.setPageViews(0);
        Date date = new Date();
        headline.setCreateTime(date);
        headline.setUpdateTime(date);
        int rows = headlineMapper.insert(headline);
        if (rows > 0) {
            return Result.ok(null);
        }
        return Result.build(null, 500, "error");
    }

    /**
     * 根据hid查找对应头条
     *
     * @param hid
     * @return
     */
    @Override
    public Result findHeadlineByHid(Integer hid) {
        LambdaQueryWrapper<Headline> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Headline::getHid, hid);
        Headline headline = headlineMapper.selectOne(wrapper);
        Map data = new HashMap<>();
        data.put("headline", headline);

        return Result.ok(data);
    }

    /**
     * 根据传入头条信息更新头条
     * 注意要获取version
     *
     * @param headline
     * @return
     */
    @Override
    public Result updateHeadline(Headline headline) {
        Integer version = headlineMapper.selectById((long) headline.getHid()).getVersion();
        headline.setVersion(version);   // 乐观锁
        headline.setUpdateTime(new Date());

        LambdaUpdateWrapper<Headline> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Headline::getHid, headline.getHid());
        headlineMapper.update(headline, wrapper);

        return Result.ok(null);
    }

    /**
     * 根据传入id删除对应头条
     *
     * @param hid
     * @return
     */
    @Override
    public Result removeByHid(Integer hid) {
        LambdaQueryWrapper<Headline> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Headline::getHid, hid);

        headlineMapper.delete(wrapper);
        return Result.ok(null);
    }

}




