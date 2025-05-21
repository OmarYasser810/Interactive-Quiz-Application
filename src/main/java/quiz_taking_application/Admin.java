package quiz_taking_application;

import java.util.ArrayList;

public class Admin extends User {
    private ArrayList<Student> students;
    private ArrayList<Teacher> teachers;
    private ArrayList<QuizResult> quizResults;

    public Admin(String username, String password) {
        super(username, password);
        students = new ArrayList<>();
        teachers = new ArrayList<>();
        quizResults = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "admin";
    }

    // ------------------- Student Management -------------------

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public boolean deleteStudent(String studentId) {
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(studentId)) {
                students.remove(i);
                return true;
            }
        }
        return false;
    }

    // ------------------- Teacher Management -------------------

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

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

    // ------------------- Quiz Result Management -------------------

    public void addQuizResult(QuizResult result) {
        quizResults.add(result);
    }

    public ArrayList<QuizResult> getQuizResultsForStudent(String studentId) {
        ArrayList<QuizResult> studentResults = new ArrayList<>();
        for (QuizResult result : quizResults) {
            if (result.getStudent().getStudentId().equals(studentId)) {
                studentResults.add(result);
            }
        }
        return studentResults;
    }

    public ArrayList<QuizResult> getResultsForTeacher(Teacher teacher) {
        ArrayList<QuizResult> results = new ArrayList<>();
        for (QuizResult result : quizResults) {
            if (teacher.getQuizzes().contains(result.getQuiz())) {
                results.add(result);
            }
        }
        return results;
    }
}
