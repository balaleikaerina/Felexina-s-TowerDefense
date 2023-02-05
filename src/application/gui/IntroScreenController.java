package application.gui;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.util.Random;

public class IntroScreenController {

    private IntroScreen introScreen;


    public IntroScreenController() {
        introScreen = new IntroScreen();
        initialize();
    }

    public IntroScreen getIntroScreen() {
        return introScreen;
    }

    public void initialize() {
        File path = new File("src/resources/music/intro.mp3");
        Media media = new Media(path.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setAutoPlay(true);

        introScreen.getIntroBackgroundPane().getStyleClass().add(randomBackground());

        introScreen.getIntroBackgroundPane().setOnMouseClicked(event -> {
            mediaPlayer.stop();

            LevelSelectController controller = new LevelSelectController();
            Scene scene = new Scene(controller.getLevelSelect());

            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();

        });


        introScreen.setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case SPACE:
                    LevelSelectController controller = new LevelSelectController();
                    Scene scene = new Scene(controller.getLevelSelect());

                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(scene);
                    window.show();

                    break;
            }

        });
    }

    public String randomBackground() {

        Random rand = new Random();
        int n = rand.nextInt(3);
        String[] backgrounds = {"introBackground", "introBackground2", "introBackground3", "introBackground4"};
        return backgrounds[n];

    }



}
