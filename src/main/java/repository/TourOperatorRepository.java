package repository;

import entity.TourOperator;
import org.jetbrains.annotations.NotNull;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;

public class TourOperatorRepository {
    private static final String SELECT_BY_ID_QUERY =
            "SELECT id_to, full_nameto, short_nameto, inn, phone_num_to,email_to, comments from touroperator where id_to = ?";

    private static final String SELECT_BY_INN_QUERY =
            "SELECT id_to, full_nameto, short_nameto, inn, phone_num_to,email_to, comments from touroperator where inn = ?";
    private static final String INSERT_QUERY =
            "INSERT INTO touroperator (full_nameto, short_nameto, inn ,phone_num_to,email_to, comments) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_QUERY_BY_INN =
            "DELETE FROM touroperator WHERE inn = ?";

    private static final String UPDATE_FULL_NAME_QUERY_BY_ID =
            "UPDATE public.touroperator " +
                    "SET full_nameto=?, short_nameto=?, inn=?, phone_num_to=?, email_to=?, comments=? " +
                    "WHERE id_to = ?";
    public static final int WHERE_PARAMETER_INDEX = 7;

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
                return getTourOperator(resultSet);
            }
            return null;
        }
    }

    @NotNull
    private static TourOperator getTourOperator(ResultSet resultSet) throws SQLException {
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

    public TourOperator get(String inn) throws SQLException {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(SELECT_BY_INN_QUERY);
            statement.setString(1, inn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return getTourOperator(resultSet);
            }
            return null;
        }
    }

    public void insert(TourOperator touroperator) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_QUERY);
            setTourOperator(touroperator, statement);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static void setTourOperator(TourOperator touroperator, PreparedStatement statement) throws SQLException {
        statement.setString(1, touroperator.getFullName());
        statement.setString(2, touroperator.getShortName());
        statement.setString(3, touroperator.getInn());
        statement.setString(4, touroperator.getPhone());
        statement.setString(5, touroperator.getEmail());
        statement.setString(6, touroperator.getComments());
    }

    public void deleteByINN(String inn) {
        try (Connection connection = connectionFactory.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_QUERY_BY_INN);
            statement.setString(1,inn);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateFullNameByID(TourOperator tourOperator, String newFullName) {
        try (Connection connection = connectionFactory.getConnection()) {
            TourOperator tmp = new TourOperator(tourOperator);
            PreparedStatement statement = connection.prepareStatement(UPDATE_FULL_NAME_QUERY_BY_ID);

            tmp.setFullName(newFullName);
            statement.setLong(WHERE_PARAMETER_INDEX,tmp.getId());
            setTourOperator(tmp,statement);

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
