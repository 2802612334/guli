package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@ApiModel("小结对象")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoVO implements Serializable {

    @ApiModelProperty("小结id")
    private String id;

    @ApiModelProperty("章节id")
    private String chapterId;

    @ApiModelProperty("小结名称")
    private String title;

    @ApiModelProperty("小结排序")
    private Integer sort;

    @ApiModelProperty(value = "是否可以试听：0收费 1免费")
    private Boolean isFree;

}
