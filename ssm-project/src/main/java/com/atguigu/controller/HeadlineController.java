package com.atguigu.controller;

import com.atguigu.pojo.Headline;
import com.atguigu.service.HeadlineService;
import com.atguigu.utils.Result;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Zephyrtoria
 * @CreateTime: 2024-10-13
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("headline")
@CrossOrigin
public class HeadlineController {
    @Autowired
    private HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestHeader("token") String token, @RequestBody Headline headline) {
        Result result = headlineService.publish(token, headline);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(@Param("hid") Integer hid) {
        Result result = headlineService.findHeadlineByHid(hid);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline) {
        Result result = headlineService.updateHeadline(headline);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(@Param("hid") Integer hid) {
        Result result = headlineService.removeByHid(hid);
        System.out.println("result = " + result);
        return result;
    }
}
