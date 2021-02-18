package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @ApiOperation("所有讲师列表")
    @GetMapping("/all")
    public Result getAll(){
        List<EduTeacher> eduTeacherList = eduTeacherService.list(null);
        return Result.ok().data("items",eduTeacherList);
    }

    @ApiOperation("根据ID查询讲师信息")
    @GetMapping("/{id}")
    public Result selectById(@ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id") String id){
        EduTeacher eduTeacher = eduTeacherService.getById(id);
        return Result.ok().data("items",eduTeacher);
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

    @ApiOperation("讲师信息更新")
    @PutMapping("{id}")
    public Result updateById(
            @ApiParam(name = "id",value = "讲师ID",required = true) @PathVariable("id") String id,
            @ApiParam(name = "eduTeacher",value = "修改讲师信息",required = true) EduTeacher eduTeacher
    ){
        eduTeacher.setId(id);
        eduTeacherService.updateById(eduTeacher);
        return Result.ok();
    }

    @ApiOperation("新增讲师")
    @PostMapping
    public Result save(
            @ApiParam(name = "teacher",value = "讲师对象",required = true) @RequestBody(required = true) EduTeacher eduTeacher
    ){
        eduTeacherService.save(eduTeacher);
        return Result.ok();
    }

    @ApiOperation("按条件分页查询讲师信息")
    @PostMapping("/{page}/{limit}")
    public Result pageQuery(
            @ApiParam(name = "page",value = "当前页码",required = true) @PathVariable("page") Integer page,
            @ApiParam(name = "limit",value = "每页记录数",required = true) @PathVariable("limit") Integer limit,
            @ApiParam(name = "teacherQuery",value = "查询条件",required = false) @RequestBody(required = false) TeacherQueryVO teacherQuery
    ){
        // 1.封装页面对象
        Page<EduTeacher> pageParam = new Page<>(page,limit);

        // 2.调用业务方法
        IPage<EduTeacher> info = eduTeacherService.pageQuery(pageParam, teacherQuery);

        // 3.封装结果并返回
        Result result = Result.ok();
        HashMap<String, Object> data = new HashMap<>();
        data.put("total",info.getTotal());
        data.put("items",info.getRecords());
        result.setData(data);

        return result;
    }

}

