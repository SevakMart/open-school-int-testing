package manager;

import db.DBConnectionProvider;

import java.sql.*;

public class AuthManager {
    public String getResetPasswordToken(String email) {
        PreparedStatement statement;
        ResultSet resultSet;
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("SELECT token FROM `reset_password_token` " +
                    "JOIN `user` ON `reset_password_token`.user_id = `user`.id " +
                    "WHERE `user`.email =?;");
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("token");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
