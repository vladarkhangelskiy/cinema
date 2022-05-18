package service;

import model.Movie;
import repository.MovieRepository;

import java.sql.SQLException;
import java.util.List;

public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAll() throws SQLException {
        return movieRepository.getAll();
    }

    public Movie get(int id) throws Exception {
        return movieRepository.get(id);
    }

    public void delete(Movie movie) throws  SQLException {
        movieRepository.delete(movie);
    }

    public void update(Movie movie) throws SQLException {
        movieRepository.update(movie);
    }

    public void add(Movie movie) throws SQLException {
        movieRepository.add(movie);
    }
}
