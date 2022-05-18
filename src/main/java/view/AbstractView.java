package view;

import model.Movie;
import model.User;
import service.MovieService;
import service.TicketService;
import service.UserService;

import java.sql.SQLException;
import java.util.List;

public class AbstractView {

    protected User user;
    protected final MovieService movieService;
    protected  final TicketService ticketService;
    protected  final UserService userService;


    public AbstractView(MovieService movieService, TicketService ticketService, UserService userService) {
        this.movieService = movieService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    public AbstractView(User user, MovieService movieService, TicketService ticketService, UserService userService) {
        this.user = user;
        this.movieService = movieService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    protected void moviesList() throws SQLException {
        List<Movie> movies = movieService.getAll();
        System.out.println("Список всех фильмов");
        for (Movie movie : movies) {
            System.out.println(movie);
        }
    }

    protected void usersList() throws SQLException {
        List<User> users = userService.getAll();
        System.out.println("Список всех пользователей");
        for (User user : users) {
            System.out.println(user);
        }
    }
}
