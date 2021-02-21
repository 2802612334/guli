package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.dto.EduSubjectDTO;
import com.atguigu.eduservice.entity.po.EduSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class ExcelListener extends AnalysisEventListener<EduSubjectDTO> {

    @Autowired
    private EduSubjectService eduSubjectService;

    @Override
    public void invoke(EduSubjectDTO data, AnalysisContext context) {
        // 1.判断一级分类是否存在该记录
        EduSubject oneEduSubject = existOneSubjectName(data);
        if(oneEduSubject == null){
            // 2.添加该一级分类
            oneEduSubject = new EduSubject();
            oneEduSubject.setTitle(data.getOneSubjectName());
            eduSubjectService.save(oneEduSubject);
        }
        // 3.判断二级分类是否存在该记录
        EduSubject twoEduSubject = existTwoSubjectName(data, oneEduSubject);
        if(twoEduSubject == null){
            // 4.添加二级分类
            twoEduSubject = new EduSubject();
            twoEduSubject.setTitle(data.getTwoSubjectName());
            twoEduSubject.setParentId(oneEduSubject.getId());
            eduSubjectService.save(twoEduSubject);
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    private EduSubject existOneSubjectName(EduSubjectDTO eduSubjectDTO){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("title",eduSubjectDTO.getOneSubjectName());

        return eduSubjectService.getOne(queryWrapper);
    }

    private EduSubject existTwoSubjectName(EduSubjectDTO eduSubjectDTO,EduSubject oneSubject){
        QueryWrapper<EduSubject> queryWrapper = new QueryWrapper<>();

        // SELECT * FROM edu_subject where title = 二级分类名 and parent_id = 一级分类id
        queryWrapper.eq("title",eduSubjectDTO.getTwoSubjectName());

        queryWrapper.eq("parent_id",oneSubject.getId());

        return eduSubjectService.getOne(queryWrapper);
    }
}
