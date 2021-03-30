package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodFeignClient;
import com.atguigu.eduservice.entity.po.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.exception.GuliException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-02-23
 */
@Api("小结管理")
@RestController
@RequestMapping("/eduservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @Autowired
    private VodFeignClient vodFeignClient;

    @ApiOperation("添加小结")
    @PostMapping
    public Result save(
            @ApiParam(name = "eduVideo",value = "小结对象",required = true) @RequestBody EduVideo eduVideo
    ){
        eduVideoService.save(eduVideo);
        return Result.ok();
    }

    @ApiOperation("更新小结")
    @PostMapping("/{id}")
    public Result updateById(
            @ApiParam(name = "id",value = "小结id",required = true) @PathVariable("id") String videoId,
            @ApiParam(name = "eduVideo",value = "小结对象",required = true) @RequestBody EduVideo eduVideo
    ){
        eduVideo.setId(videoId);
        eduVideoService.updateById(eduVideo);
        return Result.ok();
    }

    @ApiOperation("删除小结信息")
    @DeleteMapping("/{id}")
    public Result removeById(
            @ApiParam(name = "id",value = "小结id",required = true) @PathVariable("id") String videoId
    ){
        eduVideoService.removeVideo(videoId);
        return Result.ok();
    }

    @ApiOperation("删除小结视频")
    @DeleteMapping("/temp/{sourceId}")
    public Result removeSourceVideo(
            @ApiParam(name = "sourceId",value = "小结视频id",required = true) @PathVariable("sourceId") String sourceId
    ){
        List<String> sourceIds = new ArrayList<>();
        sourceIds.add(sourceId);

        Result result = vodFeignClient.removeVideo(sourceIds);
        if(result.getCode() == 20001){
            throw new GuliException(result.getCode(),result.getMessage());
        }

        return Result.ok();
    }
}

