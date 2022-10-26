package manager;

import config.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryIdManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public int getCategoryId() throws SQLException {
        try {
            int categoryId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM category LIMIT 1;");
            resultSet.next();
            categoryId = resultSet.getInt("id");
            return categoryId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }

    public int getSubCategoryId() throws SQLException {
        try {
            int categoryId;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM category Where parent_category_id>=1  LIMIT 1;");
            resultSet.next();
            categoryId = resultSet.getInt("id");
            return categoryId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
