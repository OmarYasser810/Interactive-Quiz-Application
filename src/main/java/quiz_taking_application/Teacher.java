package quiz_taking_application;

public class Teacher extends User {
    private int age;
    private String subject;
    private String employeeId;

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

    public void displayInfo() {
        System.out.println("=== Teacher Info ===");
        System.out.println("Username: " + username);
        System.out.println("Employee ID: " + employeeId);
        System.out.println("Age: " + age);
        System.out.println("Subject: " + subject);
    }
}
