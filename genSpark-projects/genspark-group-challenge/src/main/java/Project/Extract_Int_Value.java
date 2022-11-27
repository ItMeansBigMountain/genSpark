package Project;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * Authors: Affan Fareed, Alonso del Arte, Jacob Stout, Kevin Drake, Moe Yassin, Setevn Lofquist
 * Class calls a URL to get the Literal Numeric Value of String value of Integer.
 */
public class Extract_Int_Value {
    /**
     * Calls in a String version of an Integer, Uses Internet to Find Numeric Value of Word
     *
     * @param numerical_word, String version of number (example: "One", "Two Hundred and Three"
     * @return int, Actual Integer Value of word called
     * @throws IOException, File Exception
     */
    public static int extract_html(String numerical_word) throws IOException {
        //SET UP CONNECTION
        String USER_AGENT_ID = "Java/" + System.getProperty("java.version");
        URL url = new URL("https://coolconversion.com/math/words-to-numbers-converter/_" + numerical_word + "_in-numbers");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", USER_AGENT_ID);
        con.setRequestMethod("GET");

        // EXTRACT HTML
        InputStream stream = (InputStream) con.getContent();
        Scanner scanner = new Scanner(stream);
        String line;
        String output = "";

        // PARSE HTML AND FIND NUMBER
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            if (line.contains("id=\"result\" class=\"verbose\"")) {
                output = line.split("<span class='big'>")[1].split("<")[0];
                break;
            }
        }
        return Integer.parseInt(output);
    }
}
