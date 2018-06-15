package ${mapperFullPackageName};

import ${entityFullPackageName}.${entityName};
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${entityComment} 基础支持，该文件添加后不做修改
 *
 * @author Roger
 * @email 190642964@qq.com
 * @create ${.now}
 */
@Mapper
public interface ${entityName}Mapper {

    /**
     * 新增数据
     *
     * @param ${entityNameLow} ${entityName}
     * @return 数据库影响行数
     */
    int insert(${entityName} ${entityNameLow});

    /**
     * 新增数据并回写主键
     *
     * @param ${entityNameLow} ${entityName}
     * @return 数据库影响行数
     */
    int insertUseGeneratedKeys(${entityName} ${entityNameLow});

    /**
     * 新增数据(字段不为空) 并回写主键
     *
     * @param ${entityNameLow} ${entityName}
     * @return 数据库影响行数
     */
    int insertSelective(${entityName} ${entityNameLow});

    /**
     * 根据主键删除数据
     *
     * @param primaryKey 主键
     * @return 数据库影响行数
     */
    int deleteByPrimaryKey(${primaryDataType} primaryKey);

    /**
     * 根据 ${entityName} 删除数据
     * @param ${entityNameLow} ${entityName}
     * @return 数据库影响行数
     */
    int delete(${entityName} ${entityNameLow});

    /**
     * 根据 ${entityName} 主键 修改其所有内容
     *
     * @param ${entityNameLow} ${entityName}
     * @return 数据库影响行数
     */
    int updateByPrimaryKey(${entityName} ${entityNameLow});

    /**
     * 根据 ${entityName} 主键 修改其(字段不为空)内容
     *
     * @param ${entityNameLow} ${entityName}
     * @return 数据库影响行数
     */
    int updateByPrimaryKeySelective(${entityName} ${entityNameLow});

    /**
     * 根据 ${entityName} 统计数据库中的行数
     *
     * @param ${entityNameLow} ${entityName}
     * @return count值
     */
    int selectCount(${entityName} ${entityNameLow});

    /**
     * 根据 主键 获取 ${entityName}
     *
     * @param primaryKey 主键
     * @return ${entityName}
     */
    ${entityName} selectByPrimaryKey(${primaryDataType} primaryKey);

    /**
     * 根据 ${entityName} 获取一个集合
     *
     * @param ${entityNameLow} ${entityName}
     * @return List<${entityName}>
     */
    List<${entityName}> select(${entityName} ${entityNameLow});
}