package application.gamelogic.enemy;

import application.gamelogic.GameObject;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.LinkedList;

public abstract class Enemy implements GameObject{
    private int hp;
    private int maxHp;
    private double speed;
    private SimpleBooleanProperty alive;
    private SimpleDoubleProperty x;
    private SimpleDoubleProperty y;
    private LinkedList<double[]> path;
    private SimpleBooleanProperty finished;
    private final double ENEMY_SIZE = 60;

    public Enemy(int hp, double speed, double x, double y, LinkedList<double[]> path) {
        this.hp = hp;
        maxHp = hp;
        this.speed = speed;
        this.x = new SimpleDoubleProperty(x);
        this.y = new SimpleDoubleProperty(y);
        alive = new SimpleBooleanProperty(true);
        this.path = (LinkedList<double[]>) path.clone();
        finished = new SimpleBooleanProperty();
        finished.set(false);
    }

    private void followPath(double delta) {
        //Equal values if very close
        if (!path.isEmpty()) {
            if (x.get() == path.getFirst()[0] && y.get() == path.getFirst()[1]) {
                path.removeFirst();
            }

            if ((x.get() / path.getFirst()[0]) < 1.01 && (x.get() / path.getFirst()[0]) > 0.99) {
                x.set(path.getFirst()[0]);
            } else {
                if (x.get() < path.getFirst()[0]) {
                    x.set(x.get() + speed * delta);
                } else if (x.get() > path.getFirst()[0]) {
                    setX(x.get() - speed * delta);
                }
            }

            if ((y.get() / path.getFirst()[1]) < 1.01 && (y.get() / path.getFirst()[1]) > 0.99) {
                y.set(path.getFirst()[1]);
            } else {
                if (y.get() < path.getFirst()[1]) {
                    y.set(y.get() + speed * delta);
                } else if (y.get() > path.getFirst()[1]) {
                    setY(y.get() - speed * delta);
                }
            }
        }
    }

    public void update(double delta) {
        /*
        x += speedX * delta;
        y += speedY * delta;
        */
        followPath(delta);
        if ((x.get() == path.getLast()[0]) && (y.get() == path.getLast()[1])) {
            finished.set(true);
        }
    }

    public void damage(int damage) {
        hp -= damage;
        if (hp <= 0) {
            aliveProperty().setValue(false);
        }
    }

    public void setX(double x) {
        this.x.setValue(x);
    }

    public double getX() {
        return x.get();
    }

    public double getY() {
        return y.get();
    }

    public void setY(double y) {
        this.y.setValue(y);
    }

    public SimpleBooleanProperty aliveProperty() {
        return alive;
    }

    public SimpleBooleanProperty finishedProperty() {
        return finished;
    }

    public void setAlive(boolean alive) {
        this.alive.set(alive);
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public boolean isFinished() {
        return finished.get();
    }

    public boolean isAlive() {
        return alive.get();
    }

    public double getENEMY_SIZE() {
        return ENEMY_SIZE;
    }
}