package manager;

import config.DBConnectionProvider;

import java.sql.*;

public class AuthManager {
    static Statement statement;
    ResultSet resultSet;

    public String getPasswordToken(String email) {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT token FROM `reset_password_token` " +
                    "JOIN `user` ON `reset_password_token`.user_id =  `user`.id " +
                    "WHERE user_id = (SELECT id FROM `user` WHERE email = '" + email + "');");
            resultSet.next();
            return resultSet.getString("token");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
