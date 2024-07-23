import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Task4 {
    private String[][] questions = {
        {"Which data structure uses LIFO (Last In First Out)?", "Stack", "Queue", "Array", "Linked List", "Stack"},
        {"What is the time complexity of binary search?", "O(n)", "O(log n)", "O(n^2)", "O(1)", "O(log n)"},
        {"Which of the following is a programming language?", "HTML", "CSS", "Python", "SQL", "Python"}
    };

    private int current = 0;
    private int score = 0;
    private int timeLeft = 10; // Time per question in seconds
    private Timer timer;
    private boolean answered = false;

    public static void main(String[] args) {
        Task4 quiz = new Task4();
        quiz.startQuiz();
    }

    private void startQuiz() {
        Scanner scanner = new Scanner(System.in);

        while (current < questions.length) {
            System.out.println("Question " + (current + 1) + ": " + questions[current][0]);
            for (int i = 1; i <= 4; i++) {
                System.out.println(i + ". " + questions[current][i]);
            }
            
            startTimer();

            while (!answered) {
                if (scanner.hasNextInt()) {
                    int answer = scanner.nextInt();
                    if (answer >= 1 && answer <= 4) {
                        checkAnswer(answer);
                        answered = true;
                    } else {
                        System.out.println("Please enter a number between 1 and 4.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a number between 1 and 4.");
                    scanner.next();
                }
            }

            current++;
            answered = false;
        }

        displayResults();
        scanner.close();
    }

    private void startTimer() {
        timer = new Timer();
        timeLeft = 10;
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                timeLeft--;
                if (timeLeft <= 0) {
                    System.out.println("Time's up!");
                    answered = true;
                    timer.cancel();
                }
            }
        }, 1000, 1000);
    }

    private void checkAnswer(int answer) {
        timer.cancel();
        if (questions[current][answer].equals(questions[current][5])) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Incorrect! The correct answer is: " + questions[current][5]);
        }
        System.out.println();
    }

    private void displayResults() {
        System.out.println("Quiz Completed!");
        System.out.println("Your final score: " + score + " out of " + questions.length);
    }
}
