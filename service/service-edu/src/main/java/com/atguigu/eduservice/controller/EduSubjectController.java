package com.atguigu.eduservice.controller;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.entity.po.EduSubject;
import com.atguigu.eduservice.entity.vo.OneSubject;
import com.atguigu.eduservice.entity.vo.TwoSubject;
import com.atguigu.eduservice.service.EduSubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-02-21
 */
@Api("课程管理")
@CrossOrigin
@RestController
@RequestMapping("/eduservice/subject")
public class EduSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @ApiOperation("课程分类添加")
    @PostMapping
    public Result saveSubject(@ApiParam("课程分类文件") MultipartFile file){

        eduSubjectService.saveSubjectByFile(file);

        return Result.ok();
    }

    @ApiOperation("树形结构获取课程列表")
    @GetMapping("/tree/all")
    public Result getSubjectTree(){
        // 1.获取所有课程列表
        List<EduSubject> subjectList = eduSubjectService.list(null);
        Map<String,OneSubject> subjectMap = new HashMap<>();
        List<OneSubject> data = new ArrayList<>();

        // 2.封装所有的一级分类
        for (EduSubject eduSubject : subjectList) {
            if(eduSubject.getParentId().equals("0")){
                OneSubject oneSubject = new OneSubject();
                BeanUtils.copyProperties(eduSubject,oneSubject);
                data.add(oneSubject);
                subjectMap.put(oneSubject.getId(),oneSubject);
            }
        }

        // 3.在所有的一级分类下添加二级子分类
        for (EduSubject eduSubject : subjectList) {
            if(!eduSubject.getParentId().equals("0")){
                TwoSubject twoSubject = new TwoSubject();
                BeanUtils.copyProperties(eduSubject,twoSubject);
                subjectMap.get(eduSubject.getParentId()).getChildren().add(twoSubject);
            }
        }

        return Result.ok().data("items",data);
    }
}

