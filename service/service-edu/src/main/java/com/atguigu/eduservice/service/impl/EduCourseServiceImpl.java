package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.po.EduCourse;
import com.atguigu.eduservice.entity.po.EduCourseDescription;
import com.atguigu.eduservice.entity.vo.CourseInfoVO;
import com.atguigu.eduservice.mapper.EduCourseMapper;
import com.atguigu.eduservice.service.EduCourseDescriptionService;
import com.atguigu.eduservice.service.EduCourseService;
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
}
