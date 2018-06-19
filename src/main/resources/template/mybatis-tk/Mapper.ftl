package ${mapperFullPackageName};

import ${entityFullPackageName}.${entityName};
import tk.mybatis.mapper.common.Mapper;

/**
 * ${entityComment} 基础支持
 *
 * @author Roger
 * @email 190642964@qq.com
 * @create ${.now}
 */
@org.apache.ibatis.annotations.Mapper
public interface ${entityName}Mapper extends Mapper<${entityName}> {

}