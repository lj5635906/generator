package com.git.generator.service;

import com.git.generator.conversion.GeneratorDataConversion;
import com.git.generator.domain.Column;
import com.git.generator.domain.EntityProperty;
import com.git.generator.domain.Table;
import com.git.generator.handler.GeneratorDataBaseHandler;
import com.git.generator.handler.GeneratorProcessHandler;
import com.git.generator.process.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * 代码生成业务控制
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-12 18:42
 **/
@Service
public class GeneratorService {

    @Autowired
    private GeneratorProcessHandler generatorProcessHandler;
    @Autowired
    private GeneratorDataBaseHandler generatorDataBaseHandler;

    /**
     * 控制代码生成业务
     *
     * @param tableNames 需要生成的表
     * @param modules    需要生成的模块
     */
    public void generator(List<String> tableNames, List<String> modules) throws Exception {

        // 获取数据库所有表信息
        Map<String, Table> tableMap = generatorDataBaseHandler.getAllTable();

        // 获取需要生成表的信息
        Map<String, List<Column>> data = null;
        if (CollectionUtils.isEmpty(tableNames)){
            data = generatorDataBaseHandler.getAllTableDetail();
        }else {
            data = generatorDataBaseHandler.getAllTableDetailByTableNames(tableNames);
        }

        // 转换 FreeMarker 模板需要的数据
        Map<String, List<EntityProperty>> beans = GeneratorDataConversion.conversion(data);

        for (String module : modules){
            Generator generator = generatorProcessHandler.findGenerator(module);
            generator.generator(tableMap,beans);
        }
    }

}
