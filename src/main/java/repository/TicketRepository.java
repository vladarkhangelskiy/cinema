package repository;

import connector.MySqlConnector;
import model.Movie;
import model.Ticket;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketRepository {

    private final MySqlConnector connector;

    public TicketRepository(MySqlConnector connector) {
        this.connector = connector;
    }

    public Ticket get(int id) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM ticket WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new Ticket(
            resultSet.getInt("id"),
            resultSet.getInt("price"),
            resultSet.getInt("seat"),
            resultSet.getInt("userId"),
            resultSet.getInt("movieId"));
    }

    public List<Ticket> getAll() throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM ticket");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Ticket> tickets = new ArrayList<>();
        while (resultSet.next()){
            tickets.add(new Ticket(resultSet.getInt("id"),
                    resultSet.getInt("price"),
                    resultSet.getInt("seat"),
                    resultSet.getInt("userId"),
                    resultSet.getInt("movieId")));
        }
        return tickets;
    }

    public void update(Ticket ticket) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("UPDATE ticket SET price = ?, seat = ?, " +
                "userId = ?, movieId = ? where id = ?");
        preparedStatement.setInt(1, ticket.getPrice());
        preparedStatement.setInt(2, ticket.getSeat());
        preparedStatement.setInt(3, ticket.getUserId());
        preparedStatement.setInt(4, ticket.getMovieId());
        preparedStatement.setInt(5, ticket.getId());
        preparedStatement.executeUpdate();
    }

    public void delete (Ticket ticket) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("DELETE FROM ticket WHERE id = ?");
        preparedStatement.setInt(1, ticket.getId());
        preparedStatement.executeUpdate();
    }

    public void add(Ticket ticket) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("INSERT INTO ticket" +
                " (price, seat,userId, movieId) values(?, ?, ?, ?)");
        preparedStatement.setInt(1, ticket.getPrice());
        preparedStatement.setInt(2, ticket.getSeat());
        preparedStatement.setInt(3, ticket.getUserId());
        preparedStatement.setInt(4, ticket.getMovieId());
        preparedStatement.executeUpdate();
    }
}
