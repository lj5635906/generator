package com.git.generator.util;

import com.git.generator.constant.GeneratorConstant;
import org.apache.commons.lang.StringUtils;


/**
 * 构建生成文件的包名
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-15 11:16
 **/
public class GeneratorUtil {

    /**
     * 构建 service impl 全限定包名
     *
     * @param packageName 包名前缀
     * @param moduleName  模块名
     * @return service impl 全限定包名
     */
    public static String getServiceImplFullClassName(String packageName, String moduleName) {
        if (StringUtils.isEmpty(moduleName)) {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_SERVICE + "." + GeneratorConstant.PACKAGE_NAME_SERVICE_IMPL;
        } else {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_SERVICE + "." + moduleName + "." + GeneratorConstant.PACKAGE_NAME_SERVICE_IMPL;
        }
    }

    /**
     * 构建 entity 全限定包名
     *
     * @param packageName 包名前缀
     * @param moduleName  模块名
     * @return entity 全限定包名
     */
    public static String getEntityFullClassName(String packageName, String moduleName) {
        if (StringUtils.isEmpty(moduleName)) {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_ENTITY;
        } else {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_ENTITY + "." + moduleName;
        }
    }

    /**
     * 构建 mapper 全限定包名
     *
     * @param packageName 包名前缀
     * @param moduleName  模块名
     * @return mapper 全限定包名
     */
    public static String getMapperFullClassName(String packageName, String moduleName) {
        if (StringUtils.isEmpty(moduleName)) {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_MAPPER;
        } else {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_MAPPER + "." + moduleName;
        }
    }


    /**
     * 构建 repository 全限定包名
     *
     * @param packageName 包名前缀
     * @param moduleName  模块名
     * @return repository 全限定包名
     */
    public static String getRepositoryFullClassName(String packageName, String moduleName) {
        if (StringUtils.isEmpty(moduleName)) {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_REPOSITORY;
        } else {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_REPOSITORY + "." + moduleName;
        }
    }

    /**
     * 构建 service 全限定包名
     *
     * @param packageName 包名前缀
     * @param moduleName  模块名
     * @return service 全限定包名
     */
    public static String getServiceFullClassName(String packageName, String moduleName) {
        if (StringUtils.isEmpty(moduleName)) {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_SERVICE;
        } else {
            return packageName + "." + GeneratorConstant.PACKAGE_NAME_SERVICE + "." + moduleName;
        }
    }
}
