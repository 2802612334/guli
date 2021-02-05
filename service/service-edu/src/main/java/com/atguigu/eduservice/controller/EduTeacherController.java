package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-02-04
 */
@Api("讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/all")
    public Result getAll(){
        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
        return Result.ok().data("items",eduTeacherList);
    }

    @ApiOperation("根据ID删除指定讲师")
    @DeleteMapping("/{id}")
    public Result removeById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id") String id){
        boolean flag = eduTeacherService.removeById(id);
        if(flag){
            return Result.ok();
        }else {
            return Result.error();
        }
    }

    @ApiModelProperty("按条件分页查询讲师信息")
    @PostMapping("/{page}/{limit}")
    public Result pageQuery(
            @ApiParam(name = "page",value = "当前页码",required = true) @PathVariable("page") Integer page,
            @ApiParam(name = "limit",value = "每页记录数",required = true) @PathVariable("limit") Integer limit,
            @ApiParam(name = "teacherQuery",value = "查询条件",required = false) @RequestBody(required = false) TeacherQueryVO teacherQueryVO
            ){
        // 1.封装页面对象
        Page<EduTeacher> pageParam = new Page<>(page,limit);
        // 2.封装查询条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByAsc("sort");

        String name = teacherQueryVO.getName();
        Integer level = teacherQueryVO.getLevel();
        String begin = teacherQueryVO.getBegin();
        String end = teacherQueryVO.getEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }

        if(!StringUtils.isEmpty(begin)){
            queryWrapper.lt("gmt_create",begin);
        }

        if(!StringUtils.isEmpty(end)){
            queryWrapper.le("gmt_create",end);
        }

        // 3.执行查询
        IPage<EduTeacher> info = eduTeacherService.page(pageParam, queryWrapper);

        // 4.封装结果
        Result result = Result.ok();
        HashMap<String, Object> data = new HashMap<>();
        data.put("total",info.getTotal());
        data.put("items",info.getRecords());
        result.setData(data);

        return result;
    }

}

