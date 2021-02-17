package com.atguigu.commonutils;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class Result {

    @ApiModelProperty(value = "请求是否成功")
    private Boolean success;

    @ApiModelProperty(value = "返回码")
    private Integer code;

    @ApiModelProperty(value = "返回消息")
    private String message;

    @ApiModelProperty(value = "返回数据")
    private Map<String,Object> data = new HashMap<>();

    // 构造方法私有，只能通过该类的静态方法创建实例对象
    private Result(){}

    // 链式编程
    public static Result ok(){
        Result result = new Result();
        result.setSuccess(true);
        result.setMessage("成功");
        result.setCode(ResultCode.SUCCESS);
        return result;
    }

    public static Result error(){
        Result result = new Result();
        result.setSuccess(false);
        result.setMessage("失败");
        result.setCode(ResultCode.FEILID);
        return result;
    }

    public Result code(Integer code){
        this.setCode(code);
        return this;
    }

    public Result message(String message){
        this.setMessage(message);
        return this;
    }

    public Result data(String key,Object value){
        this.getData().put(key,value);
        return this;
    }

    public Result data(Map<String,Object> data){
        this.setData(data);
        return this;
    }

}
