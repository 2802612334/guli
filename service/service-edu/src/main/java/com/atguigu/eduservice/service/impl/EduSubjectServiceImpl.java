package com.atguigu.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.atguigu.eduservice.entity.dto.EduSubjectDTO;
import com.atguigu.eduservice.entity.po.EduSubject;
import com.atguigu.eduservice.listener.ExcelListener;
import com.atguigu.eduservice.mapper.EduSubjectMapper;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.exception.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author szf
 * @since 2021-02-21
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Autowired
    private ExcelListener excelListener;

    @Override
    public void saveSubjectByFile(MultipartFile file) {
        try{
            EasyExcel.read(file.getInputStream(), EduSubjectDTO.class,excelListener).sheet().doRead();
//            ExcelReaderBuilder read = EasyExcel.read(file.getInputStream(), EduSubjectDTO.class, excelListener);
        }catch (Exception e){
            throw new GuliException(20001,e.getMessage());
        }
    }
}
