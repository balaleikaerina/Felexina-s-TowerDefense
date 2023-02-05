package application.launcher.levels;

import application.gamelogic.tower.Tower;

public class TowerPlacementPosition {
    private double x;
    private double y;
    private double step;
    private boolean isPlaceTaken;
    private Tower placedTower;

    public TowerPlacementPosition(double x , double y, double step) {
        this.x = x;
        this.y = y;
        this.step = step;
        isPlaceTaken = false;
        placedTower = null;
    }

    public boolean isPlaceInBounds(double x, double y) {
        if(x > this.x && x < this.x + step) {
            if(y > this.y && y < this.y + step) {
                return true;
            }
        }
        return false;
    }

    public Tower getPlacedTower() {
        return placedTower;
    }

    public boolean getIsPlaceTaken() {
        return isPlaceTaken;
    }

    public void addTower(Tower tower) {
        placedTower = tower;
        isPlaceTaken = true;
    }

    public double[] getPosition() {
        return new double[]{x,y};
    }
}
