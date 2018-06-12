package com.git.generator.handler;

import com.git.generator.process.Generator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 生成器模块控制器
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-12 18:32
 **/
@Component
public class GeneratorProcessHandler {

    /**
     * 拼装处理器字符串
     */
    private static final String PROCESS = "Process";

    /**
     * 获取容器中所有 Generator 的实现
     */
    @Autowired
    private Map<String, Generator> generator;

    /**
     * 根据模块名称获取 代码生成处理器
     *
     * @param module 模块名称
     * @return Generator
     */
    public Generator findGenerator(String module) throws Exception {
        String generatorSimpleName = Generator.class.getSimpleName();
        String name = generatorSimpleName.substring(0, 1).toLowerCase() + generatorSimpleName.substring(1, generatorSimpleName.length()) + PROCESS + module;
        Generator generatorProcessModule = generator.get(name);
        if (null == generatorProcessModule) {
            throw new Exception("生成代码【" + name + "】模块待开发");
        }
        return generatorProcessModule;
    }

}
