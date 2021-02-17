package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQueryVO;
import com.atguigu.eduservice.mapper.EduTeacherMapper;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author szf
 * @since 2021-02-04
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {


    @Override
    public IPage<EduTeacher> pageQuery(Page<EduTeacher> page, TeacherQueryVO teacherQueryVO) {
        // 1.封装查询条件
        QueryWrapper<EduTeacher> queryWrapper = new QueryWrapper<>();

        queryWrapper.orderByAsc("sort");

        if(teacherQueryVO == null){
            return baseMapper.selectPage(page,queryWrapper);
        }

        String name = teacherQueryVO.getName();
        Integer level = teacherQueryVO.getLevel();
        Date begin = teacherQueryVO.getBegin();
        Date end = teacherQueryVO.getEnd();

        if(!StringUtils.isEmpty(name)){
            queryWrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            queryWrapper.eq("level",level);
        }

        if(begin != null){
            queryWrapper.lt("gmt_create",begin);
        }

        if(end != null){
            queryWrapper.le("gmt_create",end);
        }

        // 2.执行查询
        IPage<EduTeacher> info = baseMapper.selectPage(page,queryWrapper);

        return info;
    }
}
