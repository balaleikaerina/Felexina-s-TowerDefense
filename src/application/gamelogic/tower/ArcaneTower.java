package application.gamelogic.tower;

import application.gamelogic.enemy.Enemy;
import java.util.LinkedList;


public class ArcaneTower extends Tower{

    public ArcaneTower(double x, double y, LinkedList<Enemy> enemies) {
        super("TestTower", 150, x, y, 20, 0.5, enemies);
    }

    @Override
    public void upgrade() {
        if (getLevel() < MAXLEVEL) {
            levelProperty().set(getLevel() + 1);
            range += 35;
            damage += 10;
        }
    }
}
