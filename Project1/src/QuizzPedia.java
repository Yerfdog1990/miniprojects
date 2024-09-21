import java.util.List;

public abstract class QuizzPedia {
    // Abstract method to add questions
    abstract void addQuestion(String addedQuestion, String[] choices, char correctAnswer);
    // Abstract method to display questions
    abstract void displayQuestions(boolean answerQuestions);
    // Abstract method to validate user answers
    abstract boolean validateAnswer(String userAnswer, char correctAnswer);
    // Abstract method to display results
    abstract void displayResults(String userName, double totalQuestions, double correctAnswers);
    // Abstract method to compare scores and display rank
    public abstract void compareAndDisplayRank(List<Double> previousScores, double currentScore);
    // Abstract method to simulate answering questions and calculating the number of correct answers
    public abstract void answerQuestions();
}
