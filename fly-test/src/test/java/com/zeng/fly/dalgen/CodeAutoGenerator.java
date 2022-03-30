package com.zeng.fly.dalgen;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

/**
 * 代码生成工具
 *
 * @author: 画雲
 * @date: 2021/5/18 11:34
 * @version: V1.0
 **/
public class CodeAutoGenerator {

    public static void main(String[] args) {
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        // 是否支持AR模式
        gc.setActiveRecord(false);
        // 作者
        gc.setAuthor("zengxingdeng");
        String projectPath = System.getProperty("user.dir") + "/fly-dal/src/main/java";
        // 生成的路径
        gc.setOutputDir(projectPath);
        // 是否覆盖同名文件，默认是false
        gc.setFileOverride(true);
        // 主键策略：自增
        gc.setIdType(IdType.AUTO);
        // XML 二级缓存
        gc.setEnableCache(false);
        // XML ResultMap
        gc.setBaseResultMap(true);
        // 生成Sql片段
        gc.setBaseColumnList(true);
        /* 自定义文件命名，注意 %s 会自动填充表实体属性！*/
        gc.setMapperName("%sMapper");
        gc.setXmlName("%s");
        gc.setServiceName("%sDomainService");
        gc.setServiceImplName("%sDomainServiceImpl");
//        gc.setControllerName("%sController");

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("as1516116");
        dsc.setUrl("jdbc:mysql://139.9.101.120:3306/yzsy_goods?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai&useSSL=false&autoReconnect=true");

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        // 全局大写命名
        strategy.setCapitalMode(true);
        // 修改表前缀
        strategy.setTablePrefix("system_");
        // 表名生成策略(下划线转驼峰)
        strategy.setNaming(NamingStrategy.underline_to_camel);
        // 需要生成的表
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        // 排除生成的表
        // strategy.setExclude(new String[]{"test"});
        // strategy.setChainModel(true);
        // strategy.setEntityLombokModel(true);

        String packages = scanner("模块名");

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.zeng.fly.dal");
        pc.setMapper(packages+".mapper");
        pc.setEntity(packages+".entity");
        pc.setXml(packages+".mapper");
//        pc.setController(packages+".dao");
        pc.setService(packages+".domain");
        pc.setServiceImpl(packages+".domain.impl");

        // 自定义模板
        TemplateConfig template = new TemplateConfig();
//        template.setController("/static/template/controller.vm");
        template.setService("/static/template/service.vm");
        template.setServiceImpl("/static/template/serviceImpl.vm");
        template.setMapper("/static/template/mapper.vm");
        template.setEntity("/static/template/entity.vm");
        // template.setXml("/static/template/xml.vm");
        // template.setEntityKt("/static/template/entityDTO.vm");

        // 设置整合
        AutoGenerator mpg = new AutoGenerator();
        // 使用Veloctiy模板
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.setPackageInfo(pc);
        mpg.setStrategy(strategy);
        mpg.setDataSource(dsc);
        mpg.setGlobalConfig(gc);
        mpg.setTemplate(template);
        // 执行生成
        mpg.execute();
    }

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help);
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }
}
