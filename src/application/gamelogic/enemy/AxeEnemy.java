package application.gamelogic.enemy;

import application.gamelogic.GameObject;
import java.util.LinkedList;

public class AxeEnemy extends Enemy{
    public AxeEnemy(double posX, double posY, LinkedList<double[]> path) {
        super(100, 70, posX, posY, path);
    }
}