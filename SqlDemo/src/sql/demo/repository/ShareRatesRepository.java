package sql.demo.repository;

import java.sql.SQLException;

public class ShareRatesRepository extends BaseTable implements TableOperation {
    public ShareRatesRepository() throws SQLException {
        super("shareRates");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS "+getTableName()+"(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY," +
                "operDate DATE NOT NULL," +
                "share_id BIGINT NOT NULL," +
                "rate DECIMAL(8,2))", "созданна таблица " + getTableName());
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE "+getTableName()+" "+
                "ADD CONSTRAINT fk_share_id FOREIGN KEY (share_id) " +
                "REFERENCES shares(id) ", "создан FOREIGN KEY shareRates.share_id -> share.id");
    }
}
