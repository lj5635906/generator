package com.git.generator.process;

import com.git.generator.domain.EntityBean;
import com.git.generator.domain.Table;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器接口
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 11:10
 **/
public interface Generator {

    /**
     * 代码生成接口
     *
     * @param table       表信息
     * @param tableColumn 列信息
     * @throws Exception Exception
     */
    void generator(Map<String, Table> table, Map<String, List<EntityBean>> tableColumn) throws Exception ;

}
