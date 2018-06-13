package com.git.generator.config;

import lombok.Data;

/**
 * 数据源
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-12 11:47
 **/
@Data
public class DataSourceConfig {
    /**
     * 数据库类型
     */
    private String dbType;
    /**
     * 数据库地址
     */
    private String host;
    /**
     * 端口
     */
    private int port;
    /**
     * 数据库名称
     */
    private String databaseName;
    private String username;
    private String password;
}
