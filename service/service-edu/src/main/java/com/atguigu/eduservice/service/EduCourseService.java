package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.po.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author szf
 * @since 2021-02-23
 */
public interface EduCourseService extends IService<EduCourse> {

    void updateCourse(CourseInfoVO courseInfoVO);

    String saveCourse(CourseInfoVO courseInfoVO);

    CourseInfoVO getCourseById(String courseId);

    CourseInfoVO getReleaseCourse(String id);
}
