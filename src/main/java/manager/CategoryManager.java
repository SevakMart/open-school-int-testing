package manager;

import db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryManager {

    private Statement statement;
    private ResultSet resultSet;

    public int getParentCategoryId() {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            int categoryId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM category WHERE parent_category_id IS NULL LIMIT 1;");
            resultSet.next();
            categoryId = resultSet.getInt("id");
            return categoryId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getSubCategoryId() {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            int categoryId;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT id FROM category WHERE parent_category_id >= 1 LIMIT 1;");
            resultSet.next();
            categoryId = resultSet.getInt("id");
            return categoryId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String getCategoryByTitle() {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            String categoryTitle;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT title FROM category LIMIT 1;");
            resultSet.next();
            categoryTitle = resultSet.getString("title");
            return categoryTitle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
