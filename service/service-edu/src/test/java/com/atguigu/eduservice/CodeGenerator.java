package com.atguigu.eduservice;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;

import java.util.ArrayList;

public class CodeGenerator {

    @Test
    public void main(){

        // 1.创建代码生成器
        AutoGenerator autoGenerator = new AutoGenerator();

        // 2.全局配置
        GlobalConfig config = new GlobalConfig();
//        String projectPath = System.getProperty("user.dir");
        String projectPath = "D:/JavaIDE/code/guli-parent/service/service-ucenter";
        config.setOutputDir(projectPath + "/src/main/java");
        config.setAuthor("szf");
        config.setOpen(false);
        config.setFileOverride(false);
        config.setServiceName("%sService");
        config.setIdType(IdType.ID_WORKER_STR);
        config.setDateType(DateType.ONLY_DATE);
        config.setSwagger2(true);

        autoGenerator.setGlobalConfig(config);

        // 3.配置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/guli?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8");
        dataSourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("root");
        dataSourceConfig.setDbType(DbType.MYSQL);

        autoGenerator.setDataSource(dataSourceConfig);

        // 4.包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("eduservice");
        pc.setParent("com.atguigu");
        pc.setController("controller");
        pc.setService("service");
        pc.setMapper("mapper");
        autoGenerator.setPackageInfo(pc);


        // 5.策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setInclude("ucenter_member");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        strategy.setEntityLombokModel(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        strategy.setEntityLombokModel(true);

        strategy.setRestControllerStyle(true);  // restful api风格控制器
        strategy.setControllerMappingHyphenStyle(true);     // url中驼峰转链字符

        // 自动填充配置
        TableFill gmtCreate = new TableFill("gmt_create", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("gmt_modified", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategy.setTableFillList(tableFills);

        autoGenerator.setStrategy(strategy);

        // 6.执行
        autoGenerator.execute();
    }
}
