import Project.Extract_Int_Value;
import Project.SortInput;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Authors: Affan Fareed, Alonso del Arte, Jacob Stout, Kevin Drake, Moe Yassine, Steven Lofquist
 * Test Class for group project: challenge one
 */
class Group_Project_TEST {
    /**
     * Test 1: Tests a single-word numeric value
     * Test 2: Tests a multi-word numeric value
     * Param: String value of Integer
     *
     * @return Integer, the number from the String value of the number.
     * @throws IOException, File Exception
     */
    @Test
    void testHTMLExtractInt() throws IOException {
        assertEquals(10, Extract_Int_Value.extract_html("ten"), "Extract Int Test 1: Failed");
        assertEquals(501, Extract_Int_Value.extract_html("Five hundred and one"), "Extract Int Test 2: Failed");
    }

    /**
     * Test One: Checks to see if list is not null
     * Test Two: Checks to see if list contains same element as expected
     * Test Three: Checks file size
     * Param:  file of random strings stored as list
     *
     * @return boolean test one, boolean test two, boolean test three
     * @throws IOException, File Exception
     */
    @Test
    void testSortInputMethod() throws IOException {
        List<String> list = SortInput.sortInput("TextInput");
        assertEquals(true, list != null, "Sorted test one: failed");
        assertEquals("Three", list.get(2), "Sorted test two: failed");
        int size = list.size();
        assertEquals(size, SortInput.sortInput("TextInput").size(), "Sorted test three: failed");
    }

}