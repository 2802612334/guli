package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.EduTeacher;
import com.atguigu.eduservice.entity.vo.CourseInfoVO;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("新增课程信息")
    @PostMapping
    public Result save(
            @ApiParam(name = "courseInfoVO",value = "课程信息对象",required = true) @RequestBody(required = true) CourseInfoVO courseInfoVO
    ){
        String courseId = eduCourseService.saveCourse(courseInfoVO);
        return Result.ok().data("id",courseId);
    }
}

