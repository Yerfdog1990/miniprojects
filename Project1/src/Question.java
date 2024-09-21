import java.io.Serializable;
import java.util.Objects;

public class Question implements Serializable {
    private static final long serialVersionUID = 1L;  // Add serialVersionUID for serialization compatibility

    private String questionText;
    private String[] choices;
    private char correctAnswer;

    public Question(String questionText, String[] choices, char correctAnswer) {
        this.questionText = questionText;
        this.choices = choices;
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String[] getChoices() {
        return choices;
    }

    public char getCorrectAnswer() {
        return correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(questionText, question.questionText);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionText);
    }
}
