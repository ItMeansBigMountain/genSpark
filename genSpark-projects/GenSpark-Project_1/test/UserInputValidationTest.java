import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserInputValidationTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void input_validation() {
        boolean expected = false;
        boolean actual = UserInputValidation.input_validation("NEVER!");
        assertEquals(expected , actual);
    }
}