package com.atguigu.eduservice.client.impl;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public class VodFeignClientHystrix implements VodFeignClient {

    @Override
    public Result uploadVideo(MultipartFile file) {
        return Result.error().message("文件服务器故障，请稍后再试！");
    }

    @Override
    public Result removeVideo(List<String> videoSourceIds) {
        return Result.error().message("文件服务器故障，请稍后再试！");
    }
}
