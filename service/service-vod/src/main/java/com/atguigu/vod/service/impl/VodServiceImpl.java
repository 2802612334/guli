package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.atguigu.exception.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.util.VodProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Slf4j
@Service
public class VodServiceImpl implements VodService {

    @Override
    public String uploadVideo(MultipartFile file){
        UploadStreamRequest request = null;
        String title = null;
        String fileName = null;
        try {
            fileName = file.getOriginalFilename();
            title = fileName.substring(0,fileName.lastIndexOf("."));
            request = new UploadStreamRequest(VodProperties.KEYID, VodProperties.KEYSECRET, title, fileName, file.getInputStream());
        } catch (IOException e) {
            throw new GuliException(20001,e.getMessage());
        }
        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        String videoId = null;

        log.info("RequestId=" + request.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            videoId = response.getVideoId();
            log.info("VideoId=" + videoId + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            videoId = response.getVideoId();
            log.info("VideoId=" + videoId + "\n");
            log.error("ErrorCode=" + response.getCode() + "\n");
            log.error("ErrorMessage=" + response.getMessage() + "\n");
        }
        return videoId;
    }
}
