package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.po.CrmBanner;
import com.atguigu.eduservice.mapper.CrmBannerMapper;
import com.atguigu.eduservice.service.CrmBannerService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务实现类
 * </p>
 *
 * @author szf
 * @since 2021-03-31
 */
@Service
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBanner> implements CrmBannerService {

    @Override
    @Cacheable(
            value = "banner",
            key = "'selectFrontBanner'"
    )
    public List<CrmBanner> selectFrontBanner(IPage<CrmBanner> page) {
        // 1.按id字段升序排列
        QueryWrapper<CrmBanner> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByAsc("id");

        // 2.分页查询
        IPage<CrmBanner> crmBannerIPage = this.baseMapper.selectPage(page, queryWrapper);
        return crmBannerIPage.getRecords();
    }
}
