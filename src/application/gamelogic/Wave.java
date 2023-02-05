package application.gamelogic;

import application.gamelogic.enemy.AxeEnemy;
import application.gamelogic.enemy.Enemy;
import application.gamelogic.enemy.ForkEnemy;
import application.gamelogic.enemy.SwordEnemy;

import java.util.ArrayList;
import java.util.LinkedList;

public class Wave {
    ArrayList<Enemy> enemies;
    private int time;

    public Wave(int axeEnemy, int swordEnemy, int forkEnemy, double posX, double posY, LinkedList<double[]> enemyPath, int time) {
        enemies = new ArrayList<>();
        for (int i = 0; i < axeEnemy; i++) {
            enemies.add(new AxeEnemy(posX, posY, enemyPath));
        }
        for (int i = 0; i < swordEnemy; i++) {
            enemies.add(new SwordEnemy(posX, posY, enemyPath));
        }
        for (int i = 0; i < forkEnemy; i++) {
            enemies.add(new ForkEnemy(posX, posY, enemyPath));
        }
        this.time = time;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public int getTime() {
        return time;
    }
}