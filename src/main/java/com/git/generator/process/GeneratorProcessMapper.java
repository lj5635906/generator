package com.git.generator.process;

import com.git.generator.config.GeneratorConfig;
import com.git.generator.constant.GeneratorConstant;
import com.git.generator.domain.EntityProperty;
import com.git.generator.domain.Table;
import com.git.generator.util.GeneratorUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 - Mybatis Mapper
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 11:20
 **/
@Component
public class GeneratorProcessMapper extends AbstractGeneratorProcess {

    /**
     * 生成mapper文件目标目录
     */
    private String TARGET_MAPPER = "mapper/";
    /**
     * Mapper 模板名
     */
    private String TEMPLATE_MAPPER_NAME = "Mapper.ftl";
    /**
     * Mapper.java文件后缀名
     */
    private String JAVA_MAPPER_SUFFIX = "Mapper.java";

    @Override
    protected String getDataAccessType() {
        return GeneratorConfig.dataAccessType;
    }

    @Override
    protected void action(Configuration config, Map<String, Table> table, Map<String, List<EntityProperty>> tableColumn) throws Exception {
        // 生成代码目录
        String target = GeneratorConfig.target + GeneratorConstant.PACKAGE_NAME_MAPPER + GeneratorConstant.FILE_DELIMITER;
        Iterator<Map.Entry<String, List<EntityProperty>>> entities = tableColumn.entrySet().iterator();

        while (entities.hasNext()) {
            Map.Entry<String, List<EntityProperty>> entity = entities.next();

            // 生成目录文件
            String paintingTarget = target + entity.getKey() + JAVA_MAPPER_SUFFIX;
            // 获取生成后的文件
            File file = new File(paintingTarget);
            if (file.exists()) {
                file.delete();
            }
            // 验证是否存在 repository 文件夹，不存在则创建 repository 文件夹
            File entityDir = new File(target);
            if (!entityDir.exists()) {
                entityDir.mkdirs();
            }
            // 设置数据
            Map<String, Object> data = new HashMap<>(20);
            data.put("mapperFullPackageName", GeneratorUtil.getMapperFullClassName(GeneratorConfig.packageName, GeneratorConfig.moduleName));
            data.put("entityFullPackageName",  GeneratorUtil.getEntityFullClassName(GeneratorConfig.packageName, GeneratorConfig.moduleName));
            data.put("entityName", entity.getKey());
            String tableName = entity.getValue().get(0).getTableName();
            data.put("entityComment", table.get(tableName).getComment());
            data.put("entityNameLow", entity.getKey().substring(0, 1).toLowerCase() + entity.getKey().substring(1, entity.getKey().length()));
            for (EntityProperty bean : entity.getValue()) {
                String primaryColumnName = table.get(tableName).getPrimaryColumnName();
                if (StringUtils.equalsIgnoreCase(primaryColumnName, bean.getColumnName())) {
                    // 获取主键数据类型
                    data.put("primaryDataType", bean.getType());
                    break;
                }
            }

            // 获取模板
            Template template = config.getTemplate(TEMPLATE_MAPPER_NAME);
            // 使用FreeMarker构建生成文件
            super.painting(data, template, paintingTarget);
        }
    }

}
