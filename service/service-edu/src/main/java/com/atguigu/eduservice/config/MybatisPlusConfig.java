package com.atguigu.eduservice.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Date;

@Configuration
@MapperScan("com.atguigu.eduservice.mapper")
@EnableTransactionManagement
public class MybatisPlusConfig {


    /*
    * 分页配置
    * */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }

    /*
    * 逻辑删除
    * */
    @Bean
    public ISqlInjector sqlInjector(){
        return new LogicSqlInjector();
    }

    /*
    * 自动填充
    * */
    @Bean
    public MetaObjectHandler fieldFillHandler(){
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setFieldValByName("gmtCreate",new Date(),metaObject);
                this.setFieldValByName("gmtModified",new Date(),metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setFieldValByName("gmtModified",new Date(),metaObject);
            }
        };
    }


    /*
    * SQL 执行性能分析插件
    * 开发环境使用，线上不推荐,设置 dev test 环境开启，保证我们的效率
    * */
    @Bean
    @Profile({"dev","test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000); // ms设置sql执行的最大时执行
        performanceInterceptor.setFormat(true); // 是否格式化代码
        return performanceInterceptor;
    }
}
