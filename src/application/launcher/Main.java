package application.launcher;

import application.gui.EndScreenController;
import application.gui.IntroScreenController;
import application.gui.LevelSelectController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {

            IntroScreenController controller = new IntroScreenController();
            Scene scene = new Scene(controller.getIntroScreen());

            primaryStage = stage;
            primaryStage.setScene(scene);
            primaryStage.show();


            primaryStage.setOnCloseRequest(event -> {
                event.consume();
                try {
                    logout();
                } catch (Exception e) {
                    System.out.println("Exception occurred when closing down: " + e);
                }
            });
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void logout() throws Exception {
        this.stop();
        Platform.exit();
        System.exit(0);
    }
}
