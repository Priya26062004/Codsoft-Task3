import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class QuizApplication {
    private static Map<Integer, Map<String, Object>> quizData = new HashMap<>();
    private static int totalQuestions;
    private static int userScore = 0;
    private static int currentQuestion = 1;

    public static void main(String[] args) {
        initializeQuizData();
        totalQuestions = quizData.size();

        while (currentQuestion <= totalQuestions) {
            displayQuestion(currentQuestion);
            setTimer();
            Scanner scanner = new Scanner(System.in);
            String userAnswer = scanner.nextLine();
            submitAnswer(userAnswer, currentQuestion);
        }

        displayResult();
    }

    private static void initializeQuizData() {
        Map<String, Object> question1 = new HashMap<>();
        question1.put("question", "What is the capital of France?");
        question1.put("options", new String[]{"A. London", "B. Paris", "C. Rome", "D. Berlin"});
        question1.put("correctAnswer", "B");
        quizData.put(1, question1);

        // Add other questions similarly
    }

    private static void displayQuestion(int questionNumber) {
        Map<String, Object> question = quizData.get(questionNumber);
        String questionText = (String) question.get("question");
        String[] options = (String[]) question.get("options");

        System.out.println(questionText);
        System.out.println("Options:");
        for (String option : options) {
            System.out.println(option);
        }
    }

    private static void submitAnswer(String userAnswer, int questionNumber) {
        Map<String, Object> question = quizData.get(questionNumber);
        String correctAnswer = (String) question.get("correctAnswer");

        if (userAnswer.equalsIgnoreCase(correctAnswer)) {
            userScore++;
        }
        currentQuestion++;
    }

    private static void setTimer() {
        Timer timer = new Timer();
        int timerDuration = 10000; // Time limit per question in milliseconds

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up!");
                timer.cancel(); // Stop the timer
                currentQuestion++; // Move to the next question
            }
        }, timerDuration);
    }

    private static void displayResult() {
        System.out.println("Your Final Score: " + userScore + "/" + totalQuestions);
        // Display correct and incorrect answers summary
        // Add any additional summary information here
    }
}
