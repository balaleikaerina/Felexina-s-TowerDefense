package application.launcher.levels;

import application.gamelogic.GameObject;
import application.gamelogic.Wave;
import application.gamelogic.enemy.AxeEnemy;
import application.gamelogic.enemy.Enemy;
import application.gamelogic.tower.Tower;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class Level {
    protected ArrayList<Wave> waves = new ArrayList<>();
    protected double pathStartX;
    protected double pathStartY;
    protected double pathEndX;
    protected double pathEndY;
    protected LinkedList<double[]> enemyPath;
    protected LinkedList<double[]> buildSpots;
    private SimpleIntegerProperty index;
    private SimpleIntegerProperty playerHP;
    private SimpleObjectProperty<Enemy> currentEnemy;
    private List<GameObject> gameObjects;
    private SimpleBooleanProperty lastWave;
    private SimpleIntegerProperty playerGold;
    private int killCounter;
    private SimpleBooleanProperty isWaveFinished;

    //vllt in game und nicht im level
    private LinkedList<Enemy> listOfEnemies;

    //TODO: list of tower positions vllt an anderer stelle sinnvoller
    protected TowerPlacementGrid towerPlacementGrid;



    public Level(double pathStartX, double pathStartY, double pathEndX, double pathEndY, List<GameObject> gameObjects, double[][] towerPlacementPlaces, double step) {
        enemyPath = new LinkedList<>();
        buildSpots = new LinkedList<>();
        this.pathStartX = pathStartX;
        this.pathStartY = pathStartY;
        this.pathEndX = pathEndX;
        this.pathEndY = pathEndY;
        index = new SimpleIntegerProperty(0);
        playerHP = new SimpleIntegerProperty(10);
        playerGold = new SimpleIntegerProperty(6);
        currentEnemy = new SimpleObjectProperty(new AxeEnemy (0,0,enemyPath));
        this.gameObjects = gameObjects;
        listOfEnemies = new LinkedList<>();
        towerPlacementGrid = new TowerPlacementGrid(towerPlacementPlaces, step);
        lastWave = new SimpleBooleanProperty(false);
        killCounter = 0;
        isWaveFinished = new SimpleBooleanProperty(false);
    }

    public void failedWave() {
        playerHP.set(playerHP.intValue() - 1);
    }

    public void spawnWave() {
        new Thread(() -> {
            try {
                for (Enemy enemy : waves.get(index.intValue()).getEnemies()) {
                    currentEnemy.set(enemy);
                    gameObjects.add(enemy);
                    listOfEnemies.add(enemy);
                    Thread.sleep(waves.get(index.intValue()).getTime());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (index.get() == (waves.size() - 1))
                lastWave.set(true);
            index.set(index.intValue() + 1);
        }).start();
    }

    public double[] placeTower(double x, double y, Tower tower) {
        return towerPlacementGrid.placeTower(x, y, tower);
    }

    public Tower getTowerAtPos(double x, double y) {
        return towerPlacementGrid.getTowerAtPos(x, y);
    }

    public SimpleObjectProperty<Enemy> currentEnemyProperty() {
        return currentEnemy;
    }

    public SimpleIntegerProperty playerHPProperty() {
        return playerHP;
    } 

    public LinkedList<Enemy> getListOfEnemies() {
        return listOfEnemies;
    }

    public SimpleIntegerProperty indexProperty() {
        return index;
    }

    public SimpleBooleanProperty lastWaveProperty() {
        return lastWave;
    }

    public SimpleIntegerProperty playerGoldProperty() {
        return playerGold;
    }

    public int getPlayerGold() {
        return playerGold.get();
    }

    public double getStep() {
        return towerPlacementGrid.getStep();
    }

    public void setPlayerGold(int playerGold) {
        this.playerGold.set(playerGold);
    }

    public void setKillCounter(int killCounter) {
        this.killCounter = killCounter;
    }

    public int getKillCounter() {
        return killCounter;
    }

    public boolean getIsIsWaveFinished() {
        return isWaveFinished.get();
    }

    public void setIsWaveFinished(boolean isWaveFinished) {
        this.isWaveFinished.set(isWaveFinished);
    }

    public SimpleBooleanProperty isWaveFinishedProperty() {
        return isWaveFinished;
    }
}