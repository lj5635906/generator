package com.git.generator.process;

import com.git.generator.config.GeneratorConfig;
import com.git.generator.domain.EntityProperty;
import com.git.generator.domain.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
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
public class GeneratorProcessService extends AbstractGeneratorProcess {

    /**
     * 生成entity文件目标目录
     */
    private String TARGET_SERVICE = "service/";
    /**
     * entity 模板名
     */
    private String TEMPLATE_SERVICE_NAME = "Service.ftl";
    /**
     * Repository.java文件后缀名
     */
    private String JAVA_SERVICE_SUFFIX = "Service.java";

    @Override
    protected String getDataAccessType() {
        return GeneratorConfig.dataAccessType;
    }

    @Override
    protected void action(Configuration config, Map<String, Table> table, Map<String, List<EntityProperty>> tableColumn) throws Exception {
        // 生成代码目录
        String target = GeneratorConfig.target + TARGET_SERVICE;
        Iterator<Map.Entry<String, List<EntityProperty>>> entities = tableColumn.entrySet().iterator();

        while (entities.hasNext()) {
            Map.Entry<String, List<EntityProperty>> entity = entities.next();

            // 生成目录文件
            String paintingTarget = target + entity.getKey() + JAVA_SERVICE_SUFFIX;
            // 获取生成后的文件
            File file = new File(paintingTarget);
            if (file.exists()) {
                file.delete();
            }
            // 验证是否存在 service 文件夹，不存在则创建 service 文件夹
            File dir = new File(target);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // 设置数据
            Map<String, Object> data = new HashMap<>(20);
            data.put("packageName", GeneratorConfig.packageName);
            data.put("moduleName", GeneratorConfig.moduleName);
            data.put("entityName", entity.getKey());
            String tableName = entity.getValue().get(0).getTableName();
            data.put("entityComment", table.get(tableName).getComment());

            // 获取模板
            Template template = config.getTemplate(TEMPLATE_SERVICE_NAME);
            // 使用FreeMarker构建生成文件
            super.painting(data,template,paintingTarget);
        }
    }

}
