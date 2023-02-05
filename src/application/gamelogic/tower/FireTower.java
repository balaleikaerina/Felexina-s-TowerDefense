package application.gamelogic.tower;

import application.gamelogic.enemy.Enemy;

import java.util.LinkedList;

public class FireTower extends Tower{

    public FireTower(double x, double y, LinkedList<Enemy> enemies) {
        super("TestTower", 135, x, y, 70, 0.8, enemies);
    }

    @Override
    public void upgrade() {
        if (getLevel() < MAXLEVEL) {
            levelProperty().set(getLevel() + 1);
            range += 15;
            damage += 25;
        }
    }
}
