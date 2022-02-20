package sql.demo.repository;

import java.sql.SQLException;

public interface TableOperation {
    void createTable() throws SQLException; //создание таблицы
    void createForeignKeys() throws SQLException; //создание связей между таблицами
    //void createExtraConstraints() throws SQLException; //constraints
}
