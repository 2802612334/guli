package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "课程列表一级对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OneSubject implements Serializable {

    @ApiModelProperty("一级分类id")
    private String id;

    @ApiModelProperty("一级分类名")
    private String title;

    @ApiModelProperty("二级子列表")
    private List<TwoSubject> children = new ArrayList<>();
}
