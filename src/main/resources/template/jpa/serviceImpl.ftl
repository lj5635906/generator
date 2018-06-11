<#if moduleName??>
package ${packageName}.service.${moduleName}.impl;
<#else>
package ${packageName}.service.impl;
</#if>

<#if moduleName??>
import ${packageName}.entity.${moduleName}.${entityName};
<#else>
import ${packageName}.entity.${entityName};
</#if>
<#if moduleName??>
import ${packageName}.repository.${moduleName}.${entityName}Repository;
<#else>
import ${packageName}.repository.${entityName}Repository;
</#if>
<#if moduleName??>
import ${packageName}.service.${moduleName}.${entityName}Service;
<#else>
import ${packageName}.service.${entityName}Service;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ${entityComment} 业务处理实现
 *
 * @author Roger
 * @email 190642964@qq.com
 * @create ${.now}
 */
@Service
public class ${entityName}ServiceImpl implements ${entityName}Service{

    @Autowired
    private ${entityName}Repository ${entityNameLow}Repository;

}