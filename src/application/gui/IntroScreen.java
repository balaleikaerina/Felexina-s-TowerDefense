package application.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import javax.swing.text.Element;
import javax.swing.text.html.ImageView;
import java.io.FileInputStream;


public class IntroScreen extends StackPane {


    @FXML
    public StackPane introBackgroundPane;

    @FXML
    public Label introText;

    public IntroScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IntroScreen.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            this.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public StackPane getIntroBackgroundPane() {
        return introBackgroundPane;
    }

}
