package ${entityFullPackageName};

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * ${entityComment} 实体类
 *
 * @author Roger
 * @email 190642964@qq.com
 * @create ${.now}
 */
@Data
@Entity
@Table(name = "${tableName}")
public class ${entityName} {

<#list beans as bean>
	<#if "id"=="${bean.propertyName}">
	/**
	 * ${bean.comment}
	 */
    @Id
        <#if bean.autoincrement==true >
    @GeneratedValue(strategy = GenerationType.IDENTITY)
        </#if>
	${bean.accessAuth} ${bean.type} ${bean.propertyName};
	<#else>

    /**
     * ${bean.comment}
     */
	${bean.accessAuth} ${bean.type} ${bean.propertyName};
	</#if>
</#list>
}