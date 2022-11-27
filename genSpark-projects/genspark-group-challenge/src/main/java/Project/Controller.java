package Project;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Authors: Affan Fareed, Alonso del Arte, Jacob Stout, Kevin Drake, Moe Yassin, Setevn Lofquist
 * Controller Class for FXML File
 */
public class Controller {
    /**
     * List<String> sortedList = List from File of sorted Data
     * ArrayList<Text> nodes = for every String in sortedList, Create a Text object to have a node to store in Grid
     * GridPane gridPane = Pulled From FXML File
     */
    List<String> sortedList;
    ArrayList<Text> nodes;
    @FXML
    GridPane gridPane;

    /**
     * Creates List of Nodes, For every String, Create a Text Object, then add to GridPane.
     * Y counts up by 1 every Iteration(making Rows) x counts up every ten (Creates Columns)
     *
     * @param path, File Path
     *              no Return, Prints Grid of Data in Column Wise Format
     */
    @FXML
    public void displayList(String path) {
        nodes = new ArrayList<>();
        try {
            sortedList = SortInput.sortInput(path).stream().toList();
            for (String s : sortedList) {
                nodes.add(new Text("  " + s + "  "));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        gridPane.setGridLinesVisible(true);
        gridPane.setPadding(new Insets(2, 5, 2, 5));
        int x = 0, y = 0;
        for (int i = 0; i < nodes.size(); i++) {
            gridPane.add(nodes.get(i), x, y);
            y++;
            if (y % 10 == 0) {
                x++;
                y = 0;
            }
        }
    }

    /**
     * Opens File Explorer, allows you to Choose File to Sort.
     *
     * @return path, String value of Path to file
     */
    public String openFileExplorer() {
        FileChooser fc = new FileChooser();
        File selectedFile = fc.showOpenDialog(null);
        System.out.println(selectedFile.getAbsoluteFile());
        displayList(String.valueOf(selectedFile.getAbsoluteFile()));
        return "";
    }
}
