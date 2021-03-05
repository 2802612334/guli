package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ApiModel(value = "章节对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterVO implements Serializable {

    @ApiModelProperty("章节id")
    private String id;

    @ApiModelProperty("章节名称")
    private String title;

    @ApiModelProperty("章节排序")
    private Integer sort;

    @ApiModelProperty("小结信息")
    private List<VideoVO> children = new ArrayList<>();
}
