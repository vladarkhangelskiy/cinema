package view;

import service.MovieService;
import service.TicketService;
import service.UserService;

import java.util.Scanner;

import static model.UserRole.*;

public class MainView extends AbstractView {

    public MainView(MovieService movieService, TicketService ticketService, UserService userService) {
        super(movieService, ticketService, userService);

        mainMenu();
    }

    private void mainMenu() {
        while (true) {
            System.out.println("КИНОТЕАТР");
            System.out.println("1 Авторизация");
            System.out.println("2 Регистрация");
            System.out.println("3 Выход");
            System.out.print("Выбор ");
            Scanner scanner = new Scanner(System.in);
            String inputMenu = scanner.nextLine();
            switch (inputMenu) {
                case "1":
                    try {
                        authorizationMenu();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        break;
                    }
                    switch (user.getUserRole()) {
                        case USER:
                            new UserView(user, movieService, ticketService, userService);
                            break;
                        case MANAGER:
                            new ManagerView(movieService, ticketService, userService);
                            break;
                        case ADMIN:
                            new AdminView(movieService, ticketService, userService);
                    }
                    break;
                case "2":
                    try {
                        registrationMenu();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Введено неверное значение");
                    break;
            }
        }
    }

    private void authorizationMenu() throws Exception {
        System.out.println ("Авторизация");
        System.out.print ("Введите логин ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print ("Введите пароль ");
        String password = scanner.nextLine();
        user = userService.login(username, password);
        System.out.println("пользователь зашёл в систему");
    }

    private void registrationMenu() throws Exception {
        System.out.println("Регистрация");
        System.out.print("Введите логин ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        System.out.print("Введите пароль ");
        String password = scanner.nextLine();
        userService.add(username, password);
        System.out.println("пользователь создан");
    }
}