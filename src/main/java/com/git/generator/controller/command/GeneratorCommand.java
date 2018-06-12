package com.git.generator.controller.command;

import lombok.Data;

import java.util.List;

/**
 * 生成代码请求参数
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-12 18:15
 **/
@Data
public class GeneratorCommand {

    /**
     * 需要创建的表
     */
    private List<String> tableNames;
    /**
     * 需要生成的模块
     */
    private List<String> modules;
}
