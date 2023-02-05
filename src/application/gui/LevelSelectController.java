package application.gui;

import java.io.File;

import application.launcher.levels.level1.Level1Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class LevelSelectController {

    private LevelSelect levelSelect;

    public LevelSelectController() {
        levelSelect = new LevelSelect();
        initialize();
    }

    public LevelSelect getLevelSelect() {
        return levelSelect;
    }

    public void initialize() {
        File path = new File("src/resources/music/select.mp3");
        Media media = new Media(path.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        levelSelect.getCreditsButton().addEventHandler(ActionEvent.ACTION, event -> {
            mediaPlayer.stop();

            CreditsController controller = new CreditsController();
            Scene scene = new Scene(controller.getCredits());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        });

        levelSelect.exitButton.setOnMouseClicked(event -> {

            Stage stage = (Stage) levelSelect.exitButton.getScene().getWindow();
            stage.close();

        });


        levelSelect.level1Button.addEventHandler(ActionEvent.ACTION, event -> {
            mediaPlayer.stop();

            Level1Controller controller = new Level1Controller();
            Scene scene = new Scene(controller.getMainView());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        });




    }
}
