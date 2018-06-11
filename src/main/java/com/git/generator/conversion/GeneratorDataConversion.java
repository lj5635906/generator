package com.git.generator.conversion;

import com.git.generator.config.GeneratorConfig;
import com.git.generator.domain.Column;
import com.git.generator.domain.EntityBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 代码生成器 - 数据转化
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-08-19 10:23
 **/
@Component
public class GeneratorDataConversion {

    @Autowired
    private GeneratorConfig generatorConfig;

    /**
     * 主键名称
     */
    private static final String PRIMARY_NAME = "id";
    /**
     * 字段访问权限,默认为 private
     */
    private static final String ACCESS_AUTH = "private";

    /**
     * 将数据库查询出的数据转换为模板生产所需要的数据
     *
     * @return 转换后的数据集合，key实体名，value实体信息
     */
    public Map<String, List<EntityBean>> conversion(Map<String, List<Column>> dbData) {
        Map<String, List<EntityBean>> data = new HashMap<>(10);
        if (null == dbData || dbData.size() == 0) {
            return data;
        }

        Iterator<Map.Entry<String, List<Column>>> tables = dbData.entrySet().iterator();

        while (tables.hasNext()) {
            Map.Entry<String, List<Column>> table = tables.next();
            // 表名
            String tableName = table.getKey();
            // 实体名称
            String entityName = getEntityName(tableName);
            // 当前表所有字段信息
            List<Column> columns = table.getValue();
            // 当前表所有字段信息转换后的EntityBean
            List<EntityBean> beans = new ArrayList<>();
            for (Column column : columns){
                if(PRIMARY_NAME.equalsIgnoreCase(column.getName())){
                    column.setName(PRIMARY_NAME);
                }
                EntityBean bean = new EntityBean();
                bean.setComment(column.getComment());
                bean.setEntityName(entityName);
                bean.setAutoincrement(column.getAutoincrement());
                // 访问权限
                bean.setAccessAuth(ACCESS_AUTH);
                bean.setColumnName(column.getName());
                bean.setType(column.getDataType());
                bean.setPropertyName(getPropertyName(column.getName()));
                bean.setPropertyNameUP(getPropertyNameUp(column.getName()));
                // 主键名称
                bean.setPrimaryName(PRIMARY_NAME);
                bean.setTableName(tableName);
                beans.add(bean);
            }
            data.put(entityName,beans);
        }
        return data;
    }

    /**
     * 根据字段名获取属性名
     * @param filedName 字段名(fd_userName)
     * @return 属性名(userName)
     */
    private String getPropertyName(String filedName){
        StringBuffer propertyName = new StringBuffer();
        String[] names = filedName.split("_");
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            if (i==0){
                propertyName.append(name.substring(0, 1).toLowerCase() + name.substring(1, name.length()));
            }else {
                propertyName.append(name.substring(0, 1).toUpperCase() + name.substring(1, name.length()));
            }
        }
        return propertyName.toString();
    }

    /**
     * 根据字段名获取属性名
     * @param filedName 字段名(fd_userName)
     * @return 属性名(userName)
     */
    private String getPropertyNameUp(String filedName){
        StringBuffer properNameUp = new StringBuffer();
        String propertyName = getPropertyName(filedName);
        properNameUp.append(propertyName.substring(0, 1).toUpperCase());
        properNameUp.append(propertyName.substring(1, propertyName.length()));
        return properNameUp.toString();
    }

    /**
     * 根据表名获取实体名称
     *
     * @param tableName 表名(sys_admin_role)
     * @return 实体名称(AdminRole)
     */
    private String getEntityName(String tableName) {
        StringBuffer entityName = new StringBuffer();
        String[] names = tableName.split("_");
        int skipNum = generatorConfig.getSkipNum();
        for (int i = 0; i < names.length; i++) {
            if (i < skipNum) {
                // 跳过表名前缀
                continue;
            }
            String name = names[i];
            /*** 生成实体名字 **/
            entityName.append(name.substring(0, 1).toUpperCase());
            entityName.append(name.substring(1, name.length()));
        }
        return entityName.toString();
    }


}
