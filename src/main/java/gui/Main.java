package gui;

import java.io.IOException;

import frenny.Frenny;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import ui.Ui;

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
            stage.setTitle("Frenny");
            // Set minimum window size
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFrenny(frenny); // inject the Frenny instance
            stage.show();
            // Show welcome message
            fxmlLoader.<MainWindow>getController().handleWelcomeMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        Ui.showOutro();
        // Optionally, you can add a delay before closing the application to allow the user to read the goodbye message.
        PauseTransition delay = new PauseTransition(Duration.seconds(2));
        delay.setOnFinished(event -> Platform.exit());
        delay.play();
    }
}
