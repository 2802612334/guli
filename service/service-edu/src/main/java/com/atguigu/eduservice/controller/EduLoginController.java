package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/*
* @CrossOrigin：解决跨域问题（strict-origin-when-cross-origin）
*       跨域问题：不同请求协议，不同Ip地址，不同端口号
* */
@Api("管理系统登录")
@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin
public class EduLoginController {

    @PostMapping("/login")
    public Result login(){
        return Result.ok().data("token","admin");
    }

    @GetMapping("/info")
    public Result info(){
        Map<String,Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://guliedu-szf.oss-cn-beijing.aliyuncs.com/2021/03/02/fbef69aa0a594bfab0f0b14f6a366d19.jpg");
        return Result.ok().data(map);
    }
}
