package quiz_taking_application;

import quiz_taking_application.GUIs.LoginPage;
import quiz_taking_application.Users.Admin;
import quiz_taking_application.Users.Student;
import quiz_taking_application.Users.Teacher;

public class Main {
    public static void main(String[] args) {
        
        Admin admin = new Admin("admin1", "adminpass");
        admin.addStudent(new Student("stud1", "studpass", 20, "S001", "CS"));
        admin.addStudent(new Student("stud2", "studpass", 22, "S002", "Math"));
        admin.addStudent(new Student("stud3", "studpass", 21, "S003", "Physics"));
        admin.addTeacher(new Teacher("teach1", "teachpass", 35, "Math", "T001"));
        admin.addTeacher(new Teacher("teach2", "teachpass", 40, "Physics", "T002"));    
        
        new LoginPage(admin).setVisible(true);
    }
}