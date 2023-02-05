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

public abstract class TowerSprite extends AnchorPane implements Sprite{
    private ImageView imageView;
    private Image towerImage;
    private Circle rangeIndicator;
    private Button upgradeButton;
    private Image upgradeButtonImg;
    private SimpleObjectProperty<GameObject> towerObject;
    private final double TOWER_SIZE = 150;
    private boolean rangeIndicatorOn = false;
    private double timeTillRender = 0;

    public TowerSprite(Tower tower, String path) {
        this.setPrefHeight(tower.getRange() * 2);
        this.setPrefWidth(tower.getRange() * 2);
        this.setLayoutX(tower.getX() - this.getPrefWidth() / 2);
        this.setLayoutY(tower.getY()  - this.getPrefWidth() / 2);
        this.setPickOnBounds(false);
        this.getStylesheets().add(getClass().getResource("../application.css").toExternalForm());
        //this.setMouseTransparent(true);
        //this.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
        //System.out.println("TowerSprite placed on LayoutX: " + this.getLayoutX() + " || LayoutY: " + this.getLayoutY());

        towerImage = new Image(path);
        imageView = new ImageView(towerImage);
        imageView.setImage(towerImage);
        imageView.setFitHeight(TOWER_SIZE);
        imageView.setFitWidth(TOWER_SIZE);
        imageView.setPreserveRatio(true);
        imageView.setPickOnBounds(true);
        imageView.setX((this.getPrefWidth() / 2) - (TOWER_SIZE / 2));
        imageView.setY((this.getPrefHeight() / 2) - (TOWER_SIZE / 2));

        rangeIndicator = new Circle(tower.getRange(), Color.web("rgba(232, 232, 232, 0.00)"));
        rangeIndicator.setStroke(Color.web("rgba(84, 84, 84, 0.00)"));
        rangeIndicator.setStrokeWidth(3);
        rangeIndicator.setLayoutX((this.getPrefHeight() / 2));
        rangeIndicator.setLayoutY((this.getPrefHeight() / 2));
        rangeIndicator.setMouseTransparent(true);

        upgradeButton = new Button();
        upgradeButton.getStyleClass().add("upgradeButton");
        upgradeButton.setText(tower.getLevel() + " -> " + (tower.getLevel() + 1));
        upgradeButton.setOpacity(0);
        upgradeButton.setLayoutX((this.getPrefWidth() / 2)  - (upgradeButton.getWidth() / 2));
        upgradeButton.setLayoutY(0);
        //upgradeButton.setMouseTransparent(false);

        towerObject = new SimpleObjectProperty<GameObject>(tower);
        this.getChildren().addAll(rangeIndicator, imageView, upgradeButton);
    }

    @Override
    public void render() {

    }

    @Override
    public void render(double delta) {
        //only update once a second to save comp time
        if(towerObject != null) {
            GameObject e = towerObject.get();
            this.setPrefHeight(((Tower)e).getRange() * 2);
            this.setPrefWidth(((Tower)e).getRange() * 2);
            this.setLayoutX(e.getX() - this.getPrefWidth() / 2);
            this.setLayoutY(e.getY() - this.getPrefHeight() / 2);
            imageView.setX((this.getPrefWidth() / 2) - (TOWER_SIZE / 2));
            imageView.setY((this.getPrefHeight() / 2) - (TOWER_SIZE / 2));
            rangeIndicator.setRadius(((Tower)e).getRange());
            rangeIndicator.setLayoutX((this.getPrefHeight() / 2));
            rangeIndicator.setLayoutY((this.getPrefHeight() / 2));
            upgradeButton.setLayoutX((this.getPrefWidth() / 2) - (upgradeButton.getWidth() / 2));
            upgradeButton.setLayoutY(imageView.getY() - 20);
        }

    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {
        return towerObject;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setRangeIndicator() {
        if(rangeIndicatorOn) {
            rangeIndicatorOn = !rangeIndicatorOn;
            rangeIndicator.setStroke(Color.web("rgba(84, 84, 84, 0.00)"));
        } else {
            rangeIndicatorOn = !rangeIndicatorOn;
            rangeIndicator.setStroke(Color.web("rgba(84, 84, 84, 0.81)"));
        }
    }

    public Button getUpgradeButton() {
        return upgradeButton;
    }

    public void showUpgradeButton() {
        upgradeButton.setOpacity(1);
    }

    public void hideUpgradeButton() {
        upgradeButton.setOpacity(0);
    }

    public void setUpgradeButtonText(int currentLevel, int maxLevel) {
        if(currentLevel + 1 < maxLevel) {
            upgradeButton.setText(currentLevel + " -> " + (currentLevel + 1));
        } else if(currentLevel + 1 == maxLevel) {
            upgradeButton.setText(currentLevel + " -> " + maxLevel);
        } else {
            upgradeButton.setText("Max Level");
        }
    }
}
