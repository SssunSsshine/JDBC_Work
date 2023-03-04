package repository;

import entity.Entity;
import entity.Tourist;
import org.jetbrains.annotations.Nullable;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
public class TouristRepository implements Repository{
    public static final String SPACE = " ";
    private static final String SELECT_BY_ID_QUERY =
            "SELECT id_tourist, surname, name, patronymic, birthday, email_tourist, phone_num_tourist FROM tourist WHERE id_tourist = ?";

    private static final String SELECT_BY_EMAIL_QUERY =
            "SELECT id_tourist, surname, name, patronymic, birthday, email_tourist, phone_num_tourist FROM tourist WHERE email_tourist = ?";

    private static final String SELECT_BY_PHONE_QUERY =
            "SELECT id_tourist, surname, name, patronymic, birthday, email_tourist, phone_num_tourist FROM tourist WHERE phone_num_tourist = ?";
    private static final String INSERT_QUERY =
            "INSERT INTO tourist (surname, name, patronymic, birthday, email_tourist, phone_num_tourist) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY =
            "DELETE FROM tourist";

    private static final String UPDATE_QUERY =
            "UPDATE tourist SET";

    private ConnectionFactory connectionFactory;

    public TouristRepository(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public Tourist get(Long id) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_QUERY);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Tourist tourist = new Tourist();
                tourist.setId(resultSet.getLong("id_tourist"));
                tourist.setName(resultSet.getString("name"));
                tourist.setSurname(resultSet.getString("surname"));
                tourist.setBirthday(resultSet.getDate("birthday"));
                tourist.setPatronymic(resultSet.getString("patronymic"));
                tourist.setEmail(resultSet.getString("email_tourist"));
                tourist.setPhone(resultSet.getString("phone_num_tourist"));
                return tourist;
            }
            return null;
        }
    }

    public Tourist get(String phone) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_PHONE_QUERY);
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Tourist tourist = new Tourist();
                tourist.setId(resultSet.getLong("id_tourist"));
                tourist.setName(resultSet.getString("name"));
                tourist.setSurname(resultSet.getString("surname"));
                tourist.setBirthday(resultSet.getDate("birthday"));
                tourist.setPatronymic(resultSet.getString("patronymic"));
                tourist.setEmail(resultSet.getString("email_tourist"));
                tourist.setPhone(resultSet.getString("phone_num_tourist"));
                return tourist;
            }
            return null;
        }
    }
    public void insert(Entity entity) {
        Tourist tourist;
        if (entity.getClass() == Tourist.class){
            tourist = (Tourist) entity;
        }
        else return;
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            statement.setString(1, tourist.getSurname());
            statement.setString(2, tourist.getName());
            statement.setString(3, tourist.getPatronymic());
            statement.setDate(4, tourist.getBirthday());
            statement.setString(5, tourist.getEmail());
            statement.setString(6, tourist.getPhone());
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
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY + SPACE + setCondition + SPACE + whereCondition);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
