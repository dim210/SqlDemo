package sql.demo;

import sql.demo.repository.ShareRatesRepository;
import sql.demo.repository.SharesRepository;
import sql.demo.repository.TraidersRepository;
import sql.demo.repository.TraidersShareActionsRepository;

import java.io.Closeable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StockExchahge implements Closeable {
    public static final String DB_URL = "jdbc:h2:d:\\dim210\\docs\\trainings\\java\\code\\SqlDemo\\db\\StockExchahge";
    public static final String DB_DRIVER = "org.h2.Driver";
    private static Connection connection;
    private SharesRepository sharesRepository;
    private ShareRatesRepository shareRatesRepository;
    private TraidersRepository traidersRepository;
    private TraidersShareActionsRepository traidersShareActionsRepository;

    public StockExchahge() throws ClassNotFoundException, SQLException {
        Class.forName(DB_DRIVER);
        this.sharesRepository = new SharesRepository();
        this.shareRatesRepository = new ShareRatesRepository();
        this.traidersRepository = new TraidersRepository();
        this.traidersShareActionsRepository = new TraidersShareActionsRepository();
    }

    public static void main(String[] args) {
        try {
            StockExchahge stockExchahge = new StockExchahge();
            stockExchahge.createTables();
            stockExchahge.createTablesKeys();
            stockExchahge.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("драйвер не найден");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            System.out.println("Ошибка sql");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Исключение в close() методе");
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("StockExchange: Соединение с СУБД установлено");
        } else {
            System.out.println("StockExchange: Соединение с СУБД уже существует");
        }
        return connection;
    }
    @Override
    public void close() throws IOException {
        try {
            if (connection != null && !connection.isClosed())
                connection.close();
            System.out.println("StockExchange: SQL соединение закрыто успешно!");
        } catch (SQLException e) {
            System.out.println("StockExchange: Ошибка закрытия SQL соединения!");
        }
    }

    private void createTables() throws SQLException {
        sharesRepository.createTable();
        shareRatesRepository.createTable();
        traidersRepository.createTable();
        traidersShareActionsRepository.createTable();
    }
    private void createTablesKeys() throws SQLException {
        shareRatesRepository.createForeignKeys();
        traidersShareActionsRepository.createForeignKeys();
    }

}
