package manager;

import config.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CategoryTitleManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public String getCategoryByTitle() throws SQLException {
        try {
            String categoryTitle;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title FROM category LIMIT 1;");
            resultSet.next();
            categoryTitle = resultSet.getString("title");
            return categoryTitle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
