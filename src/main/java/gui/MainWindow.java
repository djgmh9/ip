package gui;

import frenny.Frenny;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Frenny frenny;

    private Image frennyImage = new Image(this.getClass().getResourceAsStream("/images/frenny.png"));
    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the frenny instance */
    public void setFrenny(Frenny d) {
        frenny = d;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing frenny's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = frenny.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getFrennyDialog(response, frennyImage)
        );
        userInput.clear();
    }
}
