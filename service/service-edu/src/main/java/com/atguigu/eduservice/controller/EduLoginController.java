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
        map.put("avatar","https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fa3.att.hudong.com%2F92%2F04%2F01000000000000119090475560392.jpg&refer=http%3A%2F%2Fa3.att.hudong.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1616139770&t=2c28199bcc7e97c48275003200c29f3c");
        return Result.ok().data(map);
    }
}
