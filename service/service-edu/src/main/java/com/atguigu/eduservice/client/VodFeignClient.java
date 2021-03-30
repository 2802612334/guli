package com.atguigu.eduservice.client;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.impl.VodFeignClientHystrix;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
@FeignClient(value = "service-vod",fallback = VodFeignClientHystrix.class)
public interface VodFeignClient {

    @ApiOperation("课程小结视频上传")
    @PostMapping("/eduvod/video/upload")
    Result uploadVideo(@ApiParam("视频文件") MultipartFile file);


    @ApiOperation("课程小结视频删除")
    @DeleteMapping("/eduvod/video/remove")
    Result removeVideo(@ApiParam("视频文件id") @RequestBody List<String> videoSourceIds);
}
