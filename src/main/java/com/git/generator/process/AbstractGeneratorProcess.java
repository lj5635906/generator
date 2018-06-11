package com.git.generator.process;

import com.git.generator.config.GeneratorConfig;
import com.git.generator.config.GeneratorConfiguration;
import com.git.generator.constant.GeneratorConstant;
import com.git.generator.domain.EntityBean;
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

    @Autowired
    private GeneratorConfig generatorConfig;

    @Override
    public void generator(Map<String, Table> table, Map<String, List<EntityBean>> tableColumn) throws Exception {
        if (null == tableColumn || tableColumn.size() == 0) {
            return;
        }
        Configuration config = this.getConfiguration();
        this.action(config,table,tableColumn);
    }

    /**
     * 获取 FreeMarker Configuration
     * @return Configuration
     * @throws  Exception Exception
     */
    protected Configuration getConfiguration() throws Exception {
        // 获取 Configuration
        Configuration config = GeneratorConfiguration.create();
        if (GeneratorConstant.DATA_ACCESS_TYPE_JPA.equals(generatorConfig.getDataAccessType())){
            File template = ResourceUtils.getFile(GeneratorConstant.DEFAULT_TEMPLATE_JPA);
            config.setDirectoryForTemplateLoading(template);
        }else {
            File template = ResourceUtils.getFile(GeneratorConstant.DEFAULT_TEMPLATE_MYBATIS);
            config.setDirectoryForTemplateLoading(template);
        }
        config.setDefaultEncoding(GeneratorConstant.DEFAULT_CHARSET);
        return config;
    }

    /**
     * 构建FreeMarker 绘画所需要的数据
     * @param config Configuration
     * @param table Map<String, Table> 所有的表信息
     * @param tableColumn   Map<String, List<EntityBean>> 需要生成的表、列信息
     * @throws Exception Exception
     */
    protected abstract void action(Configuration config,Map<String, Table> table, Map<String, List<EntityBean>> tableColumn) throws Exception;

    protected void painting(Map<String, Object> entityData,Template template,String paintingTarget)throws Exception{
        // 根据模板生成文件
        Writer out = new OutputStreamWriter(new FileOutputStream(paintingTarget), GeneratorConstant.DEFAULT_CHARSET);
        template.process(entityData, out);
    }
}
