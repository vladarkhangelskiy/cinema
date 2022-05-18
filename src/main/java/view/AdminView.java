package view;

import model.Movie;
import model.User;
import service.MovieService;
import service.TicketService;
import service.UserService;

import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AdminView extends AbstractView {


    public AdminView(MovieService movieService, TicketService ticketService, UserService userService) {
        super(movieService, ticketService, userService);

        adminMenu();
    }

    private void adminMenu() {
        while (true) {
            System.out.println("МЕНЮ АДМИНИСТРАТОРА");
            System.out.println("1 Редактировать фильм");
            System.out.println("2 Удалить фильм");
            System.out.println("3 Редактировать пользователя");
            System.out.println("4 Удалить пользователя");
            System.out.println("5 Добавить фильм");
            System.out.println("6 Выход");
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
                        moviesList();
                        System.out.println("Введите id:");
                        int id = Integer.parseInt(scanner.nextLine());
                        Movie movie = movieService.get(id);
                        movieService.delete(movie);
                        System.out.println("Фильм удалён");
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
                        System.out.println("Введите username");
                        String username = scanner.nextLine();
                        System.out.println("Введите password");
                        String password = scanner.nextLine();
                        user.setUsername(username);
                        user.setPassword(password);
                        userService.update(user);
                        System.out.println("Пользователь обновлён");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "4":
                    try {
                        usersList();
                        System.out.println("Введите id пользователя");
                        int userId = Integer.parseInt(scanner.nextLine());
                        User user = userService.get(userId);
                        userService.delete(user);
                        System.out.println("Пользователь удалён");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "5":
                    try {
                        Movie movie = new Movie();
                        System.out.println("Введите title");
                        String title = scanner.nextLine();
                        movie.setTitle(title);
                        System.out.println("Введите date в формате yyyy-MM-dd :");
                        String date = scanner.nextLine();
                        movie.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
                        movieService.add(movie);
                        System.out.println("Фильм создан");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "6":
                    return;
                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }
}