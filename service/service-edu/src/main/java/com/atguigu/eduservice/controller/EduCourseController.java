package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.EduCourse;
import com.atguigu.eduservice.entity.po.EduTeacher;
import com.atguigu.eduservice.entity.vo.CourseInfoVO;
import com.atguigu.eduservice.entity.vo.CourseQueryVO;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-02-23
 */
@Api("课程管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/course")
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @ApiModelProperty("获取指定课程")
    @GetMapping("/{id}")
    public Result getCourseById(@PathVariable("id") String courseId){
        CourseInfoVO courseInfoVO = eduCourseService.getCourseById(courseId);
        return Result.ok().data("courseInfo",courseInfoVO);
    }

    @ApiOperation("新增课程信息")
    @PostMapping
    public Result save(@ApiParam(name = "courseInfoVO",value = "课程信息对象",required = true) @RequestBody(required = true) CourseInfoVO courseInfoVO){
        String courseId = eduCourseService.saveCourse(courseInfoVO);
        return Result.ok().data("id",courseId);
    }

    @ApiOperation("更新课程信息")
    @PutMapping("/{id}")
    public Result updateById(
            @ApiParam(name = "id",value = "课程id") @PathVariable("id") String id,
            @ApiParam(name = "courseInfo",value = "课程信息对象") @RequestBody(required = true) CourseInfoVO courseInfoVO){
        eduCourseService.updateCourse(courseInfoVO);
        return Result.ok();
    }

    @ApiOperation("课程发布页面回显")
    @GetMapping("/release/{id}")
    public Result getReleaseCourse(@ApiParam(name = "id",value = "课程id")@PathVariable("id") String id){
        CourseInfoVO courseInfoVO = eduCourseService.getReleaseCourse(id);
        return Result.ok().data("courseInfo",courseInfoVO);
    }

    @ApiOperation("按条件分页查询讲师信息")
    @PostMapping("/{page}/{limit}")
    public Result pageQuery(
            @ApiParam(name = "page",value = "当前页码",required = true) @PathVariable("page") Integer page,
            @ApiParam(name = "limit",value = "每页记录数",required = true) @PathVariable("limit") Integer limit,
            @ApiParam(name = "courseQuery",value = "查询条件",required = false) @RequestBody(required = false) CourseQueryVO courseQuery
    ){
        // 1.封装页面对象
        Page<EduCourse> pageParam = new Page<>(page,limit);

        // 2.调用业务方法
        IPage<EduCourse> info = eduCourseService.queryPage(pageParam,courseQuery);

        // 3.封装结果并返回
        Result result = Result.ok();
        HashMap<String, Object> data = new HashMap<>();
        data.put("total",info.getTotal());
        data.put("items",info.getRecords());
        result.setData(data);

        return result;
    }

    @ApiOperation("删除课程信息")
    @DeleteMapping("/{id}")
    public Result removeById(@ApiParam(name = "id",value = "课程id")@PathVariable("id") String id){
        eduCourseService.removeCourse(id);
        return Result.ok();
    }
}

