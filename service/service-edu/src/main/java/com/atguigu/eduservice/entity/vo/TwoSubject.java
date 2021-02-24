package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "课程列表二级对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TwoSubject {
    @ApiModelProperty("二级分类id")
    private String id;

    @ApiModelProperty("二级分类名")
    private String title;
}
