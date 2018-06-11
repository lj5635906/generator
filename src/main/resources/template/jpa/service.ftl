<#if moduleName??>
package ${packageName}.service.${moduleName};
<#else>
package ${packageName}.service;
</#if>

<#if moduleName??>
import ${packageName}.entity.${moduleName}.${entityName};
<#else>
import ${packageName}.entity.${entityName};
</#if>

/**
 * ${entityComment} 业务处理接口
 *
 * @author Roger
 * @email 190642964@qq.com
 * @create ${.now}
 */
public interface ${entityName}Service{

}