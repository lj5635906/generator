package com.git.generator.handler;

import com.git.generator.config.DataSourceConfig;
import com.git.generator.domain.Column;
import com.git.generator.domain.Table;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DataSourceConfig dataSourceConfig;

    /**
     * 获取数据库连接
     *
     * @return Connection
     * @throws Exception Exception
     */
    public Connection getConnection() throws Exception {
        Class.forName(dataSourceConfig.getDriverClassName());
        // 获取链接
        return DriverManager.getConnection(dataSourceConfig.getUrl(), dataSourceConfig.getUsername(), dataSourceConfig.getPassword());
    }

    /**
     * 获取数据库中所有表信息
     *
     * @return Map<String, Table> <表名,表信息>
     */
    public Map<String, Table> getAllTable() throws Exception {
        Map<String, Table> map = new HashMap<>(2);
        Connection connection = null;
        ResultSet resultSet = null;
        ResultSet primaryResultSet = null;
        try {
            Class.forName(dataSourceConfig.getDriverClassName());
            // 获取链接
            connection = DriverManager.getConnection(dataSourceConfig.getUrl() + "&useInformationSchema=true", dataSourceConfig.getUsername(), dataSourceConfig.getPassword());
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
            connection = getConnection();
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
            connection = getConnection();
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
}
