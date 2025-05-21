package quiz_taking_application.GUIs;

import javax.swing.*;

import quiz_taking_application.Quizzes.Question;
import quiz_taking_application.Quizzes.Quiz;
import quiz_taking_application.Quizzes.QuizResult;
import quiz_taking_application.Users.Admin;
import quiz_taking_application.Users.Teacher;

import java.awt.*;
import java.util.ArrayList;

public class TeacherPage extends JFrame {

    private Teacher teacher;
    private Admin admin;

    public TeacherPage(Teacher teacher, Admin admin) {
        this.teacher = teacher;
        this.admin = admin;
        setTitle("Teacher Dashboard - " + teacher.getUsername());
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel welcomeLabel = new JLabel("Welcome Teacher: " + teacher.getUsername(), SwingConstants.CENTER);

        JButton createQuizButton = new JButton("Create Quiz");
        JButton viewQuizzesButton = new JButton("View My Quizzes");
        JButton viewResultsButton = new JButton("View Quiz Results");
        JButton logoutButton = new JButton("Logout");

        createQuizButton.addActionListener(e -> openCreateQuizDialog());
        viewQuizzesButton.addActionListener(e -> showMyQuizzes());
        viewResultsButton.addActionListener(e -> showStudentResults());
        logoutButton.addActionListener(e -> dispose()); // Close the window

        panel.add(welcomeLabel);
        panel.add(createQuizButton);
        panel.add(viewQuizzesButton);
        panel.add(viewResultsButton);
        panel.add(logoutButton);

        add(panel);
    }

    private void openCreateQuizDialog() {
        JTextField titleField = new JTextField();
        int result = JOptionPane.showConfirmDialog(this, titleField, "Enter Quiz Title", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText().trim();
            if (title.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Quiz title cannot be empty.");
                return;
            }

            Quiz quiz = new Quiz(title);
            boolean addMore = true;

            while (addMore) {
                JTextField questionField = new JTextField();
                JTextField pointsField = new JTextField();
                JTextField optA = new JTextField();
                JTextField optB = new JTextField();
                JTextField optC = new JTextField();
                JTextField optD = new JTextField();

                String[] correctOptions = {"A", "B", "C", "D"};
                JComboBox<String> correctOptionBox = new JComboBox<>(correctOptions);

                JPanel panel = new JPanel(new GridLayout(7, 2, 5, 5));

                panel.add(new JLabel("Question:"));
                panel.add(questionField);

                panel.add(new JLabel("Points:"));
                panel.add(pointsField);

                panel.add(new JLabel("Option A:"));
                panel.add(optA);

                panel.add(new JLabel("Option B:"));
                panel.add(optB);

                panel.add(new JLabel("Option C:"));
                panel.add(optC);

                panel.add(new JLabel("Option D:"));
                panel.add(optD);

                panel.add(new JLabel("Correct Option:"));
                panel.add(correctOptionBox);

                int qResult = JOptionPane.showConfirmDialog(this, panel, "Add Question", JOptionPane.OK_CANCEL_OPTION);

                if (qResult == JOptionPane.OK_OPTION) {
                    ArrayList<String> options = new ArrayList<>();
                    options.add(optA.getText());
                    options.add(optB.getText());
                    options.add(optC.getText());
                    options.add(optD.getText());

                    int correctIndex = correctOptionBox.getSelectedIndex();

                    int points;
                    try {
                        points = Integer.parseInt(pointsField.getText().trim());
                    } catch (NumberFormatException e) {
                        points = 1; // default if not provided or invalid
                    }
                    Question question = new Question(questionField.getText(), options, correctIndex, points);
                    
                    quiz.addQuestion(question);
                } else {
                    addMore = false;
                }

                int continueAdding = JOptionPane.showConfirmDialog(this, "Add another question?", "Continue?", JOptionPane.YES_NO_OPTION);
                addMore = (continueAdding == JOptionPane.YES_OPTION);
            }

            teacher.addQuiz(quiz);
            JOptionPane.showMessageDialog(this, "Quiz '" + quiz.getTitle() + "' created with " + quiz.getQuestions().size() + " questions.");
        }
    }

    private void showMyQuizzes() {
        ArrayList<Quiz> quizzes = teacher.getQuizzes();

        if (quizzes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You haven't created any quizzes yet.", "My Quizzes", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();

        for (Quiz quiz : quizzes) {
            sb.append("Quiz Title: ").append(quiz.getTitle()).append("\n");

            ArrayList<Question> questions = quiz.getQuestions();
            for (int i = 0; i < questions.size(); i++) {
                Question q = questions.get(i);
                sb.append("  Q").append(i + 1).append(": ").append(q.getQuestionText()).append(" (Points: ").append(q.getPoints()).append(")\n");

                ArrayList<String> opts = q.getOptions();
                for (int j = 0; j < opts.size(); j++) {
                    sb.append("    ").append((char) ('A' + j)).append(": ").append(opts.get(j)).append("\n");
                }

                sb.append("    Correct Answer: ").append((char) ('A' + q.getCorrectOptionIndex())).append("\n\n");
            }
            sb.append("--------------------------------------------------\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "My Quizzes", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showStudentResults() {
        ArrayList<QuizResult> results = admin.getResultsForTeacher(teacher);

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No students have taken your quizzes yet.", "Quiz Results", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Student Results for Your Quizzes:\n\n");

        // Group by quiz title
        for (Quiz quiz : teacher.getQuizzes()) {
            sb.append("Quiz: ").append(quiz.getTitle()).append("\n");

            boolean hasResults = false;
            for (QuizResult result : results) {
                if (result.getQuiz().equals(quiz)) {
                    sb.append("  Student: ").append(result.getStudent().getUsername())
                    .append(" | Score: ").append(result.getTotalScore()).append("\n");
                    hasResults = true;
                }
            }

            if (!hasResults) {
                sb.append("  No students have taken this quiz yet.\n");
            }

            sb.append("--------------------------------------------------\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(500, 400));

        JOptionPane.showMessageDialog(this, scrollPane, "Student Quiz Results", JOptionPane.INFORMATION_MESSAGE);
    }


}
