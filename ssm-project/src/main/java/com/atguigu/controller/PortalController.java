package com.atguigu.controller;

import com.atguigu.pojo.vo.PortalVO;
import com.atguigu.service.HeadlineService;
import com.atguigu.service.TypeService;
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
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes() {
        Result result = typeService.findAllTypes();
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVO portalVO) {
        Result result = headlineService.findNewPage(portalVO);
        System.out.println("result = " + result);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(@Param("hid") Integer hid) {
        Result result = headlineService.showHeadlineDetail(hid);
        System.out.println("result = " + result);
        return result;
    }
}
