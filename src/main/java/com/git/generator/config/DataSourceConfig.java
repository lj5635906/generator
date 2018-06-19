package com.git.generator.config;

import com.git.generator.constant.DbType;
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
    private DataSourceConfig(){}
    /**
     * 数据库类型
     */
    public static String dbType = DbType.MySql.getDbType();
    /**
     * 数据库地址
     */
    public static String host = "localhost";
    /**
     * 端口
     */
    public static int port = 3306;
    /**
     * 数据库名称
     */
    public static String databaseName;
    public static String username;
    public static String password;
}
