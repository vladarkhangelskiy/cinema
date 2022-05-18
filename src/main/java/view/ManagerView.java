package view;

import model.Movie;
import model.Ticket;
import model.User;
import service.MovieService;
import service.TicketService;
import service.UserService;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class ManagerView extends AbstractView {


    public ManagerView(MovieService movieService, TicketService ticketService, UserService userService) {
        super(movieService, ticketService, userService);

        managerMenu();
    }

    private void managerMenu() {
        while (true) {
            System.out.println("МЕНЮ МЕНЕДЖЕРА");
            System.out.println("1 Редактировать фильм");
            System.out.println("2 Купить билет");
            System.out.println("3 Вернуть билет");
            System.out.println("4 Выход");
            System.out.print("Выбор ");
            Scanner scanner = new Scanner(System.in);
            String inputUserMenu = scanner.nextLine();
            switch (inputUserMenu) {
                case "1":
                    try {
                        moviesList();
                        System.out.println("Введите id:");
                        int id = Integer.parseInt(scanner.nextLine());
                        Movie movie = movieService.get(id);
                        System.out.println("Введите title:");
                        String title = scanner.nextLine();
                        movie.setTitle(title);
                        System.out.println("Введите date в формате yyyy-MM-dd :");
                        String date = scanner.nextLine();
                        movie.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                        movieService.update(movie);
                        System.out.println("Фильм обновлён");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        usersList();
                        System.out.println("Введите id пользователя");
                        int userId = Integer.parseInt(scanner.nextLine());
                        User user = userService.get(userId);
                        moviesList();
                        System.out.print("Выберите номер фильма -->");
                        int movieId = Integer.parseInt(scanner.nextLine());
                        Movie movie = movieService.get(movieId);
                        List<Ticket> tickets = ticketService.getAvailableTicketsForMovie(movie);
                        System.out.println("Список доступных билетов:");
                        for (Ticket ticket : tickets) {
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
                        usersList();
                        System.out.println("Введите id пользователя");
                        int userId = Integer.parseInt(scanner.nextLine());
                        User user = userService.get(userId);
                        List<Ticket> tickets = ticketService.getTicketsForUser(user);
                        System.out.println("Список билетов пользователя:");
                        for (Ticket ticket : tickets) {
                            System.out.println(ticket);
                        }
                        System.out.println("Введите id билета");
                        int ticketId = Integer.parseInt(scanner.nextLine());
                        Ticket ticket = ticketService.get(ticketId);
                        ticketService.returnTicket(ticket);
                        System.out.println("Билет возвращён");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }
}