//package com.jbgz.pancakejob.utils;
//
//
//import com.baomidou.mybatisplus.generator.FastAutoGenerator;
//import com.baomidou.mybatisplus.generator.config.OutputFile;
//
//import java.util.Collections;
//
///**
// * mp代码生成
// * bu lmq
// * copied from qinggege
// * @since 2022-12-30
// * */
//public class CodeGenerator {
//    public static void main(String[] args){
//
//    }
//    private static void generator(){
//        FastAutoGenerator.create("jdbc:mysql://gz-cynosdbmysql-grp-17pikoep.sql.tencentcdb.com:22639/pancake_job", "HELLO", "JBGZ")
//                .globalConfig(builder -> {
//                    builder.author("lmq") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
//                            .fileOverride() // 覆盖已生成文件
//                            .outputDir("D:\\software-engineering\\demo-springboot\\pancakejob\\src\\main\\java"); // 指定输出目录
//                })
//                .packageConfig(builder -> {
//                    builder.parent("com.jbgz.pancakejob") // 设置父包名
//                            .moduleName("") // 设置父包模块名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml, "D:\\software-engineering\\demo-springboot\\pancakejob\\src\\main\\resources\\mapper\\")); // 设置mapperXml生成路径
//                })
//                .strategyConfig(builder -> {
//                    builder.addInclude("pancake") // 设置需要生成的表名
//                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
//                })
////                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
//                .execute();
//    }
//}
