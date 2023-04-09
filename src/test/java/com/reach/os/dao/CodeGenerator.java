package com.reach.os.dao;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;

/**
 * @Description
 * @Date 2022/5/3 14:38
 * @Author Rookie
 */
@Slf4j
public class CodeGenerator {
    public static void main(String[] args) {
        //1、data source config
        FastAutoGenerator.create("jdbc:mysql://8.222.217.128:3306/reach?useUnicode=true&characterEncoding=utf-8&generateSimpleParameterMetadata=true&useSSL=false",
                        "reach", "CoiLxJi3-9")
                //2、global config
                .globalConfig(builder -> {
                    builder.author("rookie") // set author
                            .outputDir("/Users/zt/IdeaProjects/ReachOS" + "/src/main/java/Mp")   //set output path
                            .commentDate("yyyy-MM-dd hh:mm:ss")   //set date
                            .dateType(DateType.ONLY_DATE)   // TIME_PACK=LocalDateTime;ONLY_DATE=Date;
                            .fileOverride()   //override historical file
                            .enableSwagger()   //enable swagger
                            .disableOpenDir();   //disable open output path,default open
                })
                //3、package config
                .packageConfig(builder -> {
                    builder.parent("org") // set parent package name
                            .moduleName("ReachOS")   //set module name
                            .entity("entity")   //pojo entity package name
                            .service("service") //Service package name
                            .serviceImpl("serviceImpl") // ***ServiceImpl package name
                            .mapper("mappers/workflow")   //Mapper package name
                            .xml("mappers/workflow")  //Mapper XML package name
                            .controller("controller") //Controller package name
                            .other("utils") //custom package name
                            .pathInfo(Collections.singletonMap(OutputFile.xml, "/Users/zt/IdeaProjects/ReachOS" +"/src/main/resources/mapper"));    //set config **Mapper.xml path info： resources Mapper folder
                })
                //4、strategy .addInclude( "define_workflow","define_workflow_event","define_event_reward","define_event_condition_task","define_event_condition_share","define_event_condition_ir","define_event") // set table name
                .strategyConfig(builder -> {
                    builder.addInclude("t_user","t_organization","t_resource","t_idea_topic","t_idea_reply") // set table need to scan and generate
                            .addTablePrefix("t_", "c_") // set filter to table prefix

                            //4.1、Mapper strategy config
                            .mapperBuilder()
                            .superClass(BaseMapper.class)   //set parent class
                            .formatMapperFileName("%sMapper")   //format mapper file name
                            .enableMapperAnnotation()       //enable @Mapper annotation
                            .formatXmlFileName("%sXml") //format Xml file name

                            //4.2、service
                            .serviceBuilder()
                            .formatServiceFileName("%sService") //格式化 service 接口文件名称，%s进行匹配表名，如 UserService
                            .formatServiceImplFileName("%sServiceImpl") //格式化 service 实现类文件名称，%s进行匹配表名，如 UserServiceImpl

                            //4.3、entity
                            .entityBuilder()
                            .enableLombok() //enable Lombok
                            .disableSerialVersionUID()  //disable Serializable implement，do not generate SerialVersionUID
                            .logicDeleteColumnName("deleted")   //logic delete property name
                            .naming(NamingStrategy.underline_to_camel)  //table columns to entity properties use underline to camel
                            .columnNaming(NamingStrategy.underline_to_camel)    //table columns to entity properties use underline to camel
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("modify_time", FieldFill.INSERT_UPDATE)
                            )
                            .enableTableFieldAnnotation()       // enable set column comment to properties when generate entity

                            //4.4、Controller
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle(); //@RestController

                })
                //5、template engine config
                .templateEngine(new VelocityTemplateEngine())	//default
                /*
                .templateEngine(new FreemarkerTemplateEngine())
                .templateEngine(new BeetlTemplateEngine())
                */
                //6、execute
                .execute();
        log.info("Execution finished");
    }
}
