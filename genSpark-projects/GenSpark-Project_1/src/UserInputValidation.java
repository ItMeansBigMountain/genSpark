


public class UserInputValidation {

    public static boolean input_validation(String USER_INPUT) {
        boolean validation = false;
        try {
            Integer.valueOf(USER_INPUT);
            validation = true;
        } catch (NumberFormatException e) {
            validation = false;
        }
        return validation;
    }


}
