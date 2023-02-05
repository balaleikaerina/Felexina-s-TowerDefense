package application.launcher.levels;

import application.gamelogic.tower.Tower;

import java.util.LinkedList;

public class TowerPlacementGrid {
    private LinkedList<TowerPlacementPosition> listOfTowerPlacementPositions;
    private double step;

    public TowerPlacementGrid(double[][] arrayOfPlacementPositions, double step) {
        listOfTowerPlacementPositions = new LinkedList<>();
        this.step = step;
        for(double[] position : arrayOfPlacementPositions) {
            addPlacementPosition(position);
        }
    }

    private void addPlacementPosition(double[] position) {
        listOfTowerPlacementPositions.add(new TowerPlacementPosition(position[0], position[1], step));
    }

    public double[] placeTower(double x, double y, Tower tower) {
        for(TowerPlacementPosition towerPlacementPosition : listOfTowerPlacementPositions) {
            if(towerPlacementPosition.isPlaceInBounds(x, y)) {
                if(!towerPlacementPosition.getIsPlaceTaken()) {
                    towerPlacementPosition.addTower(tower);
                    return towerPlacementPosition.getPosition();
                }
            }
        }
        return null;
    }

    public double getStep() {
        return step;
    }

    public Tower getTowerAtPos(double x, double y) {
        for(TowerPlacementPosition towerPlacementPosition : listOfTowerPlacementPositions) {
            if(towerPlacementPosition.isPlaceInBounds(x, y)) {
                if(towerPlacementPosition.getIsPlaceTaken()) {
                    return towerPlacementPosition.getPlacedTower();
                }
            }
        }
        return null;
    }
}
