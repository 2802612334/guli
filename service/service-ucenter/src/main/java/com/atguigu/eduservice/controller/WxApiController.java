package com.atguigu.eduservice.controller;

import com.atguigu.eduservice.entity.dto.VxUserInfoDTO;
import com.atguigu.eduservice.entity.po.UcenterMember;
import com.atguigu.eduservice.service.UcenterMemberService;
import com.atguigu.eduservice.util.HttpClientUtils;
import com.atguigu.eduservice.util.VxOpenProperties;
import com.atguigu.exception.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import io.swagger.annotations.Api;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * <p>
 * 微信登录 前端控制器
 * </p>
 *
 * @author szf
 * @since 2021-04-13
 */
@Api("微信登录")
@CrossOrigin
@Controller
@RequestMapping("/api/ucenter/wx")
public class WxApiController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @GetMapping("/login")
    public String vxLogin(){
        String baseUrl = "https://open.weixin.qq.com/connect/qrconnect?" +
                "appid=%s&" +
                "redirect_uri=%s" +
                "&response_type=code&" +
                "scope=snsapi_login&" +
                "state=%s#wechat_redirect";

        String redirectUrl = VxOpenProperties.REDIRECT_URL;
        String state = "imhelen";
        try {
            // 对回调地址进行URL编码
            redirectUrl = URLEncoder.encode(redirectUrl,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new GuliException(20001,e.getMessage());
        }

        String url = String.format(baseUrl,VxOpenProperties.APP_ID,redirectUrl,state);
        return "redirect:" + url;
    }

    /***
     *
     * @param code      用户同意第三方应用后，返回路径携带的code参数，用户不同意，不予返回
     * @param state     该state不管是否同意都要返回
     * @return
     */
    @GetMapping("/callback")
    public String callback(String code, String state) {
        // 1.如果code为null，说明用户禁止登录
        if(code.isEmpty()){
            throw new GuliException(20001,"请授权后登录");
        }
        // 2.TODO 判断state是否正确，防止csrf攻击

        // 3.通过code获取access_token
        try{
            String baseUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?" +
                    "appid=%s&" +
                    "secret=%s&" +
                    "code=%s&" +
                    "grant_type=authorization_code";

            baseUrl = String.format(baseUrl, VxOpenProperties.APP_ID,VxOpenProperties.APP_SECRET,code);

            String result = HttpClientUtils.get(baseUrl);

            Gson gson = new Gson();
            HashMap hashMap = gson.fromJson(result, HashMap.class);

            String accessToken = (String)hashMap.get("access_token");
            String openid = (String)hashMap.get("openid");

            // 4.判断当前数据库中是否有openid，如果有则无需添加到数据库
            QueryWrapper<UcenterMember> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("openid",openid);
            UcenterMember member = ucenterMemberService.getOne(queryWrapper);

            if(member == null){
                // 5.获取用户头像和其他信息
                baseUrl = "https://api.weixin.qq.com/sns/userinfo?" +
                        "access_token=%s&" +
                        "openid=%s";

                baseUrl = String.format(baseUrl,accessToken,openid);
                result = HttpClientUtils.get(baseUrl);
                VxUserInfoDTO vxUserInfoDTO = gson.fromJson(result, VxUserInfoDTO.class);
                vxUserInfoDTO.setHeadimgurl(vxUserInfoDTO.getHeadimgurl().replaceAll("\\\\",""));

                BeanUtils.copyProperties(vxUserInfoDTO,member);
                member.setAvatar(vxUserInfoDTO.getHeadimgurl());

                // 6.将用户信息保存到数据库
                ucenterMemberService.save(member);
            }

            return result;
        }catch (Exception e){
            throw new GuliException(20001,e.getMessage());
        }
    }
}
