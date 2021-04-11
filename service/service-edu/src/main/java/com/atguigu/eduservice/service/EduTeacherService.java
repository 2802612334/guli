package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.po.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author szf
 * @since 2021-02-04
 */
public interface EduTeacherService extends IService<EduTeacher> {

    IPage<EduTeacher> pageQuery(Page<EduTeacher> page, TeacherQueryVO teacherQueryVO);

    List<EduTeacher> getHotTeacher();
}
