import java.util.InputMismatchException;

public class NumberGuessValidation {
    public static boolean guess_input_validation(String USER_INPUT) {
        int guess;
        boolean validation;
        try {
            guess = Integer.parseInt(USER_INPUT);
            if (guess >= 1 && guess <= 20) {
                validation = true;
            } else {
                System.out.println("Please choose number between 1 - 20... ");
                System.out.print(">\t");
                validation = false;
            }
        } catch (InputMismatchException e) {
            System.out.print("Please enter valid whole number from 1 - 20:\n>\t");
            validation = false;
        } catch (NumberFormatException e) {
            System.out.print("Please enter valid whole number from 1 - 20:\n>\t");
            validation = false;
        }

        return validation;
    }
}
