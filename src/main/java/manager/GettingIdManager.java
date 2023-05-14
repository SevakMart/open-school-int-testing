package manager;

import db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GettingIdManager {
    PreparedStatement statement;
    ResultSet resultSet;

    public int getFromPeersQuestionTableFirstId() {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("SELECT MIN(id) FROM peers_question;");
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("MIN(id)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int getFromMentorQuestionTableFirstId() {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("SELECT MIN(id) FROM mentor_question;");
            resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("MIN(id)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
