package repository;

import entity.Tourist;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

public class TouristRepository {
    private static final String SELECT_BY_ID_QUERY =
            "SELECT id_tourist, surname, name, patronymic, birthday, email_tourist, phone_num_tourist FROM tourist WHERE id_tourist = ?";

    private static final String SELECT_BY_EMAIL_QUERY =
            "SELECT id_tourist, surname, name, patronymic, birthday, email_tourist, phone_num_tourist FROM tourist WHERE email_tourist = ?";

    private static final String SELECT_BY_PHONE_QUERY =
            "SELECT id_tourist, surname, name, patronymic, birthday, email_tourist, phone_num_tourist FROM tourist WHERE phone_num_tourist = ?";
    private static final String INSERT_QUERY =
            "INSERT INTO tourist (surname, name, patronymic, birthday, email_tourist, phone_num_tourist) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY_BY_PHONE =
            "DELETE FROM tourist WHERE phone_num_tourist = ?";

    private static final String UPDATE_QUERY =
            "UPDATE tourist " +
                    "SET surname=?, name=?, patronymic=?, birthday=?, email_tourist=?, phone_num_tourist=? " +
                    "WHERE id_tourist = ?";
    public static final int WHERE_PARAMETER_INDEX = 7;

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
                return getTourist(resultSet);
            }
            return null;
        }
    }

    @NotNull
    private static Tourist getTourist(ResultSet resultSet) throws SQLException {
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

    public Tourist get(String phone) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_PHONE_QUERY);
            statement.setString(1, phone);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getTourist(resultSet);
            }
            return null;
        }
    }

    public void insert(Tourist tourist) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setTourist(tourist, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setTourist(Tourist tourist, PreparedStatement statement) throws SQLException {
        statement.setString(1, tourist.getSurname());
        statement.setString(2, tourist.getName());
        statement.setString(3, tourist.getPatronymic());
        statement.setDate(4, tourist.getBirthday());
        statement.setString(5, tourist.getEmail());
        statement.setString(6, tourist.getPhone());
    }

    public void deleteByPhone(String phone) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_PHONE);
            statement.setString(1,phone);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateNameByID(Tourist tourist, String newName) {
        try (Connection connection = connectionFactory.getConnection()) {
            Tourist tmp = new Tourist(tourist);
            PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);

            tmp.setName(newName);
            setTourist(tmp,statement);
            statement.setLong(WHERE_PARAMETER_INDEX,tmp.getId());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
