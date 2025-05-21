package quiz_taking_application;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class StudentPage extends JFrame {

    private Student student;
    private Admin admin;

    public StudentPage(Student student, Admin admin) {
        this.student = student;
        this.admin = admin;

        setTitle("Student Dashboard - " + student.getUsername());
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        initUI();
    }

    private void initUI() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JLabel welcomeLabel = new JLabel("Welcome Student: " + student.getUsername(), SwingConstants.CENTER);

        JButton takeQuizButton = new JButton("Take a Quiz");
        JButton viewResultsButton = new JButton("View My Results");
        JButton logoutButton = new JButton("Logout");

        takeQuizButton.addActionListener(e -> openTakeQuizDialog());
        viewResultsButton.addActionListener(e -> showMyResults());
        logoutButton.addActionListener(e -> dispose());

        panel.add(welcomeLabel);
        panel.add(takeQuizButton);
        panel.add(viewResultsButton);
        panel.add(logoutButton);

        add(panel);
    }

    private void openTakeQuizDialog() {
        // Collect all quizzes from all teachers
        ArrayList<Quiz> allQuizzes = new ArrayList<>();
        for (Teacher t : admin.getTeachers()) {
            allQuizzes.addAll(t.getQuizzes());
        }

        if (allQuizzes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No quizzes available.", "Info", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String[] quizTitles = new String[allQuizzes.size()];
        for (int i = 0; i < allQuizzes.size(); i++) {
            quizTitles[i] = allQuizzes.get(i).getTitle();
        }

        String selectedQuizTitle = (String) JOptionPane.showInputDialog(this, "Select a quiz to take:",
                "Available Quizzes", JOptionPane.PLAIN_MESSAGE, null, quizTitles, quizTitles[0]);

        if (selectedQuizTitle != null) {
            Quiz selectedQuiz = null;
            for (Quiz q : allQuizzes) {
                if (q.getTitle().equals(selectedQuizTitle)) {
                    selectedQuiz = q;
                    break;
                }
            }
            if (selectedQuiz != null) {
                takeQuiz(selectedQuiz);
            }
        }
    }

    private void takeQuiz(Quiz quiz) {
        ArrayList<Question> questions = quiz.getQuestions();
        ArrayList<Integer> selectedAnswers = new ArrayList<>();

        for (Question q : questions) {
            String[] options = q.getOptions().toArray(new String[0]);
            String answer = (String) JOptionPane.showInputDialog(this, q.getQuestionText(),
                    "Question", JOptionPane.PLAIN_MESSAGE, null, options, options[0]);

            if (answer == null) {
                // Student canceled quiz
                JOptionPane.showMessageDialog(this, "Quiz canceled.");
                return;
            }

            // Store selected index
            selectedAnswers.add(q.getOptions().indexOf(answer));
        }

        // Calculate score
        int totalScore = 0;
        for (int i = 0; i < questions.size(); i++) {
            if (questions.get(i).getCorrectOptionIndex() == selectedAnswers.get(i)) {
                totalScore += questions.get(i).getPoints();
            }
        }

        // Save result
        QuizResult result = new QuizResult(student, quiz, selectedAnswers, totalScore);
        admin.addQuizResult(result);

        JOptionPane.showMessageDialog(this,
                "Quiz completed! Your score: " + totalScore,
                "Result", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showMyResults() {
        ArrayList<QuizResult> results = admin.getQuizResultsForStudent(student.getStudentId());

        if (results.isEmpty()) {
            JOptionPane.showMessageDialog(this, "You have no quiz results yet.", "My Results", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (QuizResult r : results) {
            sb.append("Quiz: ").append(r.getQuiz().getTitle())
                    .append(" | Score: ").append(r.getTotalScore())
                    .append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));

        JOptionPane.showMessageDialog(this, scrollPane, "My Quiz Results", JOptionPane.INFORMATION_MESSAGE);
    }
}
