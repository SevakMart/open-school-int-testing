package manager;

import config.DBConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CategoryIdManager {

    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public ArrayList<Integer> getAllCategoriesId() throws SQLException {

        try {
            ArrayList<Integer> listId = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT id FROM category;");
            while (resultSet.next()) {
                //System.out.println(resultSet.getInt("id"));
                listId.add(resultSet.getInt("id"));
            }
            return listId;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            connection.close();
        }
    }
}
