package manager;

import config.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PasswordTokenManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public String getPasswordToken(String email){
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT token FROM `reset_password_token` " +
                    "JOIN `user` ON `reset_password_token`.user_id =  `user`.id " +
                    "WHERE user_id = (SELECT id FROM `user` WHERE email = '"+ email +"');");
            resultSet.next();
            return resultSet.getString("token");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
