package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.po.EduCourse;
import com.atguigu.eduservice.entity.po.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVO;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author szf
 * @since 2021-02-23
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService eduCourseDescriptionService;

    @Override
    public void updateCourse(CourseInfoVO courseInfoVO) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        BeanUtils.copyProperties(courseInfoVO,eduCourseDescription);

        this.updateById(eduCourse);
        eduCourseDescriptionService.updateById(eduCourseDescription);
    }

    /**
     *
     * @param courseInfoVO
     * @return 返回当前插入课程的id信息
     */
    @Override
    public String saveCourse(CourseInfoVO courseInfoVO) {
        EduCourse eduCourse = new EduCourse();
        EduCourseDescription eduCourseDescription = new EduCourseDescription();

        BeanUtils.copyProperties(courseInfoVO,eduCourse);
        BeanUtils.copyProperties(courseInfoVO,eduCourseDescription);

        this.save(eduCourse);
        // 课程表和课程叙述一对一关系，所以id一致，所以课程叙述表的id需要手动设置，并且设置主键生成策略为INPUT
        eduCourseDescription.setId(eduCourse.getId());
        eduCourseDescriptionService.save(eduCourseDescription);
        return eduCourse.getId();
    }

    @Override
    public CourseInfoVO getCourseById(String courseId) {
        // 1.获取指定课程信息
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        eduCourseQueryWrapper.eq("id",courseId);
        EduCourse eduCourse = this.getOne(eduCourseQueryWrapper);

        // 2.获取指定课程的简单介绍
        QueryWrapper<EduCourseDescription> eduCourseDescriptionQueryWrapper = new QueryWrapper<>();
        eduCourseDescriptionQueryWrapper.eq("id",courseId);
        EduCourseDescription eduCourseDescription = eduCourseDescriptionService.getOne(eduCourseDescriptionQueryWrapper);

        CourseInfoVO courseInfoVO = new CourseInfoVO();
        try{
            BeanUtils.copyProperties(eduCourse,courseInfoVO);
            BeanUtils.copyProperties(eduCourseDescription,courseInfoVO);
        }catch (Exception e){
            throw new GuliException(20001,"输入路径不合法");
        }

        return courseInfoVO;
    }
}
