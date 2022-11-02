package db;

import utils.PropertiesReader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private volatile static DBConnectionProvider instance;
    private static Connection connection;

    private static final PropertiesReader propertiesReader = PropertiesReader.getInstance("config.properties");

    private DBConnectionProvider() {
        try {
            Class.forName(propertiesReader.getPropety("DB_DRIVER"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnectionProvider getInstance() {
        if (instance == null) {
            synchronized (DBConnectionProvider.class) {
                if (instance == null) {
                    instance = new DBConnectionProvider();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(
                        propertiesReader.getPropety("DB_URL"),
                        propertiesReader.getPropety("DB_USER"),
                        propertiesReader.getPropety("DB_PASSWORD"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
