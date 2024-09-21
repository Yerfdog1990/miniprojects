import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static FileManager fileManager = new FileManager();
    private static QuestionBank questionBank = new QuestionBank();
    private static List<Double> previousScores = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Load questions and scores from files
        questionBank.questionSet = fileManager.loadQuestions();
        previousScores = fileManager.loadScores();
        if (previousScores == null) {
            previousScores = new ArrayList<>();
        }

        // Main menu for the application
        boolean exit = false;
        while (!exit) {
            System.out.println("\n--- Question Bank Application ---");
            System.out.println("1. Add Questions");
            System.out.println("2. Answer Questions");
            System.out.println("3. View Results and Compare Rank");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addQuestion();
                    break;
                case "2":
                    answerQuestions();
                    break;
                case "3":
                    viewResultsAndCompareRank();
                    break;
                case "4":
                    exit = true;
                    saveData();
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    // Method to add a new question to the question bank
    private static void addQuestion() {
        System.out.print("Enter the question: ");
        String questionText = scanner.nextLine();

        String[] choices = new String[4];
        for (int i = 0; i < 4; i++) {
            System.out.print("Enter choice " + (char) ('A' + i) + ": ");
            choices[i] = scanner.nextLine();
        }

        System.out.print("Enter the correct answer (A, B, C, D): ");
        char correctAnswer = scanner.nextLine().trim().toUpperCase().charAt(0);

        // Add the question to the QuestionBank
        questionBank.addQuestion(questionText, choices, correctAnswer);
    }

    // Method to simulate answering questions
    private static void answerQuestions() {
        if (questionBank.questionSet.isEmpty()) {
            System.out.println("No questions available to answer.");
            return;
        }

        questionBank.answerQuestions();  // Reuse method from QuestionBank
    }

    // Method to view results and compare rank
    private static void viewResultsAndCompareRank() {
        if (questionBank.questionSet.isEmpty()) {
            System.out.println("No questions available.");
            return;
        }

        System.out.print("Enter your name: ");
        String userName = scanner.nextLine().trim();

        double totalQuestions = questionBank.questionSet.size();
        double correctAnswers = questionBank.getCorrectAnswers();

        // Display results
        questionBank.displayResults(userName, totalQuestions, correctAnswers);

        // Calculate score and compare rank
        double currentScore = (correctAnswers / totalQuestions) * 100;
        questionBank.compareAndDisplayRank(previousScores, currentScore);

        // Save the current score
        previousScores.add(currentScore);
        fileManager.saveScores(previousScores);
    }

    // Method to save data before exiting
    private static void saveData() {
        fileManager.saveQuestions(questionBank.questionSet);
        fileManager.saveScores(previousScores);
    }
}
