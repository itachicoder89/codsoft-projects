import java.util.Random;
import java.util.Scanner;

public class EncouragingNumberGuessingGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Number Guessing Game");
        System.out.println("--------------------");

        int min = 1;
        int max = 100;

        Random random = new Random();

        int randomNumber = random.nextInt(max - min + 1) + min;

        int maxAttempts = 10;
        int numberOfGuesses = 0;

        System.out.println("I've generated a random number between " + min + " and " + max + ".");
        System.out.println("Try to guess the number in " + maxAttempts + " attempts!");

        while (numberOfGuesses < maxAttempts) {
            System.out.print("Enter your guess: ");
            int userGuess = scanner.nextInt();
            numberOfGuesses++;

            if (userGuess == randomNumber) {
                System.out.println("Congratulations! You've guessed the number " + randomNumber + " correctly in " + numberOfGuesses + " guesses.");
                break;
            } else if (userGuess < randomNumber) {
                System.out.println("Your guess is too low. Try a higher number.");
                printEncouragement();
            } else {
                System.out.println("Your guess is too high. Try a lower number.");
                printEncouragement();
            }
        }

        if (numberOfGuesses == maxAttempts) {
            System.out.println("Sorry, you've reached the maximum number of attempts. The correct number was " + randomNumber + ".");
        }

        scanner.close();
    }

    // Custom method to print encouraging messages
    private static void printEncouragement() {
        String[] encouragementMessages = {
            "You're getting closer!",
            "Keep going, you're doing great!",
            "Don't give up, the answer is close!",
            "You can do it! Try again.",
            "You're making progress!"
        };

        Random random = new Random();
        int index = random.nextInt(encouragementMessages.length);
        System.out.println(encouragementMessages[index]);
    }
}
