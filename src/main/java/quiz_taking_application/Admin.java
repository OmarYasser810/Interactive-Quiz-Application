package quiz_taking_application;

import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;

    public Admin(String username, String password) {
        super(username, password);
        students = new ArrayList<>();
        teachers = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "admin";
    }

    // Add a student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added: " + student.getUsername());
    }

    // Delete student by student ID WITHOUT removeIf
    public void deleteStudent(String studentId) {
        boolean removed = false;
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.remove(i);
                removed = true;
                break;
            }
        }
        System.out.println(removed
            ? "Student with ID " + studentId + " deleted."
            : "Student with ID " + studentId + " not found.");
    }

    // Add a teacher
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
        System.out.println("Teacher added: " + teacher.getUsername());
    }

    // Delete teacher by employee ID WITHOUT removeIf
    public void deleteTeacher(String employeeId) {
        boolean removed = false;
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getEmployeeId().equals(employeeId)) {
                teachers.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            System.out.println("Teacher with ID " + employeeId + " deleted successfully.");
        } else {
            System.out.println("Teacher with ID " + employeeId + " was not found.");
        }
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

}
