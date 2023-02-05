package application.gui;

import application.launcher.levels.level1.Level1Controller;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import java.io.File;

public class EndScreenController {

    private EndScreen endScreen;
    private File musicPath;
    private MediaPlayer mediaPlayer;

    public EndScreenController() {
        endScreen = new EndScreen();
        initialize();
    }

    public EndScreen getEndScreen() {
        return endScreen;
    }

    public void displayTitle(String title) {
        endScreen.setHeaderTitle(title);
    }

    public void setEnemys_killed(String input) {
        endScreen.setEnemys_killed(input);
    }

    public void setHP_left(String input) {
        endScreen.setHp_left(input);
    }

    public void setTowers_placed(String input) {
        endScreen.setTowers_placed(input);
    }

    public void setBackground(int i) {
        switch (i) {
            case 0:
                endScreen.setBackgroundCSS("backgroundlvl1");
                break;
            case 1:
                endScreen.setBackgroundCSS("backgroundlvl2");
                break;
            case 2:
                endScreen.setBackgroundCSS("backgroundlvl3");
                break;
        }
    }

    public void setHeaderImage(int i) {
        switch (i) {
            case 0:
                endScreen.setHeaderImage("youWin");
                musicPath = new File("src/resources/music/win.mp3");
                break;
            case 1:
                endScreen.setHeaderImage("youLost");
                musicPath = new File("src/resources/music/lose.mp3");
                break;
        }
    }

    public void setBackgroundBlur(String input) {
        endScreen.setBackgroundBlur(input);
    }

    public void initialize() {
        endScreen.getMainmenuButton().addEventHandler(ActionEvent.ACTION, event -> {
            mediaPlayer.stop();

            LevelSelectController controller = new LevelSelectController();
            Scene scene = new Scene(controller.getLevelSelect());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        });

        endScreen.getPlayagainButton().addEventHandler(ActionEvent.ACTION, event -> {
            mediaPlayer.stop();

            Level1Controller controller = new Level1Controller();
            Scene scene = new Scene(controller.getMainView());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        });
    }

    public void startMusic() {
        Media media = new Media(musicPath.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
}
