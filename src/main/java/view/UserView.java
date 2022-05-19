package view;

import model.Movie;
import model.Ticket;
import model.User;
import service.MovieService;
import service.TicketService;
import service.UserService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class UserView extends AbstractView {


    public UserView(User user, MovieService movieService, TicketService ticketService, UserService userService) {
        super(user, movieService, ticketService, userService);

        userMenu();
    }

    private void userMenu() {
        while (true) {
            System.out.println("МЕНЮ ПОЛЬЗОВАТЕЛЯ");
            System.out.println("1  Список фильмов");
            System.out.println("2  Купить билет");
            System.out.println("3  Просмотреть билеты");
            System.out.println("4  Вернуть билет");
            System.out.println("5  Выход");
            System.out.print("Выбор ");
            Scanner scanner = new Scanner(System.in);
            String inputUserMenu = scanner.nextLine();

            switch (inputUserMenu) {
                case "1":
                    try {
                        moviesList();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try{
                        System.out.println("Выберите номер фильм");
                        int movieId = Integer.parseInt(scanner.nextLine());
                        Movie movie = movieService.get(movieId);
                        List<Ticket> tickets = ticketService.getAvailableTicketsForMovie(movie);
                        System.out.println("Список доступных билетов:");
                        for (Ticket ticket: tickets) {
                            System.out.println(ticket);
                        }
                        System.out.print("Выберите номер билета -->");
                        int ticketId = Integer.parseInt(scanner.nextLine());
                        Ticket ticket = ticketService.get(ticketId);
                        ticketService.buyTicket(ticket, user);
                        System.out.println("Билет успешно куплен");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        List<Ticket> tickets = ticketService.getTicketsForUser(user);
                        System.out.println("Билеты пользователя:");
                        for (Ticket ticket: tickets) {
                            System.out.println(ticket);
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    try{
                        System.out.print("Выберите номер билета -->");
                        int ticketId = Integer.parseInt(scanner.nextLine());
                        Ticket ticket = ticketService.get(ticketId);
                        ticketService.returnTicket(ticket);
                        System.out.println("Билет возвращён");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    return;
                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }

}
