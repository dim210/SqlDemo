package sql.demo.repository;

import java.sql.SQLException;

public class TraidersShareActionsRepository extends BaseTable implements TableOperation {
    public TraidersShareActionsRepository() throws SQLException {
        super("traidersShareActions");
    }

    @Override
    public void createTable() throws SQLException {
        super.executeSqlStatement("CREATE TABLE IF NOT EXISTS "+getTableName()+"(" +
                "id BIGINT AUTO_INCREMENT PRIMARY KEY, " +
                "action_type INTEGER NOT NULL, "+
                "amount DECIMAL(8,2), "+
                "traider_id INTEGER NOT NULL, " +
                "action_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL, " +
                "description VARCHAR(255) )", "созданна таблица " + getTableName());
    }

    @Override
    public void createForeignKeys() throws SQLException {
        super.executeSqlStatement("ALTER TABLE "+getTableName()+" "+
                "ADD CONSTRAINT fk_traider_id FOREIGN KEY (traider_id) " +
                "REFERENCES traiders(id) ", "создан FOREIGN KEY traidersShareActions.traider_id -> traider.id");
    }
}
