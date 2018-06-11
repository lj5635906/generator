package com.git.generator.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 数据库连接信息
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 14:37
 **/
@Data
@ConfigurationProperties(prefix = "generator.datasource")
@Configuration
public class DataSourceConfig {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
