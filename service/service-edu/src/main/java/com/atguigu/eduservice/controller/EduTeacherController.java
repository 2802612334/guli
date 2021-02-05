package com.atguigu.eduservice.controller;


import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-02-04
 */
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService eduTeacherService;

    @GetMapping("/all")
    public List<EduTeacher> getAll(){
        return eduTeacherService.list(null);
    }

    @DeleteMapping("/{id}")
    public boolean removeById(@PathVariable("id") String id){
        return eduTeacherService.removeById(id);
    }
}

