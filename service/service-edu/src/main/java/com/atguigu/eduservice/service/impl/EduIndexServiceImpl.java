package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.po.EduCourse;
import com.atguigu.eduservice.entity.po.EduTeacher;
import com.atguigu.eduservice.service.EduCourseService;
import com.atguigu.eduservice.service.EduIndexService;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EduIndexServiceImpl implements EduIndexService {

    @Autowired
    private EduCourseService eduCourseService;

    @Autowired
    private EduTeacherService eduTeacherService;

    @Override
    @Cacheable(
            cacheNames = "indexHot",
            unless = "#result == null"      // 当result为null，则不会进行缓存
    )
    public Map<String, Object> getIndexHot() {
        // 1.获取所有热门课程
        IPage<EduCourse> courseIPage = new Page<>(1,8);
        QueryWrapper<EduCourse> courseWrapper = new QueryWrapper<>();
        courseWrapper.orderByDesc("view_count");
        IPage<EduCourse> page = eduCourseService.page(courseIPage, courseWrapper);
        List<EduCourse> hotCourseList = page.getRecords();

        // 2.获取所有热门讲师
        List<EduTeacher> hotTeacherList = eduTeacherService.getHotTeacher();

        // 3.封装查询结果
        Map<String,Object> map = new HashMap<>();
        map.put("hotCourseList",hotCourseList);
        map.put("hotTeacherList",hotTeacherList);

        return map;
    }
}
