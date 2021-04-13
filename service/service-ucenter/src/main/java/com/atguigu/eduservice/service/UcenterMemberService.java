package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.po.UcenterMember;
import com.atguigu.eduservice.entity.vo.LoginVO;
import com.atguigu.eduservice.entity.vo.RegisterVO;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author szf
 * @since 2021-04-11
 */
public interface UcenterMemberService extends IService<UcenterMember> {

    String memberLogin(LoginVO loginVO);

    void memberRegister(RegisterVO registerVO);
}
