public abstract class QuizzPedia {
    abstract void addQuestion(String question, String[] choices, String correctAnswer);
    abstract void displayQuestions();
    abstract boolean validateAnswer(String userAnswer, String correctAnswer);
    abstract void displayResults(String userName, double score);
}
