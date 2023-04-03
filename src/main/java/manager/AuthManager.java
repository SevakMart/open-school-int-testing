package manager;

import db.DBConnectionProvider;

import java.sql.*;

public class AuthManager {
    PreparedStatement statement;
    ResultSet resultSet;

    public String getResetPasswordToken(String email) {
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

    public String getTokenForVerify(int userId) {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("SELECT token FROM open_school_db.verification_token WHERE user_id=?;");
            statement.setInt(1, userId);
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getString("token");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserById(int userId) {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("DELETE FROM open_school_db.`user` WHERE id=?;");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
