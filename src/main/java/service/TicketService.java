package service;

import model.Movie;
import model.Ticket;
import model.User;
import model.UserRole;
import repository.TicketRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    public Ticket get(int id) throws SQLException {
        return ticketRepository.get(id);
    }

    public void create(Ticket ticket) throws SQLException {
        ticketRepository.add(ticket);
    }

    public List<Ticket> getAvailableTicketsForMovie(Movie movie) throws SQLException {
        List<Ticket> tickets = ticketRepository.getAll();
        List<Ticket> availableTicketsForMovie = new ArrayList<>();

        for (Ticket ticket : tickets){
            if (ticket.getUserId() == 0 && ticket.getMovieId() == movie.getId()) {
                availableTicketsForMovie.add(ticket);
            }
        }
        return availableTicketsForMovie;
    }

    public List<Ticket> getTicketsForUser(User user) throws SQLException {
        List<Ticket> tickets = ticketRepository.getAll();
        List<Ticket> ticketForUser = new ArrayList<>();
        for (Ticket ticket : tickets){
            if (ticket.getUserId() == user.getId()){
                ticketForUser.add(ticket);
            }
        }
        return ticketForUser;
    }

    public void returnTicket(Ticket ticket) throws SQLException {
        ticket.setUserId(0);
        ticketRepository.update(ticket);
    }

    public void buyTicket(Ticket ticket, User user) throws Exception {
        if (ticket.getUserId() != 0){
            throw new Exception("Билет уже куплен!");
        } else {
            ticket.setUserId(user.getId());
            ticketRepository.update(ticket);
        }
    }
}
