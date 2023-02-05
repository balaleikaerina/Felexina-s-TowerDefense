package application.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class LevelSelect extends Pane {

    @FXML
    private Button settingsButton;
    @FXML
    public Button exitButton;
    @FXML
    public Button level1Button;
    @FXML
    private Button level2Button;
    @FXML
    private Button level3Button;

    public Button getCreditsButton() {
        return creditsButton;
    }

    @FXML
    private Button creditsButton;

    public LevelSelect() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LevelSelect.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            this.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




