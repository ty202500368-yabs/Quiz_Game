/**
 * Represents a single quiz question with options and the correct answer.
 */
public class QuizQuestion {
    private String question;
    private String[] options;
    private int correctAnswerIndex;
    private String explanation;

    public QuizQuestion(String question, String[] options, int correctAnswerIndex, String explanation) {
        this.question = question;
        this.options = options;
        this.correctAnswerIndex = correctAnswerIndex;
        this.explanation = explanation;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public String getExplanation() {
        return explanation;
    }

    public boolean isCorrect(int selectedIndex) {
        return selectedIndex == correctAnswerIndex;
    }
}
