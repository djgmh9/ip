package gui;

import java.io.IOException;

import frenny.Frenny;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Frenny using FXML.
 */
public class Main extends Application {

    private static final String PROJECT_DIR = System.getProperty("user.dir");
    private static final String FILE_PATH = PROJECT_DIR + "data/frenny.txt";
    private Frenny frenny = new Frenny(FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            // Set minimum window size
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFrenny(frenny); // inject the Frenny instance
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
