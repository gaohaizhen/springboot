package com.demo.springbootmybatis;

/**
 * 生成mybatis文件
 *
 */
public class GenMain {
    // private static Logger log = LoggerFactory.getLogger(GenMain.class);
	
	public static void main(String[] args) {
        String configFile = "generatorConfig.xml";
        try {

            String[] tableNames = new String[] {"admin"};
            GenMybatisFiles.gen(configFile, tableNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //
        // if(log.isDebugEnabled()){
        // log.debug("debug");
        // }
	}

}
