package com.atguigu.eduservice.mapper;

import com.atguigu.eduservice.entity.po.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVO;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author szf
 * @since 2021-02-23
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {

    CourseInfoVO selectReleaseCourse(@Param("courseId")String courseId);
}
