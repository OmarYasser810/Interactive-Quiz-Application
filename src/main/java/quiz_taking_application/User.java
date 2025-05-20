package quiz_taking_application;

public abstract class User {
    protected String username;
    protected String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String inputPassword) {
        return this.password.equals(inputPassword);
    }

    public String getUsername() {
        return username;
    }

    // This will be overridden in child classes
    public abstract String getRole();
}