package application.gamelogic.enemy;

import application.gamelogic.GameObject;
import java.util.LinkedList;

public class ForkEnemy extends Enemy{
    public ForkEnemy(double posX, double posY, LinkedList<double[]> path) {
        super(80, 100, posX, posY, path);
    }
}