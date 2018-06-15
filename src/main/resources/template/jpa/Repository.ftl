package ${repositoryFullPackageName};

import ${entityFullPackageName}.${entityName};
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