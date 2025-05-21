package quiz_taking_application.Quizzes;

import java.util.ArrayList;

public class Question {
    private String questionText;
    private ArrayList<String> options;
    private int correctOptionIndex;
    private int points;

    public Question(String questionText, ArrayList<String> options, int correctOptionIndex, int points) {
        this.questionText = questionText;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
        this.points = points;
    }

    public String getQuestionText() {
        return questionText;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }

    public int getPoints() {
        return points;
    }
}
