package application.gamelogic.tower;

import application.gamelogic.enemy.Enemy;

import java.util.LinkedList;

public class ArcherTower extends Tower{

    public ArcherTower(double x, double y, LinkedList<Enemy> enemies) {
        super("TestTower", 200, x, y, 25, 0.7, enemies);
    }

    @Override
    public void upgrade() {
        if (getLevel() < MAXLEVEL) {
            levelProperty().set(getLevel() + 1);
            range += 65;
        }
    }
}
