package service;

import model.Movie;
import model.User;
import model.UserRole;
import repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll() throws SQLException {
        return userRepository.getAll();
    }

    public User get(int id) throws SQLException {
        return userRepository.get(id);
    }

    public void delete(User user) throws SQLException {
        userRepository.delete(user);
    }

    public void update(User user) throws SQLException {
        userRepository.update(user);
    }

    public void add(String username, String password) throws Exception {
        if (userRepository.get(username) == null) {
            userRepository.add(new User(username,password, UserRole.USER));
        } else {
            throw new Exception("Такой пользователь уже существует");
        }
    }

    public User login(String username, String password) throws Exception {
        User user = userRepository.get(username, password);
        if (user == null){
            throw new Exception("Неправильный логин или пароль");
        }
        return user;
    }
}