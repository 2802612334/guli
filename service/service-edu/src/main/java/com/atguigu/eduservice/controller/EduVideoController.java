package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.EduVideo;
import com.atguigu.eduservice.service.EduVideoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    TODO 删除小结

}

