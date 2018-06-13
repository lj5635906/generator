package com.git.generator.controller;

import com.git.generator.config.DataSourceConfig;
import com.git.generator.config.GeneratorConfig;
import com.git.generator.constant.GeneratorConstant;
import com.git.generator.controller.command.DataSourceCommand;
import com.git.generator.controller.command.GeneratorCommand;
import com.git.generator.controller.command.SystemCommand;
import com.git.generator.controller.vo.ResultVo;
import com.git.generator.domain.Table;
import com.git.generator.handler.GeneratorDataBaseHandler;
import com.git.generator.service.GeneratorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 代码生成器控制层
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-12 10:01
 **/
@RestController
public class GeneratorController {

    @Autowired
    private GeneratorDataBaseHandler generatorDataBaseHandler;
    @Autowired
    private GeneratorService generatorService;

    /**
     * 验证数据源是否正确
     *
     * @param command 数据源
     */
    @PostMapping("/set-datasource")
    public ResultVo connection(@RequestBody DataSourceCommand command) {
        DataSourceConfig config = new DataSourceConfig();
        BeanUtils.copyProperties(command, config);
        GeneratorDataBaseHandler.dataSourceConfig = config;
        try {
            generatorDataBaseHandler.getConnection(generatorDataBaseHandler.getUrl());
            return ResultVo.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return ResultVo.fail();
        }
    }

    /**
     * 设置系统参数
     *
     * @param command 系统参数
     */
    @PostMapping("/set-system")
    public ResultVo setSystem(@RequestBody SystemCommand command) {
        GeneratorConfig.dataAccessType = command.getDataAccessType();
        GeneratorConfig.moduleName = command.getModuleName();
        GeneratorConfig.packageName = command.getPackageName();
        GeneratorConfig.SKIP_NUM_TABLE = command.getSkipNumTable();
        GeneratorConfig.SKIP_NUM_FILED = command.getSkipNumFiled();
        GeneratorConfig.target = command.getTarget();
        return ResultVo.ok();
    }

    /**
     * 获取当前数据访问层框架 允许生成的模块
     */
    @GetMapping("/modules")
    public ResultVo getModules() {
        if (GeneratorConstant.DATA_ACCESS_TYPE_JPA.equals(GeneratorConfig.dataAccessType)) {
            List<String> module = new ArrayList<String>() {{
                this.add("Entity");
                this.add("Repository");
                this.add("Service");
                this.add("ServiceImpl");
            }};
            return ResultVo.ok(module);
        } else if (GeneratorConstant.DATA_ACCESS_TYPE_MYBATIS.equals(GeneratorConfig.dataAccessType)) {
            List<String> module = new ArrayList<String>() {{
                this.add("Entity");
                this.add("Repository");
                this.add("Service");
                this.add("ServiceImpl");
                this.add("Mapper");
                this.add("Xml");
            }};
            return ResultVo.ok(module);
        } else {

        }
        return ResultVo.ok();
    }

    /**
     * 获取数据库所有表信息
     */
    @GetMapping("/tables")
    public ResultVo index() throws Exception {
        Map<String, Table> tableInfo = generatorDataBaseHandler.getAllTable();
        List<Table> allTables = new ArrayList<>();

        Iterator<Map.Entry<String, Table>> tables = tableInfo.entrySet().iterator();
        while (tables.hasNext()) {
            Map.Entry<String, Table> table = tables.next();
            // 表名
            String tableName = table.getKey();
            allTables.add(tableInfo.get(tableName));
        }

        // 根据表名排序
        List<Table> sorted = allTables.stream().sorted(Comparator.comparing(Table::getName)).collect(Collectors.toList());

        return ResultVo.ok(sorted);
    }

    /**
     * 生成代码
     */
    @PostMapping("/generator")
    public ResultVo generator(@RequestBody GeneratorCommand command) throws Exception {
        generatorService.generator(command.getTableNames(), command.getModules());
        return ResultVo.ok();
    }

}