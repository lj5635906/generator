package com.git.generator.test;

import com.git.generator.GeneratorApplication;
import com.git.generator.conversion.GeneratorDataConversion;
import com.git.generator.domain.Column;
import com.git.generator.domain.EntityBean;
import com.git.generator.domain.Table;
import com.git.generator.handler.GeneratorDataBaseHandler;
import com.git.generator.process.Generator;
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
    private GeneratorDataBaseHandler generatorDataBaseHandler;

    @Autowired
    private GeneratorDataConversion generatorDataConversion;

    @Autowired
    private Generator generatorEntity;
    @Autowired
    private Generator generatorRepository;
    @Autowired
    private Generator generatorService;
    @Autowired
    private Generator generatorServiceImpl;

    @Test
    public void getTableInfo() {
        try {
            Map<String, Table> tableMap = generatorDataBaseHandler.getAllTable();
            Map<String, List<Column>> data = generatorDataBaseHandler.getAllTableDetailByTableNames(new ArrayList<String>() {{
                this.add("home_customer");
            }});

            Map<String, List<EntityBean>> beans = generatorDataConversion.conversion(data);

            generatorEntity.generator(tableMap,beans);
            generatorRepository.generator(tableMap,beans);
            generatorService.generator(tableMap,beans);
            generatorServiceImpl.generator(tableMap,beans);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
