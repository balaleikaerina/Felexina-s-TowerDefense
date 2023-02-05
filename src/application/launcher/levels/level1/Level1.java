package application.launcher.levels.level1;

import application.gamelogic.GameObject;
import application.gamelogic.Wave;
import application.launcher.levels.Level;

import java.util.List;

public class Level1 extends Level {
    public Level1(List<GameObject> gameObjects, double[][] towerPlacementPlaces) {
        super(1440, 890, -58.0, 222, gameObjects, towerPlacementPlaces, 150);
        enemyPath.add(new double[]{1440, 880});
        enemyPath.add(new double[]{1440, 297});
        enemyPath.add(new double[]{1440, 236});
        enemyPath.add(new double[]{1335, 224});
        enemyPath.add(new double[]{870, 223});
        enemyPath.add(new double[]{826, 258});
        enemyPath.add(new double[]{794, 697});
        enemyPath.add(new double[]{760, 743});
        enemyPath.add(new double[]{240, 748});
        enemyPath.add(new double[]{196, 728});
        enemyPath.add(new double[]{160, 687});
        enemyPath.add(new double[]{155, 544});
        enemyPath.add(new double[]{186, 480});
        enemyPath.add(new double[]{422, 475});
        enemyPath.add(new double[]{472, 444});
        enemyPath.add(new double[]{504, 405});
        enemyPath.add(new double[]{497, 261});
        enemyPath.add(new double[]{464, 239});
        enemyPath.add(new double[]{426, 220});
        enemyPath.add(new double[]{75, 222});
        enemyPath.add(new double[]{-58, 222});

        Wave wave1 = new Wave(5, 0, 0, pathStartX, pathStartY, enemyPath, 1000);
        waves.add(wave1);
        Wave wave2 = new Wave(0, 5, 0, pathStartX, pathStartY, enemyPath, 500);
        waves.add(wave2);
        Wave wave3 = new Wave(4, 3, 3, pathStartX, pathStartY, enemyPath, 500);
        waves.add(wave3);
    }
}
