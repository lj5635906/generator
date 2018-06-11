<#if moduleName??>
package ${packageName}.repository.${moduleName};
<#else>
package ${packageName}.repository;
</#if>

<#if moduleName??>
import ${packageName}.entity.${moduleName}.${entityName};
<#else>
import ${packageName}.entity.${entityName};
</#if>
import org.springframework.data.jpa.repository.JpaRepository;

/**
* ${entityComment}
*
* @author Roger
* @email 190642964@qq.com
* @create ${.now}
*/
public interface ${entityName}Repository extends JpaRepository<${entityName},${primaryDataType}> {

}