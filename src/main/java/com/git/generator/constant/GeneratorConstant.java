package com.git.generator.constant;

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
     * freemarker 模板文件地址 - JPA
     */
    String DEFAULT_TEMPLATE_JPA = "classpath:template\\jpa";
    /**
     * freemarker 模板文件地址 - MYBATIS
     */
    String DEFAULT_TEMPLATE_MYBATIS = "classpath:template\\mybatis";

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
}
