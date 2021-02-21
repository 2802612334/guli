package com.atguigu.eduservice.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
//@Accessors(chain = true)    使用EasyExcel不能加入该注解
@ApiModel(value="课程文件读取实体对象", description="课程实体")
public class EduSubjectDTO {

    @ExcelProperty(value = "一级分类",index = 0)
    private String oneSubjectName;

    @ExcelProperty(value = "二级分类",index = 1)
    private String twoSubjectName;

}
