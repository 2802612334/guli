package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.EduChapter;
import com.atguigu.eduservice.entity.po.EduSubject;
import com.atguigu.eduservice.entity.po.EduVideo;
import com.atguigu.eduservice.entity.vo.ChapterVO;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.atguigu.eduservice.entity.vo.TwoSubject;
import com.atguigu.eduservice.entity.vo.VideoVO;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-02-23
 */
@Api("章节管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/chapter")
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @Autowired
    private EduVideoService eduVideoService;

    @ApiOperation("树形获取指定课程所有章节和小结信息")
    @GetMapping("/tree/all/{id}")
    public Result getChapterAndVideoTree(@PathVariable("id") String subjectId){
        List<ChapterVO> data = new ArrayList<>();
        Map<String, ChapterVO> map = new HashMap<>();

        // 1.获取该课程下所有章节信息
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",subjectId);
        List<EduChapter> eduChapters = eduChapterService.list(eduChapterQueryWrapper);
        for (EduChapter eduChapter : eduChapters) {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(eduChapter,chapterVO);
            data.add(chapterVO);
            map.put(chapterVO.getId(),chapterVO);
        }

        // 2.获取所有小结信息
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",subjectId);
        List<EduVideo> eduVideos = eduVideoService.list(eduVideoQueryWrapper);

        // 3.将所有小结信息整合到章节中
        for (EduVideo eduVideo : eduVideos) {
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(eduVideo,videoVO);
            map.get(videoVO.getChapterId()).getChildren().add(videoVO);
        }

        return Result.ok().data("items",data);
    }
}

