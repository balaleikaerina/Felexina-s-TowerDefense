package application.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class Credits extends BorderPane {

    @FXML
    private Button goBack;

    public Credits() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Credits.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            this.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Button getGoBack() {
        return goBack;
    }

}
