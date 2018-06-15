package ${serviceImplFullPackageName};

import ${entityFullPackageName}.${entityName};
import ${repositoryFullPackageName}.${entityName}Repository;
import ${serviceFullPackageName}.${entityName}Service;

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