import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NumberGuessValidationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void guess_input_validation() {
        boolean expected = false;
        boolean actual1 = NumberGuessValidation.guess_input_validation("NEVER!");
        boolean actual2 = NumberGuessValidation.guess_input_validation("50");
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }
}