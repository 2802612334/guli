package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.po.CrmBanner;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 首页banner表 服务类
 * </p>
 *
 * @author szf
 * @since 2021-03-31
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> selectFrontBanner(IPage<CrmBanner> page);
}
