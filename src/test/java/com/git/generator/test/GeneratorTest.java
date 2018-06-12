package com.git.generator.test;

import com.git.generator.GeneratorApplication;
import com.git.generator.config.DataSourceConfig;
import com.git.generator.config.GeneratorConfig;
import com.git.generator.conversion.GeneratorDataConversion;
import com.git.generator.domain.Column;
import com.git.generator.domain.EntityProperty;
import com.git.generator.domain.Table;
import com.git.generator.handler.GeneratorDataBaseHandler;
import com.git.generator.process.Generator;
import com.git.generator.service.GeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 测试
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 15:05
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GeneratorApplication.class)
public class GeneratorTest {

    @Autowired
    private GeneratorService generatorService;

    @Test
    public void getTableInfo() {
        try {
            DataSourceConfig config = new DataSourceConfig();
            config.setUrl("jdbc:mysql://www.luojie.site:3306/home?Unicode=true&characterEncoding=UTF-8");
            config.setDriverClassName("com.mysql.jdbc.Driver");
            config.setUsername("root");
            config.setPassword("");
            GeneratorDataBaseHandler.dataSourceConfig = config;

            // # 数据访问层使用框架 jpa、mybatis
            GeneratorConfig.dataAccessType = "jpa";
            // # 生成代码模块名
            GeneratorConfig.moduleName = null;
            // # 生成代码包名前缀
            GeneratorConfig.packageName = "com.home.customer.server";
            // # 需要跳过表名前缀的数目
            GeneratorConfig.SKIP_NUM_TABLE = 1;
            // # 需要跳过字段前缀的数目
            GeneratorConfig.SKIP_NUM_FILED = 0;
            // # 生成代码文件目录
            GeneratorConfig.target = "F:/home/code/";

            List<String> tableNames = new ArrayList<String>() {{
                this.add("home_building");
                this.add("home_building_position");
                this.add("home_customer");
            }};

            List<String> modules = new ArrayList<String>() {{
                this.add("Entity");
                this.add("Repository");
            }};

            generatorService.generator(tableNames, modules);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
