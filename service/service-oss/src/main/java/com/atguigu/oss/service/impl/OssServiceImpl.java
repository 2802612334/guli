package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.exception.GuliException;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.util.OssProperties;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@Service
public class OssServiceImpl implements OssService {

    @Autowired
    private OssProperties ossProperties;

    @Override
    public String uploadFileAvatar(MultipartFile file) {
        OSS ossClient = new OSSClientBuilder().build(OssProperties.ENDOPINT,OssProperties.KEYID , OssProperties.KEYSECRET);;
        String path = null;
        String url = null;
        try{
            // 获取文件扩展名
            int index = file.getOriginalFilename().lastIndexOf('.');
            String lastName = file.getOriginalFilename().substring(index,file.getOriginalFilename().length());

            // 生成上传路径
            path = new DateTime().toString("yyyy/MM/dd");
            // 生成上传文件名
            path += "/" + UUID.randomUUID().toString().replaceAll("-","") + lastName;
            // 生成上传文件访问链接
            url = "https://" + OssProperties.BUCKETNAME + "." + OssProperties.ENDOPINT + "/" + path;

            ossClient.putObject(OssProperties.BUCKETNAME, path, file.getInputStream());
            log.info("文件" + path + "上传成功！访问地址为：" + url);
        }catch (Exception e){
            throw new GuliException(20001,e.getMessage());
        }finally{
            ossClient.shutdown();
        }
        return url;
    }
}
