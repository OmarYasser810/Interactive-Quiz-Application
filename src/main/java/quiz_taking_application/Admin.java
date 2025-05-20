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

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    // Add a student
    public void addStudent(Student student) {
        students.add(student);
    }

    // Delete student by student ID WITHOUT removeIf
    public boolean deleteStudent(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.remove(i);
                return true; // Student removed
            }
        }
        return false; // Student not found
    }

    // Add a teacher
    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public boolean deleteTeacher(String employeeId) {
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).getEmployeeId().equals(employeeId)) {
                teachers.remove(i);
                return true;
            }
        }
        return false;
    }

}
