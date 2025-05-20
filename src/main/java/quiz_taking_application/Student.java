package quiz_taking_application;

public class Student extends User {
    private int age;
    private String studentId;
    private String major;

    public Student(String username, String password, int age, String studentId, String major) {
        super(username, password); // call User constructor
        this.age = age;
        this.studentId = studentId;
        this.major = major;
    }

    // Getter methods
    public int getAge() {
        return age;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getMajor() {
        return major;
    }

    @Override
    public String getRole() {
        return "student";
    }

    public void displayInfo() {
        System.out.println("=== Student Info ===");
        System.out.println("Username: " + username);
        System.out.println("Student ID: " + studentId);
        System.out.println("Age: " + age);
        System.out.println("Major: " + major);
    }
}
