package com.atguigu.execl;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDemo {

    @ExcelProperty(value = "学号",index = 0)
    private Integer id;

    @ExcelProperty(value = "姓名",index = 1)
    private String name;
}
