package quiz_taking_application.Users;

import java.util.ArrayList;

import quiz_taking_application.Quizzes.Quiz;

public class Teacher extends User {
    private int age;
    private String subject;
    private String employeeId;
    private ArrayList<Quiz> quizzes = new ArrayList<>();

    public Teacher(String username, String password, int age, String subject, String employeeId) {
        super(username, password); // call User constructor
        this.age = age;
        this.subject = subject;
        this.employeeId = employeeId;
    }

    // Getter methods
    public int getAge() {
        return age;
    }

    public String getSubject() {
        return subject;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String getRole() {
        return "teacher";
    }

    public void addQuiz(Quiz quiz) {
        quizzes.add(quiz);
    }

    public ArrayList<Quiz> getQuizzes() {
        return quizzes;
    }

}
