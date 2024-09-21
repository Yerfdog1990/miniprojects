import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FileManager {
    private static final String QUESTIONS_FILE = "questions.dat";
    private static final String SCORES_FILE = "score.data.txt";

    // Save the set of questions to a file
    public void saveQuestions(Set<Question> questions) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(QUESTIONS_FILE))) {
            out.writeObject(questions);
            System.out.println("Questions saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving questions: " + e.getMessage());
        }
    }

    // Load the set of questions from a file
    @SuppressWarnings("unchecked")
    public Set<Question> loadQuestions() {
        Set<Question> questions = new HashSet<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(QUESTIONS_FILE))) {
            questions = (Set<Question>) in.readObject();
            System.out.println("Questions loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Questions file not found. Starting with an empty question bank.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading questions: " + e.getMessage());
        }
        return questions;
    }

    // Save the list of scores to a file
    public void saveScores(List<Double> scores) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SCORES_FILE))) {
            out.writeObject(scores);
            System.out.println("Scores saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving scores: " + e.getMessage());
        }
    }

    // Load the list of scores from a file
    @SuppressWarnings("unchecked")
    public List<Double> loadScores() {
        List<Double> scores = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(SCORES_FILE))) {
            scores = (List<Double>) in.readObject();
            System.out.println("Scores loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("Scores file not found. Starting with an empty score list.");
            // Return an empty list if the file is not found
            return new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading scores: " + e.getMessage());
        }
        return scores;
    }
}
