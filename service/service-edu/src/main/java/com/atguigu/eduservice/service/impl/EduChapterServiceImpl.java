package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.po.EduChapter;
import com.atguigu.eduservice.entity.po.EduVideo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService eduVideoService;

    @Override
    public void removeChapterById(String id) {

        // 1.判断该章节下面是否还有小节，如果有小结则无法删除
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();

        eduVideoQueryWrapper.eq("chapter_id",id);
        int count = eduVideoService.count(eduVideoQueryWrapper);

        // 2.章节中有小结信息，不可以删除
        if(count > 0){
            throw new GuliException(20001,"该章节中有小结，不可删除。");
        }

        // 3.章节中无小结信息，可以删除
        this.removeById(id);
    }
}
