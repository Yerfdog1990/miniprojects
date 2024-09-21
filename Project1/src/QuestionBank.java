import java.util.*;

class QuestionBank extends QuizzPedia{
    // Set to hold unique questions
    Set<Question> questionSet = new HashSet<>();
    private int totalQuestions;
    private int correctAnswers;

    public int getCorrectAnswers() {
        return correctAnswers;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    @Override
    void addQuestion(String addedQuestion, String[] choices, char correctAnswer) {
        Question newQuestion = new Question(addedQuestion, choices, correctAnswer);
        if(newQuestion.equals(questionSet)){
            System.out.println("Question already exist in the bank.");
        }else{
            questionSet.add(newQuestion);
            System.out.println("Question added successfully.");
        }
    }

    @Override
    public void displayQuestions(boolean answerQuestions) {
        if (questionSet.isEmpty()) {
            System.out.println("No question available.");
        } else {
            correctAnswers = 0;
            Scanner userInput = new Scanner(System.in);
            for (Question quizz : questionSet) {
                System.out.println(quizz.getQuestionText());
                String[] choices = quizz.getChoices();
                for (int i = 0; i < choices.length; i++) {
                    System.out.println((char) ('A' + i) + ": " + choices[i]);
                }
                System.out.println();
                // If the answerQuestions flag is true, prompt for the answer
                if (answerQuestions) {
                    System.out.println("Enter your answer (A, B, C, D): ");
                    String userChoice = userInput.nextLine().trim();
                    // Validate and check if the answer is correct
                    if(validateAnswer(userChoice, quizz.getCorrectAnswer())){
                        System.out.println("Correct!\n");
                        correctAnswers++;
                    }else{
                        System.out.println("Incorrect. The correct answer was " + quizz.getCorrectAnswer() + ".\n");
                    }
                }
            }
        }
    }

    @Override
    boolean validateAnswer(String userAnswer, char correctAnswer) {
        // Check if the user answer matches the correct answer
        if(!userAnswer.matches("[A-Da-d]")){
            System.out.println("Invalid input. Only A, B, C, or D are accepted.");
            return false;
        }
        return userAnswer.equalsIgnoreCase(String.valueOf(correctAnswer));
    }

    @Override
    void displayResults(String userName, double totalQuestions, double correctAnswers) {
        double scorePercentage = (correctAnswers/totalQuestions) * 100;
        System.out.println(userName +" scored: " +scorePercentage+ "%.");
    }

    @Override
    public void compareAndDisplayRank(List<Double> previousScores, double currentScore) {
        // Sort previous scores
        Collections.sort(previousScores);
        // Determine the rank of the current score
        int rank = 1;
        for(Double score : previousScores){
            if(currentScore < score){
                rank++;
            }
        }
        System.out.println("Your rank is: " + rank + " out of " + (previousScores.size() + 1) + " test takers.");
    }

    @Override
    public void answerQuestions() {
        totalQuestions = questionSet.size();
        displayQuestions(true);
    }
}
