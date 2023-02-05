package application.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class EndScreen extends Pane {

    @FXML
    private Pane background;

    @FXML
    private Button playagainButton;

    @FXML
    private Button mainmenuButton;

    @FXML
    private Label headerTitle;

    @FXML
    private Label enemys_killed;

    @FXML
    private Label hp_left;

    @FXML
    private Label towers_placed;

    @FXML
    private BorderPane backgroundblur;

    @FXML
    private Pane headerImage;

   

    public EndScreen () {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EndScreen.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            this.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHeaderTitle(String headerTitle) {
        this.headerTitle.setText(headerTitle);
    }

    public Label getHeaderTitle() {
        return headerTitle;
    }

    public Button getMainmenuButton() {
        return mainmenuButton;
    }

    public Button getPlayagainButton() {
        return playagainButton;
    }

    public void setBackgroundCSS(String backgroundCSS) {
        this.background.getStyleClass().add(backgroundCSS);
    }

    public void setBackgroundBlur(String input) {
        backgroundblur.setStyle("-fx-background-color: #" + input);
    }

    public void setEnemys_killed(String input) {
        enemys_killed.setText(input);
    }

    public void setHp_left(String input) {
        hp_left.setText(input);
    }

    public void setTowers_placed(String input) {
        towers_placed.setText(input);
    }

    public void setHeaderImage(String input) {
        headerImage.getStyleClass().add(input);
    }
}
