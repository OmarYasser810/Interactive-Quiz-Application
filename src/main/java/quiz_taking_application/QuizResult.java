package quiz_taking_application;

import java.util.ArrayList;

public class QuizResult {
    private Student student;
    private Quiz quiz;
    private ArrayList<Integer> selectedOptions; // index of selected option per question
    private int totalScore;

    public QuizResult(Student student, Quiz quiz, ArrayList<Integer> selectedOptions, int totalScore) {
        this.student = student;
        this.quiz = quiz;
        this.selectedOptions = selectedOptions;
        this.totalScore = totalScore;
    }

    public Student getStudent() {
        return student;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public ArrayList<Integer> getSelectedOptions() {
        return selectedOptions;
    }

    public int getTotalScore() {
        return totalScore;
    }
}
