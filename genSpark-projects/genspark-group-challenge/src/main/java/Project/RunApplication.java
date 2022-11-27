package Project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Authors: Affan Fareed, Alonso del Arte, Jacob Stout, Kevin Drake, Moe Yassin, Setevn Lofquist
 * Class to run FXML to Display the GUI
 */
public class RunApplication extends Application {
    /**
     * Extends Application, to use Start method. To display GUI
     *
     * @param primaryStage, main Stage to Display
     * @throws IOException, File Exception
     */
    public void start(Stage primaryStage) throws IOException {
        URL fxmlLocation = getClass().getResource("Group_Project.fxml");
        FXMLLoader fxml = new FXMLLoader(fxmlLocation);
        Scene scene = new Scene(fxml.load());
        primaryStage.setTitle("Challenge 1");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * static Main Method to run Args
     *
     * @param args, all data in start method
     */
    public static void main(String[] args) {
        launch(args);
    }
}
