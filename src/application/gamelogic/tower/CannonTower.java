package application.gamelogic.tower;

import application.gamelogic.enemy.Enemy;

import java.util.LinkedList;

public class CannonTower extends Tower{

    public CannonTower(double x, double y, LinkedList<Enemy> enemies) {
        super("TestTower", 130, x, y, 45, 0.9, enemies);
    }

    @Override
    public void upgrade() {
        if (getLevel() < MAXLEVEL) {
            levelProperty().set(getLevel() + 1);
            damage += 50;
        }
    }
}
