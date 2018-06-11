package com.control.service.template.${moduleName};

import com.control.core.enums.code.SystemCode;
import com.control.core.enums.multi.DelFlag;
import com.control.core.exception.CustomException;
import com.control.domain.template.${moduleName}.${entity_name};
import com.control.persistence.infrastructure.${moduleName}.${entity_name}Mapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ${entity_name_remark} 业务处理 模板
 *
 * @author Roger
 * @email 190642964@qq.com
 * @create ${.now}
*/
@Component
public class ${entity_name}Service{

    @Autowired
    private ${entity_name}Mapper ${entity_name_low}Mapper;

    /**
     * 保存数据
     *
     * @param record ${entity_name}
    */
    public void save(${entity_name} record) {
        record.setCreateDate(new Date());
        record.setDelTag(DelFlag.no.getCode());
        ${entity_name_low}Mapper.insertUseGeneratedKeys(record);
    }

    /**
     * 删除数据
     *
     * @param ids ID集合
    */
    public void deletes(Long[] ids) {
        for (Long id : ids) {
            ${entity_name} ${entity_name_low} = ${entity_name_low}Mapper.select${entity_name}(id);
            if (null != ${entity_name_low}) {
                ${entity_name_low}Mapper.updateByPrimaryKeySelective(new ${entity_name}(id, DelFlag.yes.getCode(), new Date()));
            }
        }
    }

    /**
     * 修改数据
     *
     * @param record ${entity_name}
    */
    public void editById(${entity_name} record) {
        ${entity_name} ${entity_name_low} = ${entity_name_low}Mapper.select${entity_name}(record.getId());
        if (null != ${entity_name_low}) {
            record.setUpdateDate(new Date());
            ${entity_name_low}Mapper.updateByPrimaryKeySelective(record);
        } else {
            throw new CustomException(SystemCode.record_not_exist.getCode(), SystemCode.record_not_exist.getMessage());
        }
    }

    /**
     * 根据ID获取详情,不存在返回null
     *
     * @param id 主键ID
     * @return ${entity_name}
    */
    public ${entity_name} findOne(Long id) {
        return ${entity_name_low}Mapper.selectByPrimaryKey(id);
    }

    /**
     * 根据ID获取详情,不存在抛出异常[code=1000,msg=记录不存在]
     *
     * @param id 主键ID
     * @return ${entity_name}
    */
    public ${entity_name} findOneExist(Long id) {
        ${entity_name} ${entity_name_low} = ${entity_name_low}Mapper.selectByPrimaryKey(id);
        if (null != ${entity_name_low}) {
            return ${entity_name_low};
        } else {
            throw new CustomException(SystemCode.record_not_exist.getCode(), SystemCode.record_not_exist.getMessage());
        }
    }

    /**
     * 分页查询数据
     *
     * @param page 分页信息
     * @return PageInfo<${entity_name}>
    */
    public PageInfo<${entity_name}> page(Page<${entity_name}> page, Map<String, Object> param) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<${entity_name}> data = ${entity_name_low}Mapper.select${entity_name}List(param);
        PageInfo<${entity_name}> pageInfo = new PageInfo<>(data);
        return pageInfo;
    }

}