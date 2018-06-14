<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.control.persistence.infrastructure.${moduleName}.${entity_name}Mapper">
	<resultMap type="com.control.domain.template.${moduleName}.${entity_name}" id="${entity_name_low}ResultMap">
	<#list beans as bean>
	  <#if bean.propertyName == bean.keyName>
		<id column="${bean.filedName}" property="${bean.propertyName}" />
	  <#else>
	    <result column="${bean.filedName}" property="${bean.propertyName}" />
	  </#if>
	</#list>
	</resultMap>

    <select id="select${entity_name}List" resultMap="${entity_name_low}ResultMap" parameterType="map">
        SELECT
			*
        FROM
			${tableName}
        <where>
<#list beans as bean>
			<#if "createDate"=="${bean.propertyName}">
            <if test="null != startCreateDate">
                AND createDate >= ${r"#{startCreateDate}"}
            </if>
			<if test="null != endCreateDate">
				AND createDate <![CDATA[ <= ]]> ${r"#{endCreateDate}"}
			</if>
			<#elseif "updateDate"=="${bean.propertyName}">
			<if test="null != startUpdateDate">
				AND updateDate >= ${r"#{startUpdateDate}"}
			</if>
			<if test="null != endUpdateDate">
				AND updateDate <![CDATA[ <= ]]> ${r"#{endUpdateDate}"}
			</if>
			<#else>
			<if test="null != ${bean.propertyName}">
				AND ${bean.filedName} = ${"#{"+bean.propertyName+"}"}
			</if>
			</#if>
</#list>
        </where>
    </select>
</mapper>