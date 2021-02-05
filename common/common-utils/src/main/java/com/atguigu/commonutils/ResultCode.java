package com.atguigu.commonutils;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(20000),
    FEILED(20001);

    private Integer code;
}
