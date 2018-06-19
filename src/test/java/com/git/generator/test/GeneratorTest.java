package com.git.generator.test;

import com.git.generator.GeneratorApplication;
import com.git.generator.config.DataSourceConfig;
import com.git.generator.config.GeneratorConfig;
import com.git.generator.constant.DbType;
import com.git.generator.constant.GeneratorConstant;
import com.git.generator.service.GeneratorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

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
            DataSourceConfig.host = "localhost";
            DataSourceConfig.port = 3306;
            DataSourceConfig.databaseName = "home";
            DataSourceConfig.username = "root";
            DataSourceConfig.password="123456";
            DataSourceConfig.dbType = DbType.MySql.getDbType();

            // # 数据访问层使用框架 jpa、mybatis
            GeneratorConfig.dataAccessType = GeneratorConstant.DATA_ACCESS_TYPE_JPA;
            // # 生成代码模块名
            GeneratorConfig.moduleName = null;
            // # 生成代码包名前缀
            GeneratorConfig.packageName = "com.example";
            // # 需要跳过表名前缀的数目
            GeneratorConfig.SKIP_NUM_TABLE = 1;
            // # 需要跳过字段前缀的数目
            GeneratorConfig.SKIP_NUM_FILED = 0;
            // # 生成代码文件目录
            GeneratorConfig.target = "F:\\home\\code\\";

            List<String> tableNames = new ArrayList<String>() {{
                this.add("home_customer");
                this.add("home_building_position");
            }};

            // tableNames 为 null时，生成数据库所有数据表
            generatorService.generator(null, GeneratorConstant.GENERATOR_MODULE_JPA);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
