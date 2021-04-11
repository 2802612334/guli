package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.CrmBanner;
import com.atguigu.eduservice.service.CrmBannerService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 首页banner表 前端控制器（前台用户接口）
 * </p>
 *
 * @author szf
 * @since 2021-03-31
 */
@Api("导航条")
@CrossOrigin
@RestController
@RequestMapping("/educms/banner")
public class CrmBannerFrontController {

    @Autowired
    private CrmBannerService crmBannerService;

    @ApiOperation("前台获取banner数据")
    @GetMapping("/get/{limit}")
    public Result getBanner(@ApiParam(name = "limit",value = "记录数",required = true) @PathVariable("limit") Integer limit){

        // 1.封装分页查询对象
        Page<CrmBanner> page = new Page<>(1,limit);
        // 2.调用业务查询
        List<CrmBanner> crmBanners = crmBannerService.selectFrontBanner(page);
        // 3.封装查询结果
        Map<String,Object> map = new HashMap<>();
        map.put("total",limit);
        map.put("items",crmBanners);

        return Result.ok().data(map);
    }

}

