package quiz_taking_application.GUIs;

import javax.swing.*;

import quiz_taking_application.Users.Admin;
import quiz_taking_application.Users.Student;
import quiz_taking_application.Users.Teacher;

import java.awt.*;
import java.util.ArrayList;

public class AdminPage extends JFrame {

    private Admin admin;

    public AdminPage(Admin admin) {
        this.admin = admin;

        setTitle("Admin Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(8, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel welcomeLabel = new JLabel("Welcome Admin: " + admin.getUsername(), SwingConstants.CENTER);

        JButton addStudentButton = new JButton("Add Student");
        JButton deleteStudentButton = new JButton("Delete Student");
        JButton listStudentsButton = new JButton("List Students");
        JButton addTeacherButton = new JButton("Add Teacher");
        JButton deleteTeacherButton = new JButton("Delete Teacher");
        JButton listTeachersButton = new JButton("List Teachers");
        JButton logoutButton = new JButton("Logout");

        // Attach action listeners
        addStudentButton.addActionListener(e -> openAddStudentDialog());
        deleteStudentButton.addActionListener(e -> openDeleteStudentDialog());
        listStudentsButton.addActionListener(e -> showStudentList());
        addTeacherButton.addActionListener(e -> openAddTeacherDialog());
        deleteTeacherButton.addActionListener(e -> openDeleteTeacherDialog());
        listTeachersButton.addActionListener(e -> showTeacherList());
        logoutButton.addActionListener(e -> dispose()); // Close the window

        // Add components in order
        panel.add(welcomeLabel);
        panel.add(addStudentButton);
        panel.add(deleteStudentButton);
        panel.add(listStudentsButton);
        panel.add(addTeacherButton);
        panel.add(deleteTeacherButton);
        panel.add(listTeachersButton);
        panel.add(logoutButton);

        add(panel);
    }


    private void openAddStudentDialog() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField studentIdField = new JTextField();
        JTextField majorField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Student ID:"));
        panel.add(studentIdField);
        panel.add(new JLabel("Major:"));
        panel.add(majorField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Student",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String studentId = studentIdField.getText().trim();
                String major = majorField.getText().trim();

                Student newStudent = new Student(username, password, age, studentId, major);
                admin.addStudent(newStudent);
                JOptionPane.showMessageDialog(this, "Student added successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to add student: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openDeleteStudentDialog() {
        String studentId = JOptionPane.showInputDialog(this, "Enter Student ID to delete:");

        if (studentId != null && !studentId.trim().isEmpty()) {
            boolean removed = admin.deleteStudent(studentId.trim());

            if (removed) {
                JOptionPane.showMessageDialog(this, "Student with ID " + studentId + " deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No student found with ID " + studentId + ".", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    private void showStudentList() {
        ArrayList<Student> students = admin.getStudents();

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students found.", "Student List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Students:\n\n");
        for (Student s : students) {
            sb.append("Username: ").append(s.getUsername()).append("\n")
            .append("Student ID: ").append(s.getStudentId()).append("\n")
            .append("Age: ").append(s.getAge()).append("\n")
            .append("Major: ").append(s.getMajor()).append("\n\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Student List", JOptionPane.INFORMATION_MESSAGE);
    }


    private void openAddTeacherDialog() {
        JTextField usernameField = new JTextField();
        JTextField passwordField = new JTextField();
        JTextField ageField = new JTextField();
        JTextField subjectField = new JTextField();
        JTextField employeeIdField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 1));
        panel.add(new JLabel("Username:"));
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        panel.add(passwordField);
        panel.add(new JLabel("Age:"));
        panel.add(ageField);
        panel.add(new JLabel("Subject:"));
        panel.add(subjectField);
        panel.add(new JLabel("Employee ID:"));
        panel.add(employeeIdField);

        int result = JOptionPane.showConfirmDialog(this, panel, "Add New Teacher",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String username = usernameField.getText().trim();
                String password = passwordField.getText().trim();
                int age = Integer.parseInt(ageField.getText().trim());
                String subject = subjectField.getText().trim();
                String employeeId = employeeIdField.getText().trim();

                Teacher newTeacher = new Teacher(username, password, age, subject, employeeId);
                admin.addTeacher(newTeacher);
                JOptionPane.showMessageDialog(this, "Teacher added successfully!");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Age must be a number.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Failed to add teacher: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void openDeleteTeacherDialog() {
        String employeeId = JOptionPane.showInputDialog(this, "Enter Employee ID to delete:");

        if (employeeId != null && !employeeId.trim().isEmpty()) {
            boolean removed = admin.deleteTeacher(employeeId.trim());

            if (removed) {
                JOptionPane.showMessageDialog(this, "Teacher with ID " + employeeId + " deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No teacher found with ID " + employeeId + ".", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void showTeacherList() {
        ArrayList<Teacher> teachers = admin.getTeachers();

        if (teachers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No teachers found.", "Teacher List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder("Teachers:\n\n");
        for (Teacher t : teachers) {
            sb.append("Username: ").append(t.getUsername()).append("\n")
            .append("Employee ID: ").append(t.getEmployeeId()).append("\n")
            .append("Age: ").append(t.getAge()).append("\n")
            .append("Subject: ").append(t.getSubject()).append("\n\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(350, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "Teacher List", JOptionPane.INFORMATION_MESSAGE);
    }

}
