package com.git.generator.handler;

import com.git.generator.config.DataSourceConfig;
import com.git.generator.constant.DbType;
import com.git.generator.domain.Column;
import com.git.generator.domain.Table;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成器 - 解析数据库表信息处理程序
 *
 * @author roger
 * @email 190642964@qq.com
 * @create 2017-08-19 10:23
 **/
@Component
public class GeneratorDataBaseHandler {

    /**
     * 获取数据库连接
     *
     * @return Connection
     * @throws Exception Exception
     */
    public Connection getConnection(String url) throws Exception {
        Class.forName(getDriverClassName());
        // 获取链接
        return DriverManager.getConnection(url, DataSourceConfig.username, DataSourceConfig.password);
    }

    /**
     * 获取数据库连接
     *
     * @param url url
     * @return Connection
     * @throws Exception Exception
     */
    public Connection getConnectionUseInformationSchema(String url) throws Exception {
        if (StringUtils.equalsIgnoreCase(DbType.MySql.getDbType(), DataSourceConfig.dbType)) {
            return getConnection(url + "&useInformationSchema=true");
        } else {
            throw new Exception("当前数据库类型【" + DataSourceConfig.dbType + "】 读取表信息 待开发");
        }
    }

    /**
     * 获取数据库中所有表信息
     *
     * @return Map<String, Table> <表名,表信息>
     */
    public Map<String, Table> getAllTable() throws Exception {
        Map<String, Table> map = new HashMap<>(10);
        Connection connection = null;
        ResultSet resultSet = null;
        ResultSet primaryResultSet = null;
        try {
            connection = getConnectionUseInformationSchema(getUrl());
            //  获取一个 DatabaseMetaData 对象，该对象包含关于此 Connection 对象所连接的数据库的元数据。
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // 获取当前表字段信息
            resultSet = databaseMetaData.getTables(null, "%", "%", new String[]{"TABLE"});
            while (resultSet.next()) {
                Table table = new Table();
                String remarks = resultSet.getString("REMARKS");
                String tableName = resultSet.getString("TABLE_NAME");

                // 获取主键
                primaryResultSet = databaseMetaData.getPrimaryKeys(null, null, tableName);
                while (primaryResultSet.next()) {
                    // 主键列名
                    String primaryColumnName = primaryResultSet.getString("COLUMN_NAME");
                    table.setPrimaryColumnName(primaryColumnName);
                }

                table.setName(tableName);
                table.setComment(remarks);

                map.put(tableName, table);
            }
            return map;
        } finally {
            closeResultSet(primaryResultSet);
            closeResultSet(resultSet);
            closeConnection(connection);
        }
    }

    /**
     * 获取数据库所有表的详细信息，包括表的所有列
     *
     * @return key->表名，value，表的所有字段信息
     */
    public Map<String, List<Column>> getAllTableDetail() throws Exception {
        Map<String, List<Column>> map = new HashMap<>(20);
        Connection connection = null;
        ResultSet allTableResultSet = null;
        ResultSet tableResultSet = null;
        try {
            connection = getConnection(getUrl());
            // 获取此 Connection 对象的当前目录名称
            String currentCatalog = connection.getCatalog();
            //  获取一个 DatabaseMetaData 对象，该对象包含关于此 Connection 对象所连接的数据库的元数据。
            DatabaseMetaData databaseMetaData = connection.getMetaData();
            // 获取当前数据库所有表信息
            allTableResultSet = databaseMetaData.getTables(currentCatalog, null, null, new String[]{"TABLE"});

            while (allTableResultSet.next()) {
                // 获取表名
                String tableName = allTableResultSet.getString("TABLE_NAME");
                // 获取当前表所有列信息
                tableResultSet = databaseMetaData.getColumns(currentCatalog, null, tableName, null);
                // 解析列
                List<Column> columns = getColumns(tableResultSet);
                map.put(tableName, columns);
            }
            return map;
        } finally {
            closeResultSet(tableResultSet);
            closeResultSet(allTableResultSet);
            closeConnection(connection);
        }
    }

    /**
     * 根据传入表名集合  获取数据库所有表的详细信息，包括表的所有列
     *
     * @return key->表名，value，表的所有字段信息
     */
    public Map<String, List<Column>> getAllTableDetailByTableNames(List<String> tableNames) throws Exception {
        Map<String, List<Column>> map = new HashMap<>(10);
        if (null == tableNames || tableNames.size() == 0) {
            return map;
        }
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            // 获取链接
            connection = getConnection(getUrl());
            // 获取此 Connection 对象的当前目录名称
            String currentCatalog = connection.getCatalog();
            //  获取一个 DatabaseMetaData 对象，该对象包含关于此 Connection 对象所连接的数据库的元数据。
            DatabaseMetaData databaseMetaData = connection.getMetaData();

            for (String tableName : tableNames) {
                // 获取当前表字段信息
                resultSet = databaseMetaData.getColumns(currentCatalog, null, tableName, null);
                // 解析列
                List<Column> columns = getColumns(resultSet);
                map.put(tableName, columns);
            }
            return map;
        } finally {
            closeResultSet(resultSet);
            closeConnection(connection);
        }
    }

    /**
     * 解析每张表所有列的结果集
     *
     * @param resultSet 当前表的结果集
     * @return 解析后的结果集
     * @throws SQLException
     */
    public static List<Column> getColumns(ResultSet resultSet) throws SQLException {
        List<Column> list = new ArrayList<>();
        if (null == resultSet) {
            return list;
        }
        while (resultSet.next()) {
            Column column = new Column();
            column.setName(resultSet.getString("COLUMN_NAME"));
            column.setDataType(resultSet.getString("DATA_TYPE"));
            column.setAutoincrement(resultSet.getString("IS_AUTOINCREMENT"));
            column.setComment(resultSet.getString("REMARKS"));
            list.add(column);
        }
        return list;
    }

    /**
     * 关闭连接 ResultSet
     *
     * @param resultSet ResultSet
     */
    public void closeResultSet(ResultSet resultSet) {
        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 关闭连接 Connection
     *
     * @param connection Connection
     */
    public void closeConnection(Connection connection) {
        if (null != connection) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 获取驱动类名
     *
     * @return 驱动类名
     */
    public String getDriverClassName() throws Exception {
        if (StringUtils.equalsIgnoreCase(DbType.MySql.getDbType(), DataSourceConfig.dbType)) {
            return DbType.MySql.getDriverClassName();
        } else if (StringUtils.equalsIgnoreCase(DbType.Oracle.getDbType(), DataSourceConfig.dbType)) {
            return DbType.Oracle.getDriverClassName();
        } else if (StringUtils.equalsIgnoreCase(DbType.SQLServer.getDbType(), DataSourceConfig.dbType)) {
            return DbType.SQLServer.getDriverClassName();
        } else {
            throw new Exception("当前数据库类型【" + DataSourceConfig.dbType + "】待开发");
        }
    }

    /**
     * 获取数据库连接地址
     *
     * @return url
     */
    public String getUrl() throws Exception {
        if (StringUtils.equalsIgnoreCase(DbType.MySql.getDbType(), DataSourceConfig.dbType)) {
            return "jdbc:mysql://" + DataSourceConfig.host + ":" + DataSourceConfig.port + "/" + DataSourceConfig.databaseName + "?Unicode=true&characterEncoding=UTF-8";
        } else if (StringUtils.equalsIgnoreCase(DbType.Oracle.getDbType(), DataSourceConfig.dbType)) {
            return "jdbc:oracle:thin:@" + DataSourceConfig.host + ":" + DataSourceConfig.port + ":" + DataSourceConfig.databaseName;
        } else if (StringUtils.equalsIgnoreCase(DbType.SQLServer.getDbType(), DataSourceConfig.dbType)) {
            return "jdbc:sqlserver://" + DataSourceConfig.host + ":" + DataSourceConfig.port + ";DatabaseName=" + DataSourceConfig.databaseName;
        } else {
            throw new Exception("当前数据库类型【" + DataSourceConfig.dbType + "】待开发");
        }
    }

}
