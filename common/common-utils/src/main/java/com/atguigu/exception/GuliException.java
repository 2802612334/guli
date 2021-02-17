package com.atguigu.exception;

import com.atguigu.commonutils.ResultCode;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GuliException extends RuntimeException {

    @ApiModelProperty("状态码")
    private Integer code;

    @ApiModelProperty("异常信息")
    private String message;

}
