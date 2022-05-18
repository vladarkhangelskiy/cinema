import connector.MySqlConnector;
import model.Ticket;
import repository.MovieRepository;
import repository.TicketRepository;
import repository.UserRepository;
import service.MovieService;
import service.TicketService;
import service.UserService;
import view.MainView;

public class Main {

    public static void main(String[] args) {

        MySqlConnector connector = new MySqlConnector();
        MovieRepository movieRepository = new MovieRepository(connector);
        TicketRepository ticketRepository = new TicketRepository(connector);
        UserRepository userRepository = new UserRepository(connector);
        MovieService movieService = new MovieService(movieRepository);
        TicketService ticketService = new TicketService(ticketRepository);
        UserService userService = new UserService(userRepository);

        MainView mainView = new MainView(movieService, ticketService,userService);

    }
}
