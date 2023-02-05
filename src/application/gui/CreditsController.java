package application.gui;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CreditsController {

    private Credits credits;

    public CreditsController() {
        credits = new Credits();
        initialize();
    }

    public Credits getCredits() {
        return credits;
    }

    public void initialize() {

        credits.getGoBack().addEventHandler(ActionEvent.ACTION, event -> {
            LevelSelectController controller = new LevelSelectController();
            Scene scene = new Scene(controller.getLevelSelect());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        });

    }
}
