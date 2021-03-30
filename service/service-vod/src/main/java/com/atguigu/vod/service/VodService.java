package com.atguigu.vod.service;

import com.aliyuncs.exceptions.ClientException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {

    String uploadVideo(MultipartFile file);

    void removeVideo(List<String> videoSourceIds);
}