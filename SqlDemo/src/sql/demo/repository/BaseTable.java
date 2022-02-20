package sql.demo.repository;

import sql.demo.StockExchahge;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BaseTable implements Closeable {
    protected Connection connection;
    protected String tableName;

    BaseTable(String tableName) throws SQLException {
        this.tableName = tableName;
        this.connection = StockExchahge.getConnection();
    }

    @Override
    public void close() {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
            System.out.println("BaseTable: SQL соединение закрыто успешно!");
        } catch (SQLException e) {
            System.out.println("BaseTable: Ошибка закрытия SQL соединения!");
        }
    }

    void executeSqlStatement(String sql, String description) throws SQLException {
        reopenConnection();
        Statement statement = connection.createStatement();
        statement.execute(sql);
        statement.close();

        if (description != null) {
            System.out.println(description);
        }
    }

    void executeSqlStatement(String sql) throws SQLException {
        executeSqlStatement(sql, null);
    };

    void reopenConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = StockExchahge.getConnection();
        }
    }

    public String getTableName() {
        return tableName;
    }
}
