package com.git.generator.config;

import freemarker.template.Configuration;

/**
 * 创建FreeMarker Configuration
 * 不需要重复创建 Configuration 实例； 它的代价很高，尤其是会丢失缓存。Configuration 实例就是应用级别的单例。
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 11:34
 **/
public class GeneratorConfiguration {

    private static class SingletonInstance{
        private static Configuration instance = new Configuration(Configuration.VERSION_2_3_28);
    }

    private GeneratorConfiguration(){
    }
    public static Configuration create(){
        return SingletonInstance.instance;
    }

}
