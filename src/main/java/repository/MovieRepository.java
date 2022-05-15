package repository;

import connector.MySqlConnector;
import model.Movie;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {

    private final MySqlConnector connector;

    public MovieRepository(MySqlConnector connector) {
        this.connector = connector;
    }

    public Movie get(int id) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM movie WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        return new Movie(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getDate("date"));
    }

    public List<Movie> getAll() throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("SELECT * FROM movie");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<Movie> movies = new ArrayList<>();
        while (resultSet.next()){
            movies.add(new Movie(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getDate("date")));
        }
        return movies;
    }

    public void update(Movie movie) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("UPDATE movie SET title = ?, date = ?, where id = ?");
        preparedStatement.setString(1, movie.getTittle());
        preparedStatement.setDate(2, (Date) movie.getDate());
        preparedStatement.setInt(3,movie.getId());
        preparedStatement.executeUpdate();
    }

    public void delete(Movie movie) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("DELETE FROM movie WHERE id = ?");
        preparedStatement.setInt(1, movie.getId());
        preparedStatement.executeUpdate();
    }

    public void add(Movie movie) throws SQLException {
        PreparedStatement preparedStatement = connector.getConnection().prepareStatement("INSERT INTO movie (title, date) values(?, ?)");
        preparedStatement.setString(1, movie.getTittle());
        preparedStatement.setDate(2, (Date) movie.getDate());
        preparedStatement.executeUpdate();
    }
}
