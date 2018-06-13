package com.git.generator.process;

import com.git.generator.config.GeneratorConfig;
import com.git.generator.config.GeneratorConfiguration;
import com.git.generator.constant.GeneratorConstant;
import com.git.generator.domain.EntityProperty;
import com.git.generator.domain.Table;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器处理器
 * 根据模板设计模式
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 19:07
 **/
public abstract class AbstractGeneratorProcess implements Generator{

    @Override
    public void generator(Map<String, Table> table, Map<String, List<EntityProperty>> tableColumn) throws Exception {
        if (null == tableColumn || tableColumn.size() == 0) {
            return;
        }
        Configuration config = this.getConfiguration();
        this.action(config,table,tableColumn);
    }

    /**
     * 获取 持久层框架 类型
     * @return 持久层框架 类型
     */
    protected abstract String getDataAccessType();

    /**
     * 获取 FreeMarker Configuration
     * @return Configuration
     * @throws  Exception Exception
     */
    protected Configuration getConfiguration() throws Exception {
        // 获取 Configuration
        Configuration config = GeneratorConfiguration.create();
        if (GeneratorConstant.DATA_ACCESS_TYPE_JPA.equals(getDataAccessType())){
            File template = ResourceUtils.getFile(GeneratorConstant.DEFAULT_TEMPLATE_JPA);
            config.setDirectoryForTemplateLoading(template);
        }else if (GeneratorConstant.DATA_ACCESS_TYPE_MYBATIS.equals(getDataAccessType())){
            File template = ResourceUtils.getFile(GeneratorConstant.DEFAULT_TEMPLATE_MYBATIS);
            config.setDirectoryForTemplateLoading(template);
        }else {
            throw new Exception("当前持久层框架【"+getDataAccessType()+"】待开发");
        }
        config.setDefaultEncoding(GeneratorConstant.DEFAULT_CHARSET);
        return config;
    }

    /**
     * 构建 FreeMarker 绘画所需要的数据
     * @param config Configuration
     * @param table Map<String, Table> 所有的表信息
     * @param tableColumn   Map<String, List<EntityBean>> 需要生成的表、列信息
     * @throws Exception Exception
     */
    protected abstract void action(Configuration config,Map<String, Table> table, Map<String, List<EntityProperty>> tableColumn) throws Exception;

    /**
     * 根据数据构建需要生成的文件
     * @param entityData Map<String, Object> 所有数据
     * @param template Template
     * @param paintingTarget 构建文件目标地址
     * @throws Exception
     */
    protected void painting(Map<String, Object> entityData,Template template,String paintingTarget)throws Exception{
        // 根据模板生成文件
        Writer out = new OutputStreamWriter(new FileOutputStream(paintingTarget), GeneratorConstant.DEFAULT_CHARSET);
        template.process(entityData, out);
        out.flush();
        out.close();
    }
}
