package com.git.generator.controller.command;

import lombok.Data;

/**
 * 设置数据源
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-12 11:26
 **/
@Data
public class DataSourceCommand {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
