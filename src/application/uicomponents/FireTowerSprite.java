package application.uicomponents;

import application.gamelogic.GameObject;
import application.gamelogic.tower.Tower;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FireTowerSprite extends TowerSprite {
    private ImageView imageView;
    private Image towerImage;
    private Circle rangeIndicator;
    private Button upgradeButton;
    private SimpleObjectProperty<GameObject> towerObject;
    private final double TOWER_SIZE = 150;
    private boolean rangeIndicatorOn = false;
    private double timeTillRender = 0;

    public FireTowerSprite(Tower tower) {
        super(tower, "./resources/fireTowerTower.png");
    }
}
