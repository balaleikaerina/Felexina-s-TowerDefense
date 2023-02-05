package application.gamelogic;

import application.gamelogic.tower.Tower;
import application.launcher.levels.Level;
import application.launcher.levels.level1.Level1;
import application.gamelogic.enemy.Enemy;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.LinkedList;

public class Game {
    private ArrayList<GameObject> gameObjects = new ArrayList<>();
    private Level currentLevel;
    private double aggregateTime = 0;
    private String towerChooser = "";

    public Game(int levelNumber) {

        //TODO: new with x180 || y180
        double[][] towerPlacementPlaces = { {0, 50}, {180, 50}, {360, 50}, {500, 100}, {620, 220}, {750, 100}, {890, 50}, {1250, 50}, {1450, 100},
                                            {350, 280}, {175, 280}, {0, 280}, {38, 437}, {30, 630}, {620, 400}, {890, 300}, {890, 480}, {870, 660},
                                            {250, 580}, {430, 580}, {610, 580}, {1170, 300}, {1320, 300}, {1320, 480}, {1320, 660},
                                            {1520, 260}, {1520, 520}, {1520, 700}};
        switch(levelNumber) {
            case 0:
                currentLevel = new Level1(gameObjects, towerPlacementPlaces);
                break;
            default:
                currentLevel = new Level1(gameObjects, towerPlacementPlaces);
                break;
        }
    }

    public void update(double delta) {
        aggregateTime += delta;
        //boolean isWaveFinished = currentLevel.getIsIsWaveFinished();
        for(int i = 0; i < gameObjects.size(); i++) {
            GameObject currObj = gameObjects.get(i);

            if ((currObj instanceof Enemy) && ((!((Enemy) currObj).isAlive()) || (((Enemy) currObj).isFinished()))) {
                //isWaveFinished = ((Enemy)currObj).isFinished();
                gameObjects.remove(currObj);
                if(!((Enemy) currObj).isFinished()) {
                    setPlayerGold(getPlayerGold() + 1);
                    setKillCounter();
                }

            } else {
                currObj.update(delta);
            }
        }

        if(aggregateTime > 1) {
            aggregateTime = 0;
            checkIfWaveIsFinished();
        }
    }

    public void add(GameObject object) {
        gameObjects.add(object);
    }

    public boolean enoughGoldForTower(String towerType) {
        int currentPlayerGold = getPlayerGold();
        switch (towerType) {
            case "testTower":
                if(currentPlayerGold >= 3) {
                    setPlayerGold(currentPlayerGold - 3);
                    return true;
                }
                break;
            case "upgradeLvl1":
                if(currentPlayerGold >= 2) {
                    setPlayerGold(currentPlayerGold - 2);
                    return true;
                }
                break;
            case "upgradeLvl2":
                if(currentPlayerGold >= 4) {
                    setPlayerGold(currentPlayerGold - 4);
                    return true;
                }
                break;
            default:
                return false;
        }
        return false;
    }

    public void checkIfWaveIsFinished() {
        boolean foundNoEnemy = true;
        for(GameObject obj : gameObjects) {
            if(obj instanceof Enemy) {
                foundNoEnemy = false;
                break;
            }
        }
        currentLevel.setIsWaveFinished(foundNoEnemy);
    }

    public void remove (GameObject object) {
        gameObjects.remove(object);
    }

    public void start() {
        
    }

    public void spawnWave() {
        currentLevel.spawnWave();
    }

    public void failedWave() {
        currentLevel.failedWave();
    }

    public SimpleBooleanProperty lastWaveProperty() {
        return currentLevel.lastWaveProperty();
    }

    public SimpleIntegerProperty playerHPProperty() {
        return currentLevel.playerHPProperty();
    }

    public SimpleIntegerProperty indexProperty() {
        return currentLevel.indexProperty();
    }

    public double[] tryToPlaceTower(double x, double y, Tower tower) {
        return currentLevel.placeTower(x, y, tower);
    }

    public SimpleObjectProperty<Enemy> currentEnemyProperty() {
        return currentLevel.currentEnemyProperty();
    }

    public LinkedList<Enemy> getListOfEnemies() {
        return currentLevel.getListOfEnemies();
    }

    public double getStep() {
        return currentLevel.getStep();
    }

    public Tower getTowerAtPos(double x, double y) {
        return currentLevel.getTowerAtPos(x, y);
    }

    public int getTotalTowers() {
        int towerCount = 0;
        for(GameObject obj : gameObjects) {
            if(obj instanceof Tower) {
                towerCount++;
            }
        }
        return towerCount;
    }

    public int getPlayerGold() {
        return currentLevel.getPlayerGold();
    }

    public SimpleIntegerProperty playerGoldProperty() {
        return currentLevel.playerGoldProperty();
    }

    public void setPlayerGold(int playerGold) {
        currentLevel.setPlayerGold(playerGold);
    }

    public void setKillCounter() {
        currentLevel.setKillCounter(currentLevel.getKillCounter() + 1);
    }

    public int getKillCounter() {
        return currentLevel.getKillCounter();
    }

    public SimpleBooleanProperty isWaveFinishedProperty() {
        return currentLevel.isWaveFinishedProperty();
    }

    public void setTowerChooser(String towerChooser) {
        this.towerChooser = towerChooser;
    }

    public String getTowerChooser() {
        return towerChooser;
    }
}
