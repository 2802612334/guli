package com.atguigu.oss.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.oss.service.OssService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 文件上传控制器
 * </p>
 *
 * @author szf
 * @since 2021-02-20
 */
@CrossOrigin
@RestController
@RequestMapping("/upload/file")
public class OssController {

    @Autowired
    private OssService ossService;

    @ApiOperation("讲师头像上传")
    @PostMapping("/avatar")
    public Result uploadFileAvatar(@ApiParam("讲师头像") MultipartFile file){
        String url = ossService.uploadFileAvatar(file);
        return Result.ok().data("url",url);
    }

}
