package manager;

import db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryManager {
    PreparedStatement statement;
    ResultSet resultSet;

    public void deleteUserById(int userId) {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("DELETE FROM category WHERE id=?;");
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
