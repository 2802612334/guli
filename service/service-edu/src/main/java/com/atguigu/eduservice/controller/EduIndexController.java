package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.service.EduIndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * <p>
 * 首页 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-04-01
 */
@Api("首页控制器")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/index")
public class EduIndexController {

    @Autowired
    private EduIndexService eduIndexService;

    @ApiOperation("获取首页数据")
    @GetMapping("/hot")
    public Result getIndexHot(){
        Map<String,Object> map = eduIndexService.getIndexHot();
        return Result.ok().data(map);
    }
}
