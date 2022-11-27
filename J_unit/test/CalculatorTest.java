import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    //THIS FILE WILL TEST ALL METHODS ON CALCULATOR OBJECT CLASS
        // PROCEDURE
            // create class in src
            // create module "./test" in project folder
            // right click on class object to be tested
                //choose generate
                    //make sure J-unit5 module is added to external libraries
                    //choose @setup / @teardown
                    //choose methods()


    @BeforeEach
    void setUp() {
        //before test runs
    }


    @AfterEach
    void tearDown() {
        //runs after tests run
    }



    //SYNTAX TO TEST A METHOD FROM CLASS OBJECT
    @Test                                         //Testing decorator
    void add() {
        int expected = 2;                         //expected return value
        int actual = Calculator.add(1, 1);  // METHOD TO BE TESTED
        assertEquals(expected, actual);           // assert method for testing
    }



    @Test
    void sub() {
        int expected = 0;
        int actual = Calculator.sub(1, 1);
        assertEquals(expected, actual);
    }



}