package sql.demo.repository;

import java.sql.SQLException;

public class TraidersRepository extends BaseTable implements TableOperation {
    public TraidersRepository() throws SQLException {
        super("traiders");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS "+getTableName()+"(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(255) NOT NULL," +
                "sfreqTick INTEGER NOT NULL,"+
                "cash DECIMAL(8,2),"+
                "traidingmethod INTEGER NOT NULL," +
                "changeProbability INTEGER NOT NULL," +
                "about VARCHAR(255) )", "созданна таблица " + getTableName());
    }

    @Override
    public void createForeignKeys() throws SQLException {

    }
}
