package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {

    private volatile static DBConnectionProvider instance;
    private static Connection connection;

    public static final String DB_URL = "jdbc:mysql://awseb-e-ifh3vhuuyk-stack-awsebrdsdatabase-ihwfox4tu4ds.cbeyaci1cgqp.eu-central-1.rds.amazonaws.com:3306/open_school_db";
    public static final String DB_USER = "dev_user";
    public static final String DB_PASSWORD = "Password1!";
    public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";

    private DBConnectionProvider(){
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static DBConnectionProvider getInstance(){
        if(instance == null){
            synchronized (DBConnectionProvider.class){
                if(instance == null){
                    instance = new DBConnectionProvider();
                }
            }

        }
        return instance;
    }

    public Connection getConnection(){
        try {
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}
