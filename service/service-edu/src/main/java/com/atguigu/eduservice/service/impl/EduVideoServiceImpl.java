package com.atguigu.eduservice.service.impl;

import com.atguigu.commonutils.Result;
import com.atguigu.eduservice.client.VodFeignClient;
import com.atguigu.eduservice.entity.po.EduVideo;
import com.atguigu.eduservice.mapper.EduVideoMapper;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.exception.GuliException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author szf
 * @since 2021-02-23
 */
@Service
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideo> implements EduVideoService {

    @Autowired
//    @Qualifier(value = "vodFeignClient")
    private VodFeignClient vodFeignClient;

    @Override
    public void removeVideo(String videoId) {
        // 1.查询该小结对应视频信息
        EduVideo eduVideo = this.baseMapper.selectById(videoId);
        // 2.获取对应视频id，并调用远程接口删除视频
        String videoSourceId = eduVideo.getVideoSourceId();
        if(videoSourceId != null){
            List<String> temp = new ArrayList<>();
            temp.add(videoSourceId);
            Result result = vodFeignClient.removeVideo(temp);
            if(result.getCode() == 20001){
                throw new GuliException(result.getCode(),result.getMessage());
            }
        }
        // 3.删除之后，删除小结信息
        this.baseMapper.deleteById(eduVideo);
    }
}
