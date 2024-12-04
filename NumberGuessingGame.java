import java.util.Scanner;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int roundsPlayed = 0;
        int roundsWon = 0;
        int totalAttemptsUsed = 0; // To calculate score based on attempts
        String playAgain;

        System.out.println("Welcome to the Number Guessing Game!");

        do {
            // Generate a random number between 1 and 100
            int secretNumber = random.nextInt(100) + 1;
            int attemptsLeft = 7; // 7 attempts per round
            int attemptsUsedInThisRound = 0; // Track attempts for this round
            boolean guessedCorrectly = false;

            System.out.println("\n--- New Round ---");
            System.out.println("Guess the number (between 1 and 100). You have 7 attempts!");

            // Loop for guesses
            while (attemptsLeft > 0) {
                System.out.print("Enter your guess (Attempts left: " + attemptsLeft + "): ");
                
                // Input validation
                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a valid number!");
                    scanner.next(); // Clear the invalid input
                    continue;
                }

                int guess = scanner.nextInt();
                attemptsUsedInThisRound++;
                totalAttemptsUsed++;

                if (guess == secretNumber) {
                    System.out.println("🎉 Correct! You've guessed the number.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (guess < secretNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }

                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.println("😞 You've used all attempts! The correct number was " + secretNumber + ".");
            }

            roundsPlayed++;

            // Display round summary
            System.out.println("Round summary:");
            System.out.println(" - Attempts used this round: " + attemptsUsedInThisRound);
            if (guessedCorrectly) {
                System.out.println(" - Result: Won ");
            } else {
                System.out.println(" - Result: Lost ");
            }

            // Ask if the user wants to play another round
            System.out.print("Do you want to play another round? (yes/no): ");
            playAgain = scanner.next().toLowerCase();
        } while (playAgain.equals("yes"));

        // Calculate average attempts per round
        double averageAttempts = roundsPlayed > 0 ? (double) totalAttemptsUsed / roundsPlayed : 0;

        // Display final score
        System.out.println("\n--- Game Over ---");
        System.out.println("Rounds played: " + roundsPlayed);
        System.out.println("Rounds won: " + roundsWon);
        System.out.printf("Average attempts used per round: %.2f%n", averageAttempts);
        System.out.printf("Win rate: %.2f%% %n", roundsPlayed > 0 ? (roundsWon * 100.0 / roundsPlayed) : 0);
        System.out.println("Thank you for playing! See you next time. 🎮");

        scanner.close();
    }
}
