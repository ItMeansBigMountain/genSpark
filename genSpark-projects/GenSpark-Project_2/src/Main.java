import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {
    public static void main(String[] args) {
        System.out.println("NUMBER GUESSING GAME");

        //INIT LIVES & GUESSES
        int default_amount_of_lives = 6;
        int lives = default_amount_of_lives;
        int count_of_guesses = 0;
        int guess;

        // INIT RANDOM NUMBER
        Random number_generator_obj = new Random();
        int secret = number_generator_obj.ints(1, 20).findFirst().getAsInt();


        //INIT USER-NAME
        Scanner myObj = new Scanner(System.in);
        System.out.print("Hello! What is your name?\n>\t");
        String userName = myObj.nextLine();
        System.out.println(String.format("Well, %s, I am thinking of a number between 1 and 20.", userName));


        // GAME LOOP
        while (lives > 0) {

            //USER INPUT & VALIDATION
            guess = guess_input(myObj);


            // GUESSING LOGIC
            if (guess > secret) {
                System.out.println("Your guess is too high! ");
                lives--;
                count_of_guesses++;
            } else if (guess < secret) {
                System.out.println("Your guess is too low... ");
                lives--;
                count_of_guesses++;
            } else {
                //WINNER
                count_of_guesses++;
                System.out.println(String.format("Good job, %s! you guessed my number in %s guesees!", userName, count_of_guesses));

                //RESTART GAME
                System.out.print("Would you like to play again? (y/n) \n>\t");
                String restart = myObj.next();
                if (restart.toLowerCase().startsWith("y")) {
                    secret = number_generator_obj.ints(1, 20).findFirst().getAsInt();
                    lives = default_amount_of_lives;
                    count_of_guesses = 0;
                } else {
                    System.out.println("GOODBYE.");
                    break;
                }
            }

            //GAME OVER
            if (lives == 0) {

                //RESTART GAME
                    System.out.println("OUT OF LIVES!");
                System.out.print("Would you like to play again? (y/n) \n>\t");
                String losing_restart = myObj.next();
                if (losing_restart.toLowerCase().startsWith("y")) {
                    secret = number_generator_obj.ints(1, 20).findFirst().getAsInt();
                    lives = default_amount_of_lives;
                    count_of_guesses = 0;
                } else {
                    System.out.println("GOODBYE.");
                    break;
                }
            }


        }


    }

    public static int guess_input(Scanner input_scanner) {
        int guess = -999;
        String USER_INPUT;
        System.out.print("\nTake a guess.\n>\t");
        boolean validation = false;
        while (!validation) {
            USER_INPUT = input_scanner.next();
            validation = NumberGuessValidation.guess_input_validation(USER_INPUT);
        }
        if (validation) {
            return guess;
        } else {
            return 0;
        }
    }

}