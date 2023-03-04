package repository;

import entity.Entity;
import entity.TourOperator;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

public class TourOperatorRepository implements Repository{
    public static final String SPACE = " ";
    private static final String SELECT_BY_ID_QUERY =
            "SELECT id_to, full_nameto, short_nameto, inn, phone_num_to,email_to, comments from touroperator where id_to = ?";

    private static final String SELECT_BY_INN_QUERY =
            "SELECT id_to, full_nameto, short_nameto, inn, phone_num_to,email_to, comments from touroperator where inn = ?";
    private static final String INSERT_QUERY =
            "INSERT INTO touroperator (full_nameto, short_nameto, inn ,phone_num_to,email_to, comments) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY =
            "DELETE FROM touroperator";

    private static final String UPDATE_QUERY =
            "UPDATE touroperator SET";

    private ConnectionFactory connectionFactory;

    public TourOperatorRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public TourOperator get(Long id) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TourOperator touroperator = new TourOperator();
                touroperator.setId(resultSet.getLong("id_to"));
                touroperator.setFullName(resultSet.getString("full_nameto"));
                touroperator.setShortName(resultSet.getString("short_nameto"));
                touroperator.setInn(resultSet.getString("inn"));
                touroperator.setComments(resultSet.getString("comments"));
                touroperator.setEmail(resultSet.getString("email_to"));
                touroperator.setPhone(resultSet.getString("phone_num_to"));
                return touroperator;
            }
            return null;
        }
    }

    public TourOperator get(String inn) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_INN_QUERY);
            statement.setString(1, inn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                TourOperator touroperator = new TourOperator();
                touroperator.setId(resultSet.getLong("id_to"));
                touroperator.setFullName(resultSet.getString("full_nameto"));
                touroperator.setShortName(resultSet.getString("short_nameto"));
                touroperator.setInn(resultSet.getString("inn"));
                touroperator.setComments(resultSet.getString("comments"));
                touroperator.setEmail(resultSet.getString("email_to"));
                touroperator.setPhone(resultSet.getString("phone_num_to"));
                return touroperator;
            }
            return null;
        }
    }

    public void insert(Entity entity) {
        TourOperator touroperator;
        if (entity.getClass() == TourOperator.class){
            touroperator = (TourOperator) entity;
        }
        else return;

        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, touroperator.getFullName());
            statement.setString(2, touroperator.getShortName());
            statement.setString(3, touroperator.getInn());
            statement.setString(4, touroperator.getPhone());
            statement.setString(5, touroperator.getEmail());
            statement.setString(6, touroperator.getComments());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(@Nullable String whereCondition) {
        if (whereCondition == null) whereCondition = "";
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY + SPACE + whereCondition);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(String setCondition, @Nullable String whereCondition) {
        if (setCondition == null || setCondition.isBlank()) return;
        if (whereCondition == null) whereCondition = "";

        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY + SPACE + setCondition.trim() + SPACE + whereCondition.trim());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
