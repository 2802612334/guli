package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.po.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author szf
 * @since 2021-02-21
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSubjectByFile(MultipartFile file);
}
