package application.uicomponents;

import application.gamelogic.GameObject;
import application.gamelogic.enemy.Enemy;
import application.gamelogic.tower.Tower;
import javafx.animation.PathTransition;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class ArcaneTowerSprite extends TowerSprite {

    private ImageView imageView;
    private Image towerImage;
    private Circle rangeIndicator;
    private Button upgradeButton;
    private Image upgradeButtonImg;
    private SimpleObjectProperty<GameObject> towerObject;
    private final double TOWER_SIZE = 150;
    private boolean rangeIndicatorOn = false;
    private double timeTillRender = 0;

    public ArcaneTowerSprite(Tower tower) {
        super(tower, "./resources/arcaneTower.png");
    }
}
