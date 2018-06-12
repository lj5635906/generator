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
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
