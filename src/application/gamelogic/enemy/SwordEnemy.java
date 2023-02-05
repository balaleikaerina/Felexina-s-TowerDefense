package application.gamelogic.enemy;

import application.gamelogic.GameObject;
import java.util.LinkedList;

public class SwordEnemy extends Enemy{
    public SwordEnemy(double posX, double posY, LinkedList<double[]> path) {
        super(150, 60, posX, posY, path);
    }
}