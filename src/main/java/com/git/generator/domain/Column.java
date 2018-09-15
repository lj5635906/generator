package com.git.generator.domain;

import lombok.Data;

import java.sql.Types;

/**
 * 列信息
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-11 10:29
 **/
@Data
public class Column {

    /**
     * 列名
     */
    private String name;

    /**
     * 该列是否为自增
     */
    private Boolean autoincrement;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 字段注释
     */
    private String comment;

    public void setAutoincrement(String autoincrement) {
        if ("YES".equalsIgnoreCase(autoincrement)) {
            this.autoincrement = true;
        } else {
            this.autoincrement = false;
        }
    }

    public void setDataType(String dataType) {
        // REAL
        switch (Integer.parseInt(dataType)) {
            case Types.SMALLINT:
            case Types.TINYINT:
            case Types.INTEGER:
                this.dataType = "Integer";
                break;
            case Types.BIGINT:
                this.dataType = "Long";
                break;
            case Types.FLOAT:
                this.dataType = "Float";
                break;
            case Types.DOUBLE:
                this.dataType = "Double";
                break;
            case Types.NUMERIC:
            case Types.DECIMAL:
                this.dataType = "BigDecimal";
                break;
            case Types.BOOLEAN:
            case Types.BIT:
                this.dataType = "Boolean";
                break;
            case Types.CHAR:
            case Types.VARCHAR:
            case Types.LONGVARCHAR:
            case Types.NVARCHAR:
            case Types.LONGNVARCHAR:
                this.dataType = "String";
                break;
            case Types.DATE:
            case Types.TIMESTAMP:
            case Types.TIME:
                this.dataType = "LocalDateTime";
                break;
            case Types.BINARY:
            case Types.VARBINARY:
            case Types.LONGVARBINARY:
                this.dataType="byte[]";
                break;
            default:
                this.dataType="custom";
        }
    }
}
