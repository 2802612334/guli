package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.service.MsmService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api("短信发送")
@CrossOrigin
@RestController
@RequestMapping("/edumsm")
public class MsmController {

    @Autowired
    private MsmService msmService;

    /**
     *
     * @param mobile    用户手机号
     * @return
     */
    @ApiOperation("注册功能手机验证码发送")
    @GetMapping("/register/code/{mobile}")
    public Result getMobileCode(@ApiParam("用户手机号") @PathVariable("mobile") String mobile){
        msmService.sendMobileCode(mobile);
        return Result.ok();
    }
}
