package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.EduCourse;
import com.atguigu.eduservice.entity.po.EduSubject;
import com.atguigu.eduservice.entity.po.EduTeacher;
import com.atguigu.eduservice.entity.vo.CourseInfoVO;
import com.atguigu.eduservice.service.EduCourseService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty("获取指定课程")
    @GetMapping("/{id}")
    public Result getSubjectById(@PathVariable("id") String courseId){
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.eq("id",courseId);
        EduCourse eduCourse = eduCourseService.getOne(eduCourseQueryWrapper);
        CourseInfoVO courseInfoVO = new CourseInfoVO();
        BeanUtils.copyProperties(eduCourse,courseInfoVO);
        return Result.ok().data("courseInfo",courseInfoVO);
    }

    @ApiOperation("新增课程信息")
    @PostMapping
    public Result save(
            @ApiParam(name = "courseInfoVO",value = "课程信息对象",required = true) @RequestBody(required = true) CourseInfoVO courseInfoVO
    ){
        String courseId = eduCourseService.saveCourse(courseInfoVO);
        return Result.ok().data("id",courseId);
    }
}

