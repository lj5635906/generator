<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperFullPackageName}.${entityName}Mapper">
    <resultMap type="${entityFullPackageName}.${entityName}" id="${entityNameLow}ResultMap">
	<#list beans as bean>
	  <#if bean.propertyName == bean.primaryName>
		<id column="${bean.columnName}" property="${bean.propertyName}" />
	  <#else>
	    <result column="${bean.columnName}" property="${bean.propertyName}" />
	  </#if>
	</#list>
	</resultMap>

    <sql id="${entityNameLow}Columns">
		<#list beans as bean>
			<#if bean_index == beans?size - 1>
		${bean.columnName}
			<#else>
		${bean.columnName},
			</#if>
		</#list>
    </sql>

    <sql id="${entityNameLow}Property">
		<#list beans as bean>
			<#if bean_index == beans?size - 1>
		${"#{"+bean.propertyName+"}"}
			<#else>
		${"#{"+bean.propertyName+"}"},
			</#if>
		</#list>
    </sql>

    <sql id="${entityNameLow}SelectiveColumns">
		<#list beans as bean>
			<#if bean_index == beans?size - 1>
		<if test="${bean.propertyName} != null">
			${bean.columnName}
		</if>
			<#else>
		<if test="${bean.propertyName} != null">
			${bean.columnName},
		</if>
			</#if>
		</#list>
    </sql>

    <sql id="${entityNameLow}SelectiveProperty">
		<#list beans as bean>
			<#if bean_index == beans?size - 1>
		<if test="${bean.propertyName} != null">
			${"#{"+bean.propertyName+"}"}
		</if>
			<#else>
		<if test="${bean.propertyName} != null">
			${"#{"+bean.propertyName+"}"},
		</if>
			</#if>
		</#list>
    </sql>

    <sql id="${entityNameLow}SelectiveWhere">
        <where>
			<#list beans as bean>
			<if test="${bean.propertyName} != null">
				AND ${bean.columnName} = ${"#{"+bean.propertyName+"}"}
			</if>
			</#list>
        </where>
    </sql>

    <sql id="${entityNameLow}UpdateSet">
        <trim prefix="SET" suffixOverrides=",">
		<#list beans as bean>
            ${bean.columnName} = ${"#{"+bean.propertyName+"}"},
		</#list>
        </trim>
    </sql>

    <sql id="${entityNameLow}SelectiveUpdateSet">
        <trim prefix="SET" suffixOverrides=",">
		<#list beans as bean>
            <if test="${bean.propertyName} != null">
                ${bean.columnName} = ${"#{"+bean.propertyName+"}"},
            </if>
		</#list>
        </trim>
    </sql>

    <insert id="insert" parameterType="${entityFullPackageName}.${entityName}">
        INSERT INTO ${tableName} (
        <include refid="${entityNameLow}Columns"></include>
        ) VALUES (
        <include refid="${entityNameLow}Property"></include>
        )
    </insert>

    <insert id="insertUseGeneratedKeys" parameterType="${entityFullPackageName}.${entityName}" keyProperty="${primaryPropertyName}"
            useGeneratedKeys="true">
        INSERT INTO ${tableName} (
        <include refid="${entityNameLow}Columns"></include>
        ) VALUES (
        <include refid="${entityNameLow}Property"></include>
        )
    </insert>

    <insert id="insertSelective" parameterType="${entityFullPackageName}.${entityName}" keyProperty="${primaryPropertyName}"
			useGeneratedKeys="true">
        INSERT INTO ${tableName} (
        <include refid="${entityNameLow}SelectiveColumns"></include>
        ) VALUES (
        <include refid="${entityNameLow}SelectiveProperty"></include>
        )
    </insert>

    <delete id="deleteByPrimaryKey">
        DELETE FROM ${tableName} WHERE ${primaryColumnName} = ${"#{"+primaryPropertyName+"}"}
    </delete>

    <delete id="delete" parameterType="${entityFullPackageName}.${entityName}">
        DELETE FROM ${tableName}
        <include refid="${entityNameLow}SelectiveWhere"></include>
    </delete>

    <update id="updateByPrimaryKey" parameterType="${entityFullPackageName}.${entityName}">
        UPDATE ${tableName}
        <include refid="${entityNameLow}UpdateSet"></include>
        WHERE ${primaryColumnName} = ${"#{"+primaryPropertyName+"}"}
    </update>

    <update id="updateByPrimaryKeySelective" parameterType="${entityFullPackageName}.${entityName}">
        UPDATE ${tableName}
        <include refid="${entityNameLow}SelectiveUpdateSet"></include>
        WHERE ${primaryColumnName} = ${"#{"+primaryPropertyName+"}"}
    </update>

    <select id="selectCount" resultType="int">
        SELECT
        COUNT(1)
        FROM ${tableName}
        <include refid="${entityNameLow}SelectiveWhere"></include>
    </select>

    <select id="selectByPrimaryKey" resultMap="${entityNameLow}ResultMap">
        SELECT
        <include refid="${entityNameLow}Columns"></include>
        FROM ${tableName}
        WHERE ${primaryColumnName} = ${"#{"+primaryPropertyName+"}"}
    </select>

    <select id="select" resultMap="${entityNameLow}ResultMap" parameterType="${entityFullPackageName}.${entityName}">
        SELECT
        <include refid="${entityNameLow}Columns"></include>
        FROM ${tableName}
        <include refid="${entityNameLow}SelectiveWhere"></include>
    </select>
</mapper>