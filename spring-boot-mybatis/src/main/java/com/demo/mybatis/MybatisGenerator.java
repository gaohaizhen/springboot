package com.demo.mybatis;

import java.io.File;

import org.mybatis.generator.api.GeneratedJavaFile;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

public class MybatisGenerator {

    public static void main(String[] args) throws Exception{
        List<String> warnings = new ArrayList<String>();

        File configFile = new File("E:\\code_demo\\spring-demo\\spring-boot-mybatis\\src\\main\\java\\com\\demo\\mybatis\\generatorConfig.xml");

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(configFile);
        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        for(GeneratedJavaFile file:myBatisGenerator.getGeneratedJavaFiles()){
            System.out.println(file.getTargetPackage());
            System.out.println(file.getFileName());

        }

    }


}
