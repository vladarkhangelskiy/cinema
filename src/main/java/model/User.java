package model;

public class User extends Entity {

    private UserRole userRole;
    private String username;
    private String password;

    public User(int id, String username, String password, UserRole userRole) {
        super(id);
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public User(String username, String password, UserRole userRole) {
        this.username = username;
        this.password = password;
        this.userRole = userRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
