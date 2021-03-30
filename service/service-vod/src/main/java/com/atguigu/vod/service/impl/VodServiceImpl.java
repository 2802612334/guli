package com.atguigu.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.atguigu.exception.GuliException;
import com.atguigu.vod.service.VodService;
import com.atguigu.vod.util.AliyunVodSDKUtils;
import com.atguigu.vod.util.VodProperties;
import com.mysql.cj.util.TimeUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    @Override
    public void removeVideo(List<String> videoSourceIds) {
        DefaultAcsClient client = AliyunVodSDKUtils.initVodClient(VodProperties.KEYID,VodProperties.KEYSECRET,VodProperties.REGIONID);
        DeleteVideoRequest request = new DeleteVideoRequest();

        StringBuffer stringBuffer = new StringBuffer();
        //支持传入多个视频ID，多个用逗号分隔
        for (String videoSourceId : videoSourceIds) {
            stringBuffer.append(videoSourceId + ",");
        }
        stringBuffer.substring(0, stringBuffer.length() - 1);
        request.setVideoIds(stringBuffer.toString());

        try {
            DeleteVideoResponse acsResponse = client.getAcsResponse(request);
        } catch (ClientException e) {
            throw new GuliException(20001,e.getMessage());
        }
    }
}
