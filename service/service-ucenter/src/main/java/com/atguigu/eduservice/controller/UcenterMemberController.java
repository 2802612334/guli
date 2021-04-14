package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.UcenterMember;
import com.atguigu.eduservice.entity.vo.LoginVO;
import com.atguigu.eduservice.entity.vo.RegisterVO;
import com.atguigu.eduservice.service.UcenterMemberService;
import com.atguigu.exception.GuliException;
import com.atguigu.utils.JwtUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
@RequestMapping("/ucenter")
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

    @ApiOperation("会员信息获取")
    @GetMapping("/")
    public Result getMemberInfo(HttpServletRequest request){
        // 1.判断用户输入token是否有效
        boolean flag = JwtUtils.checkToken(request);
        if(flag){
            // 2.解析token
            String memberId = JwtUtils.getMemberIdByJwtToken(request);
            // 3.根据会员id信息，获取会员信息
            UcenterMember member = ucenterMemberService.getById(memberId);
            return Result.ok().data("guli_user",member);
        }else {
            throw new GuliException(20001,"token错误！");
        }
    }
}