package quiz_taking_application;

import java.util.ArrayList;

public class LoginSystem {
    private ArrayList<User> users;

    public LoginSystem() {
        users = new ArrayList<>();

        // Adding sample users
        users.add(new Admin("admin1", "adminpass"));
        users.add(new Student("student1", "studpass", 20, "STU1001", "Physics"));
        users.add(new Teacher("teacher1", "teachpass", 35, "Math", "TCH1001"));
    }

    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.authenticate(password)) {
                return user;
            }
        }
        return null; // no match found
    }
}