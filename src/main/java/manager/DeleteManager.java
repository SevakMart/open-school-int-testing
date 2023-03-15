package manager;

import db.DBConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteManager {
    PreparedStatement statement;
    public void deleteCourse(int enrolledCourseId) {

        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("DELETE FROM `enrolled_learning_path` WHERE id=?");
            statement.setInt(1, enrolledCourseId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteQuestionToThePeers(int questionId) {
        try (final Connection connection = DBConnectionProvider.getInstance().getConnection()) {
            statement = connection.prepareStatement("DELETE FROM `enrolled_learning_path` WHERE id=?");
            statement.setInt(1, questionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
