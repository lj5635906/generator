package com.git.generator.process;

import com.git.generator.config.GeneratorConfig;
import com.git.generator.domain.EntityBean;
import com.git.generator.domain.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 - 生成实体类
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 11:20
 **/
@Component
public class GeneratorProcessRepository extends AbstractGeneratorProcess {

    @Autowired
    private GeneratorConfig generatorConfig;

    /**
     * 生成entity文件目标目录
     */
    private String TARGET_REPOSITORY = "repository/";
    /**
     * entity 模板名
     */
    private String TEMPLATE_REPOSITORY_NAME = "Repository.ftl";
    /**
     * Repository.java文件后缀名
     */
    String JAVA_REPOSITORY_SUFFIX = "Repository.java";

    @Override
    protected void action(Configuration config, Map<String, Table> table, Map<String, List<EntityBean>> tableColumn) throws Exception {
        // 生成代码目录
        String target = generatorConfig.getTarget() + TARGET_REPOSITORY;
        Iterator<Map.Entry<String, List<EntityBean>>> entities = tableColumn.entrySet().iterator();

        while (entities.hasNext()) {
            Map.Entry<String, List<EntityBean>> entity = entities.next();

            // 获取生成后的文件
            File file = new File(target + entity.getKey() + JAVA_REPOSITORY_SUFFIX);
            if (file.exists()) {
                file.delete();
            }
            // 验证是否存在 repository 文件夹，不存在则创建 repository 文件夹
            File entityDir = new File(target);
            if (!entityDir.exists()) {
                entityDir.mkdirs();
            }
            // 设置数据
            Map<String, Object> data = new HashMap<>(7);
            data.put("packageName", generatorConfig.getPackageName());
            data.put("moduleName", generatorConfig.getModuleName());
            data.put("entityName", entity.getKey());
            String tableName = entity.getValue().get(0).getTableName();
            data.put("entityComment", table.get(tableName).getComment());
            data.put("tableName", tableName);
            for (EntityBean bean : entity.getValue()) {
                String primaryColumnName = table.get(tableName).getPrimaryColumnName();
                if (StringUtils.equalsIgnoreCase(primaryColumnName, bean.getColumnName())) {
                    // 获取主键数据类型
                    data.put("primaryDataType", bean.getType());
                    break;
                }
            }

            // 生成目录文件
            String paintingTarget = target + entity.getKey() + JAVA_REPOSITORY_SUFFIX;
            // 获取模板
            Template template = config.getTemplate(TEMPLATE_REPOSITORY_NAME);
            // 使用FreeMarker构建生成文件
            super.painting(data,template,paintingTarget);
        }
    }

}