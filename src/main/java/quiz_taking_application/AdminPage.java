package quiz_taking_application;

import javax.swing.*;
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
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel welcomeLabel = new JLabel("Welcome Admin: " + admin.getUsername(), SwingConstants.CENTER);

        JButton addStudentButton = new JButton("Add Student");
        JButton deleteStudentButton = new JButton("Delete Student");
        JButton listStudentsButton = new JButton("List Students");

        // You can attach action listeners to these buttons here
        addStudentButton.addActionListener(e -> {openAddStudentDialog();});
        listStudentsButton.addActionListener(e -> showStudentList());
        deleteStudentButton.addActionListener(e -> openDeleteStudentDialog());

        panel.add(welcomeLabel);
        panel.add(addStudentButton);
        panel.add(deleteStudentButton);
        panel.add(listStudentsButton);

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
        ArrayList<Student> students = admin.getStudents(); // You need to expose this list via getter

        if (students.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students found.", "Student List", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] columnNames = {"Username", "Age", "Student ID", "Major"};
        String[][] data = new String[students.size()][columnNames.length];

        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            data[i][0] = s.getUsername();
            data[i][1] = String.valueOf(s.getAge());
            data[i][2] = s.getStudentId();
            data[i][3] = s.getMajor();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        JOptionPane.showMessageDialog(this, scrollPane, "Student List", JOptionPane.PLAIN_MESSAGE);
    }

}
