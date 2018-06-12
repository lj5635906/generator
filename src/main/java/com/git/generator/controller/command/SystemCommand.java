package com.git.generator.controller.command;

import com.git.generator.constant.GeneratorConstant;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 代码生成器相关配置
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 10:15
 **/
@Data
public class SystemCommand {

    /**
     * 生成代码文件目录
     */
    private String target;
    /**
     * 生成代码包名前缀
     */
    private String packageName;
    /**
     * 生成代码模块名
     */
    private String moduleName;
    /**
     * 需要跳过字段前缀的数目
     */
    private int skipNumFiled = 0;
    /**
     * 需要跳过表名前缀的数目
     */
    private int skipNumTable = 0;
    /**
     * 持久层框架
     */
    private String dataAccessType = GeneratorConstant.DATA_ACCESS_TYPE_JPA;

}
