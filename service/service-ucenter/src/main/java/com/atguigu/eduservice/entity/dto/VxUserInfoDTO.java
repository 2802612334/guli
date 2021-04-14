package com.atguigu.eduservice.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VxUserInfoDTO {

    private String openid;

    private String nickname;

    // 1为男性，2为女性
    private Integer sex;

    private String province;

    private String city;

    private String country;

    private String headimgurl;

    // 用户特权信息
    private HashMap privilege;

    private String unionid;
}
