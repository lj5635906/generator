package com.git.generator.domain;

import lombok.Data;

/**
 * 代码生成器 - 表信息
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-08-19 10:23
 **/
@Data
public class EntityBean {
	/**
	 * 主键名
	 */
	private String primaryName;
	/**
	 * 该列是否为自增
	 */
	private Boolean autoincrement;
	/**
	 * 访问权限 ---private、public
	 */
	private String accessAuth;
	/**
	 * 字段 数据类型
	 */
	private String type;
	/**
	 * 实体名字
	 */
	private String entityName;
	/**
	 * 属性名称(首字母小写)
	 */
	private String propertyName;
	/**
	 * 属性名称(首字母大写)
	 */
	private String propertyNameUP;
	/**
	 * 表名称
	 */
	private String tableName;
	/**
	 * 字段名称
	 */
	private String columnName;
	/**
	 * 字段注释
	 */
	private String comment;

}