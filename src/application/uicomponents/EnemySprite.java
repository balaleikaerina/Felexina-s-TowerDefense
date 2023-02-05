package application.uicomponents;

import application.gamelogic.GameObject;
import application.gamelogic.enemy.Enemy;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;


public abstract class EnemySprite extends VBox implements Sprite {
    private ImageView imageView;
    private ProgressBar enemyHealthBar;
    private Image enemyImage;
    private SimpleObjectProperty<GameObject> enemyObject;
    private final double ENEMY_SIZE = 60;

    public EnemySprite(String enemyImage) {
        //this.setBackground(new Background(new BackgroundFill(Color.web("#181818"), null, null)));
        imageView = new ImageView();
        this.enemyImage = new Image(enemyImage);
        imageView.setImage(this.enemyImage);
        imageView.setFitHeight(ENEMY_SIZE);
        imageView.setFitWidth(ENEMY_SIZE);
        //imageView.setX((this.getPrefWidth() / 2) - (ENEMY_SIZE / 2));
        //imageView.setY((this.getPrefHeight() / 2) - (ENEMY_SIZE / 2));
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);

        enemyHealthBar = new ProgressBar(1);
        enemyHealthBar.setStyle("-fx-accent: red");
        enemyHealthBar.setPrefWidth(60);
        enemyHealthBar.setMinWidth(60);
        enemyHealthBar.setMaxWidth(60);
        enemyHealthBar.setPrefHeight(10);
        enemyHealthBar.setMinHeight(10);
        enemyHealthBar.setMaxHeight(10);


        this.getChildren().addAll(enemyHealthBar, imageView);
        enemyObject = new SimpleObjectProperty<GameObject>();
    }

    @Override
    public void render() {
        if(enemyObject.getValue() != null) {
            GameObject e = enemyObject.get();
            this.setLayoutX(e.getX() + this.getWidth() / 2);
            this.setLayoutY(e.getY() - this.getHeight() / 2);
            enemyHealthBar.setProgress(((double)((Enemy)enemyObject.getValue()).getHp() / (double)((Enemy)enemyObject.getValue()).getMaxHp()));
            //System.out.println(((double)((Enemy)enemyObject.getValue()).getHp()));
        }
    }

    @Override
    public void render(double delta) {

    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {
        return enemyObject;
    }

}
