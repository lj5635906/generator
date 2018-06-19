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

</mapper>