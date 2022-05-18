package repository;

import connector.MySqlConnector;
import model.Ticket;
import model.User;
import model.UserRole;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {
    private final MySqlConnector connector;

    public UserRepository(MySqlConnector connector) {
        this.connector = connector;
    }

    public User get(String username) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE username = ?");
        preparedStatement.setString(1, username);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    UserRole.valueOf(resultSet.getString("userRole")));
        } else {
            return null;
        }
    }

    public User get(String username, String password) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    UserRole.valueOf(resultSet.getString("userRole")));
        } else {
            return null;
        }
    }

    public User get(int id) throws Exception {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            return new User(
                    resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    UserRole.valueOf(resultSet.getString("userRole")));
        } else {
            throw new Exception("пользователь не найден");
        }
    }

    public List<User> getAll() throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM user");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> tickets = new ArrayList<>();
        while (resultSet.next()){
            tickets.add(new User(resultSet.getInt("id"),
                    resultSet.getString("username"),
                    resultSet.getString("password"),
                    UserRole.valueOf(resultSet.getString("userRole"))));
        }
        return tickets;
    }

    public void update(User user) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("UPDATE user SET username = ?, password = ? where id = ?");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setInt(3, user.getId());
        preparedStatement.executeUpdate();
    }

    public void delete (User user) throws Exception {
        if (user.getUserRole() != UserRole.USER) {
            throw new Exception("нельзя удалить админа или менеджера");
        }
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("DELETE FROM user WHERE id = ?");
        preparedStatement.setInt(1, user.getId());
        preparedStatement.executeUpdate();
    }

    public void add(User user) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("INSERT INTO user" +
                " (username , password, userRole) values(?, ?, ?)");
        preparedStatement.setString(1, user.getUsername());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.setString(3, user.getUserRole().name());
        preparedStatement.executeUpdate();
    }
}
