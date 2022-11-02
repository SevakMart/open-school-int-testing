package manager;

import db.DBConnectionProvider;

import java.sql.*;

public class AuthManager {
    private Statement statement;
    private ResultSet resultSet;

    public String getResetPasswordToken(String email) {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT token FROM `reset_password_token` " +
                    "JOIN `user` ON `reset_password_token`.user_id = `user`.id " +
                    "WHERE `user`.email ='" + email + "';");
            resultSet.next();
            return resultSet.getString("token");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
