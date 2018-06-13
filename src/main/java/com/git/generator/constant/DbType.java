package com.git.generator.constant;

/**
 * 数据库类型、驱动名
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2018-06-13 13:16
 **/
public enum DbType {
    MySql("MySql","com.mysql.jdbc.Driver"),
    Oracle("Oracle","oracle.jdbc.driver.OracleDriver"),
    SQLServer("SQLServer","com.microsoft.sqlserver.jdbc.SQLServerDriver")
    ;

    DbType(String dbType, String driverClassName) {
        this.dbType = dbType;
        this.driverClassName = driverClassName;
    }

    private String dbType;
    private String driverClassName;

    public String getDbType() {
        return dbType;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

}
