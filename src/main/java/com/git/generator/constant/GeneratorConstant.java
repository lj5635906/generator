package com.git.generator.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * generator 常量
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 11:44
 **/
public interface GeneratorConstant {

    /**
     * 默认编码格式
     */
    String DEFAULT_CHARSET = "UTF-8";
    /**
     * 持久层框架 - jpa
     */
    String DATA_ACCESS_TYPE_JPA = "jpa";
    /**
     * 持久层框架 - mybatis
     */
    String DATA_ACCESS_TYPE_MYBATIS = "mybatis";
    /**
     * 持久层框架 - mybatis 通用 mapper
     */
    String DATA_ACCESS_TYPE_MYBATIS_TK_MAPPER = "mybatis-tk";
    /**
     * freemarker 模板文件地址 - JPA
     */
    String DEFAULT_TEMPLATE_JPA = "classpath:template\\jpa";
    /**
     * freemarker 模板文件地址 - MYBATIS
     */
    String DEFAULT_TEMPLATE_MYBATIS = "classpath:template\\mybatis";
    /**
     * freemarker 模板文件地址 - MYBATIS  通用 mapper
     */
    String DEFAULT_TEMPLATE_MYBATIS_TK_MAPPER = "classpath:template\\mybatis-tk";

    /**
     * 文件夹分割符
     */
    String FILE_DELIMITER = "/";

    /**
     * entity 包名
     */
    String PACKAGE_NAME_ENTITY = "domain";
    /**
     * mapper 包名
     */
    String PACKAGE_NAME_MAPPER = "mapper";
    /**
     * Repository 包名
     */
    String PACKAGE_NAME_REPOSITORY = "repository";
    /**
     * service 包名
     */
    String PACKAGE_NAME_SERVICE = "service";
    /**
     * service impl 包名
     */
    String PACKAGE_NAME_SERVICE_IMPL = "impl";
    /**
     * Mybatis 能够生成的模块
     */
    List<String> GENERATOR_MODULE_MYBATIS = new ArrayList<String>() {{
        this.add("Entity");
        this.add("Service");
        this.add("ServiceImpl");
        this.add("Mapper");
        this.add("MapperXml");
    }};
    /**
     * JPA 能够生成的模块
     */
    List<String> GENERATOR_MODULE_JPA = new ArrayList<String>() {{
        this.add("Entity");
        this.add("Repository");
        this.add("Service");
        this.add("ServiceImpl");
    }};
}
