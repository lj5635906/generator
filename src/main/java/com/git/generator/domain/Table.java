package com.git.generator.domain;

import lombok.Data;

/**
 * 表信息
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 10:27
 **/
@Data
public class Table {
    /**
     * 表名
     */
    private String name;

    /**
     * 表注释
     */
    private String comment;

    /**
     * 主键列名
     */
    private String primaryColumnName;

}
