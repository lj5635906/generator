package ${entityFullPackageName};

import com.control.common.core.util.DateTimeUtils;
import com.control.common.core.util.UUIDUtils;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

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
	${bean.accessAuth} ${bean.type} ${bean.propertyName};
	<#else>

    /**
     * ${bean.comment}
     */
	${bean.accessAuth} ${bean.type} ${bean.propertyName};
	</#if>
</#list>

    /**
     * 初始化数据
     */
    public void auto(){
        this.setId(UUIDUtils.generateUUID());
        this.setCreateDateTime(DateTimeUtils.getNow());
        this.setUpdateDateTime(DateTimeUtils.getNow());
        this.setDeleteFlag(0);
        this.setVersion(0);
    }

}