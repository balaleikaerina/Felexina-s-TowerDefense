package application.launcher.levels.level1;

import application.uicomponents.ImageButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class Level1View extends AnchorPane {

    @FXML
    private AnchorPane baseAnchorPane, containerViewPane;
    @FXML
    private Pane clickRegisterPane;
    @FXML
    private FlowPane bottomPane;
    @FXML
    private HBox bottomButtonBox, topLabelBox, bottomRightBox, towerButtonBox;
    @FXML
    private ImageView baseMapImage, hpIcon;
    @FXML
    private Label xLabel, hpStat, waveStat, goldLabel;



    private Button showRange, initUpgrade, placeArcaneTower, placeArcherTower, placeCannonTower, placeFireTower;
    private ImageButton spawnEnemy, towerRange, upgradeTower;
    private Image enemy1Img, spawnEnemyImg, towerRangeIndicatorImg, upgradeTowerImg, arcaneTowerImg, archerTowerImg, cannonTowerImg, fireTowerImg;
    private boolean isClickPaneTrans = false;

    public Level1View() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Level1.fxml"));
            loader.setRoot(this);
            loader.setController(this);
            loader.load();

            waveStat.setText("Wave: 0");
            waveStat.setStyle("-fx-text-fill: white");
            hpStat.setText("HP: 10");
            hpStat.setStyle("-fx-text-fill: white");
            goldLabel.setStyle("-fx-text-fill: white");

            spawnEnemyImg = new Image("./resources/images/nextwaveButton.png");
            spawnEnemy = new ImageButton(spawnEnemyImg, 40, 80);


            towerRangeIndicatorImg = new Image("./resources/images/towerRangeIndicatiorButton.png");
            towerRange = new ImageButton(towerRangeIndicatorImg, 40, 80);

            upgradeTowerImg = new Image("./resources/images/towerlevelupbutton.png");
            upgradeTower = new ImageButton(upgradeTowerImg, 40, 80);



            /* Old Buttons

            showRange = new Button();
            showRange.setText("Show Range");
            showRange.setPrefHeight(20);

            initUpgrade = new Button();
            initUpgrade.setText("Upgrade Tower");
            initUpgrade.setPrefHeight(20);

             */

            towerButtonBox.setBackground(new Background(new BackgroundFill(Color.web("#181818"), null, null)));

            arcaneTowerImg = new Image("./resources/images/tower1Button.png");
            placeArcaneTower = new ImageButton(arcaneTowerImg, 40, 80);


            archerTowerImg = new Image("./resources/images/tower2Button.png");
            placeArcherTower = new ImageButton(archerTowerImg, 40, 80);

            fireTowerImg = new Image("./resources/images/tower3Button.png");
            placeFireTower = new ImageButton(fireTowerImg, 40, 80);

            cannonTowerImg = new Image("./resources/images/tower4Button.png");
            placeCannonTower = new ImageButton(cannonTowerImg, 40, 80);


            /*
            placeArcaneTower = new Button("ArcaneTower");
            placeArcherTower = new Button("ArcherTower");
            placeCannonTower = new Button("CannonTower");
            placeFireTower = new Button("FireTower"); */

            towerButtonBox.setAlignment(Pos.CENTER);
            towerButtonBox.getChildren().addAll(placeArcaneTower, placeArcherTower, placeCannonTower, placeFireTower);
            towerButtonBox.setSpacing(10);


            topLabelBox.setBackground(new Background(new BackgroundFill(Color.web("#181818"), null, null)));

            enemy1Img = new Image("./resources/testEnemy1.png");

            clickRegisterPane.setMouseTransparent(false);


            bottomPane.setBackground(new Background(new BackgroundFill(Color.web("#181818"), null, null)));

            bottomButtonBox.setAlignment(Pos.CENTER_LEFT);
            bottomButtonBox.getChildren().addAll(towerRange, upgradeTower);
            bottomButtonBox.setSpacing(10);

            bottomRightBox.getChildren().addAll(spawnEnemy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public AnchorPane getBaseAnchorPane() {
        return baseAnchorPane;
    }

    public Pane getClickRegisterPane() {
        return clickRegisterPane;
    }

    public boolean setClickRegisterPaneTransparent() {
        isClickPaneTrans = !isClickPaneTrans;
        clickRegisterPane.setMouseTransparent(isClickPaneTrans);
        return isClickPaneTrans;
    }

    public ImageButton getSpawnEnemy() {
        return spawnEnemy;
    }

    public ImageButton getTowerRange() {
        return towerRange;
    }

    public ImageButton getUpgradeTower() {
        return upgradeTower;
    }

    public Label getxAndYLabel() {
        return xLabel;
    }
  
    public void setHpStat(String hpStat) {
        this.hpStat.setText(hpStat);
    }

    public void setWaveStat(String waveStat) {
        this.waveStat.setText(waveStat);
    }

    public void setGoldLabel(String goldVal) {
        goldLabel.setText(goldVal);
    }

    //Old Button
    public Button getShowRange() {
        return showRange;
    }

    public Button getInitUpgrade() {
        return initUpgrade;
    }

    public Label getHpStat() {
        return hpStat;
    }

    public Button getPlaceArcaneTower() {
        return placeArcaneTower;
    }

    public Button getPlaceArcherTower() {
        return placeArcherTower;
    }

    public Button getPlaceCannonTower() {
        return placeCannonTower;
    }

    public Button getPlaceFireTower() {
        return placeFireTower;
    }
}
