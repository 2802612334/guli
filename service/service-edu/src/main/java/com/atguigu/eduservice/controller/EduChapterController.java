package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.EduChapter;
import com.atguigu.eduservice.entity.po.EduVideo;
import com.atguigu.eduservice.entity.vo.ChapterVO;
import com.atguigu.eduservice.entity.vo.VideoVO;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    public Result getChapterAndVideoTree(@PathVariable("id") String courseId){
        List<ChapterVO> data = new ArrayList<>();
        Map<String, ChapterVO> map = new HashMap<>();

        // 1.获取该课程下所有章节信息
        QueryWrapper<EduChapter> eduChapterQueryWrapper = new QueryWrapper<>();
        eduChapterQueryWrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = eduChapterService.list(eduChapterQueryWrapper);
        for (EduChapter eduChapter : eduChapters) {
            ChapterVO chapterVO = new ChapterVO();
            BeanUtils.copyProperties(eduChapter,chapterVO);
            data.add(chapterVO);
            map.put(chapterVO.getId(),chapterVO);
        }

        // 2.获取所有小结信息
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id",courseId);
        List<EduVideo> eduVideos = eduVideoService.list(eduVideoQueryWrapper);

        // 3.将所有小结信息整合到章节中
        for (EduVideo eduVideo : eduVideos) {
            VideoVO videoVO = new VideoVO();
            BeanUtils.copyProperties(eduVideo,videoVO);
            map.get(videoVO.getChapterId()).getChildren().add(videoVO);
        }

        return Result.ok().data("items",data);
    }

    @ApiOperation("新增章节")
    @PostMapping
    public Result save(
            @ApiParam(name = "chapter",value = "章节对象",required = true) @RequestBody EduChapter eduChapter
    ){
        eduChapterService.save(eduChapter);
        return Result.ok().data("id",eduChapter.getId());
    }

    @ApiOperation("根据ID删除指定章节")
    @DeleteMapping("/{id}")
    public Result removeById(@ApiParam(name = "id",value = "章节id",required = true) @PathVariable("id") String id){

        eduChapterService.removeChapterById(id);

        return Result.ok();
    }

    @ApiOperation("更新章节信息")
    @PutMapping("/{id}")
    public Result updateById(
            @ApiParam(name = "id",value = "章节id",required = true) @PathVariable("id") String id,
            @ApiParam(name = "chapter",value = "章节对象",required = true) @RequestBody(required = true) EduChapter chapter
    ){
        chapter.setId(id);
        eduChapterService.updateById(chapter);
        return Result.ok();
    }

}

