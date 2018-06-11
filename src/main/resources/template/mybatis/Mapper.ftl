package com.control.persistence.repository.${moduleName};

import com.control.domain.template.${moduleName}.${entity_name};
import com.control.persistence.core.ControlMapper;
import org.apache.ibatis.annotations.Select;
import com.control.persistence.repository.${moduleName}.${entity_name}Repository;

import java.util.List;
import java.util.Map;

/**
* ${entity_name_remark} 基础支持，该文件添加后不做修改
*
* @author Roger
* @email 190642964@qq.com
* @create ${.now}
*/
public interface ${entity_name}Mapper extends ${entity_name}Repository,ControlMapper<${entity_name}> {

    /**
      * ${entity_name_remark}-通过XML查询数据,参数类型为Map<实体字段名,参数值>
      * @param param Map<String,Object>
      * @return List<${entity_name}>
    */
    List<${entity_name}> select${entity_name}List(Map<String,Object> param);

    /**
      * ${entity_name_remark}-根据主键ID和删除标志查询该记录是否删除
      * @param id 主键标志
      * @return ${entity_name}
    */
    @Select("select * from ${tableName} td where id = ${r"#{id}"} and delTag = 0")
    ${entity_name} select${entity_name}(Long id);

}