package com.atguigu.vod.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Api("视频上传")
@CrossOrigin
@RestController
@RequestMapping("/eduvod")
public class VodController {

    @Autowired
    private VodService vodService;

    @ApiOperation("课程小结视频上传")
    @PostMapping("/video/upload")
    public Result uploadVideo(@ApiParam("视频文件") MultipartFile file){
        String videoSourceId = vodService.uploadVideo(file);
        return Result.ok().data("videoSourceId",videoSourceId);
    }

    @ApiOperation("课程小结视频删除")
    @DeleteMapping("/video/remove")
    public Result removeVideo(@ApiParam("视频文件id") @RequestBody List<String> videoSourceIds){
        vodService.removeVideo(videoSourceIds);
        return Result.ok();
    }
}
