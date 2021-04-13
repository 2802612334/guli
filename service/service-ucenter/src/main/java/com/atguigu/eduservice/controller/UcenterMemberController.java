package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.vo.LoginVO;
import com.atguigu.eduservice.entity.vo.RegisterVO;
import com.atguigu.eduservice.service.UcenterMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-04-11
 */
@Api("注册登录控制器")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/ucenter")
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @ApiOperation("会员登录")
    @PostMapping("/login")
    public Result memberLogin(@RequestBody LoginVO loginVO){
        String token = ucenterMemberService.memberLogin(loginVO);
        return Result.ok().data("token",token);
    }

    @ApiOperation("会员注册")
    @PostMapping("/register")
    public Result memberRegister(@RequestBody RegisterVO registerVO){
        ucenterMemberService.memberRegister(registerVO);
        return Result.ok();
    }
}