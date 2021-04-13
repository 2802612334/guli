package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.po.UcenterMember;
import com.atguigu.eduservice.entity.vo.LoginVO;
import com.atguigu.eduservice.entity.vo.RegisterVO;
import com.atguigu.eduservice.mapper.UcenterMemberMapper;
import com.atguigu.eduservice.service.UcenterMemberService;
import com.atguigu.exception.GuliException;
import com.atguigu.utils.JwtUtils;
import com.atguigu.utils.MD5;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author szf
 * @since 2021-04-11
 */
@Service
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMember> implements UcenterMemberService {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /***
     *
     * @param loginVO   登录实体对象
     * @return          token值
     */
    @Override
    public String memberLogin(LoginVO loginVO) {
        // 1.校验参数是否为空
        String mobile = loginVO.getMobile();
        String password = loginVO.getPassword();
        if(mobile.isEmpty() || password.isEmpty()){
            throw new GuliException(20001,"登录错误！");
        }
        // 2.获取会员
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        UcenterMember ucenterMember = this.baseMapper.selectOne(queryWrapper);
        if(ucenterMember == null){
            throw new GuliException(20001,"用户不存在！");
        }
        // 3.检验密码和是否被禁用
        if(!MD5.encrypt(password).equals(ucenterMember.getPassword())){
            throw new GuliException(20001,"输入密码错误！");
        }
        if(ucenterMember.getIsDisabled() == false){
            throw new GuliException(20001,"用户已被禁用！");
        }
        // 4.使用jwt生成token字符串
        return JwtUtils.getJwtToken(ucenterMember.getId(),ucenterMember.getNickname());
    }

    @Override
    public void memberRegister(RegisterVO registerVO) {
        // 1.校验参数是否为空
        String mobile = registerVO.getMobile();
        String nickname = registerVO.getNickname();
        String password = registerVO.getPassword();
        String code = registerVO.getCode();

        if(mobile.isEmpty() || nickname.isEmpty() || password.isEmpty() || code.isEmpty()){
            throw new GuliException(20001,"注册信息输入错误！");
        }
        // 2.校验验证码
        String realCode = (String)redisTemplate.opsForValue().get("");
        if(!code.equals(realCode)){
            throw new GuliException(20001,"验证码输入错误！");
        }
        // 3.校验是否具有相同的手机号
        QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("mobile",mobile);
        Integer count = this.baseMapper.selectCount(queryWrapper);
        if(count > 0){
            throw new GuliException(20001,"该手机号已被注册！");
        }
        // 4.将注册信息添加到数据库
        UcenterMember ucenterMember = new UcenterMember();
        BeanUtils.copyProperties(registerVO,ucenterMember);
        this.baseMapper.insert(ucenterMember);
    }
}
