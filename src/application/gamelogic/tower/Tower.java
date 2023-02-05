package application.gamelogic.tower;

import application.gamelogic.Game;
import application.gamelogic.GameObject;
import application.gamelogic.enemy.Enemy;
import javafx.animation.PathTransition;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.LinkedList;

public abstract class Tower implements GameObject {
    private String name;
    protected double range;
    private double x;
    private double y;
    protected int damage;
    protected double speed;
    private SimpleIntegerProperty level;
    protected final int MAXLEVEL;
    private LinkedList<Enemy> enemies;
    private double time;
    private SimpleBooleanProperty animationTrigger;
    private Enemy currentEnemyToAttack;

    public Tower(String name, double range, double x, double y, int damage, double speed, LinkedList<Enemy> enemies) {
        this.name = name;
        this.y = y;
        this.range = range;
        this.x = x;
        this.damage = damage;
        this.speed = speed;
        this.level = new SimpleIntegerProperty(1);
        this.MAXLEVEL = 3;
        this.enemies = enemies;
        time = 0;
        animationTrigger = new SimpleBooleanProperty(false);
        //System.out.println("Tower palced on X: " + x + "|| Y: " + y);
    }

    public void upgrade() {
        if (getLevel() < MAXLEVEL) {
            levelProperty().set(getLevel() + 1);
        }
    }

    public void update(double delta) {
        time += delta;
        if(time >= speed) {
            for (Enemy enemy : enemies) {
                if (enemy.aliveProperty().get() && (Math.pow(range, 2) >= (Math.pow((enemy.getX() + (enemy.getENEMY_SIZE() / 2) - this.x), 2) + Math.pow((enemy.getY() + (enemy.getENEMY_SIZE() / 2) - this.y), 2)))) {
                    currentEnemyToAttack = enemy;
                    animationTrigger.set(!animationTrigger.getValue());
                    enemy.damage(damage);
                    time = 0;
                    return;
                }
            }
        }
    }


    //getters and setters
    public int getLevel() {
        return level.getValue();
    }

    public SimpleIntegerProperty levelProperty() {
        return level;
    }

    public SimpleBooleanProperty animationTriggerProperty() {
        return animationTrigger;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRange() {
        return range;
    }

    public double getSpeed() {
        return speed;
    }

    public Enemy getCurrentEnemyToAttack() {
        return currentEnemyToAttack;
    }

    public int getMAXLEVEL() {
        return MAXLEVEL;
    }
}