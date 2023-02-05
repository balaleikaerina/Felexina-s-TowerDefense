package application.uicomponents;

import application.gamelogic.GameObject;
import application.gamelogic.enemy.Enemy;
import application.gamelogic.tower.Tower;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Line;

public class BulletAnimation extends ImageView implements Sprite{
    private SimpleBooleanProperty reachedDestination;
    private double[] trajectory;
    private Image bulletImage;
    private double bulletSpeed;
    private SimpleObjectProperty<Enemy> enemyToAttack;
    private double x, y;

    public BulletAnimation(Tower tower, Enemy enemy) {
        bulletImage = new Image("./resources/bulletSprite.png");
        this.setFitWidth(16);
        this.setFitHeight(16);
        this.setPreserveRatio(true);
        this.setPickOnBounds(true);
        this.setImage(bulletImage);
        reachedDestination = new SimpleBooleanProperty(false);
        enemyToAttack = new SimpleObjectProperty<>(enemy);
        trajectory = new double[]{enemy.getX(), enemy.getY()};
        bulletSpeed = (1 - tower.getSpeed()) * 1000;
        x = tower.getX();
        y = tower.getY();
        this.setX(x);
        this.setY(y);

        //Winkel in dem das bild gedreht werden muss
        double a, b, c, alpha, beta;
        a = Math.abs(y - trajectory[1]);
        b = Math.abs(x - trajectory[0]);
        c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        beta = Math.toDegrees(Math.acos(a/c));
        //alpha = 180 - beta;
        this.setRotate(beta);
    }

    @Override
    public void render() {

    }

    //TODO: ändern der bulletanimation(speed und akt. von trajectory)
    @Override
    public void render(double delta) {
        //move in x
        if (x < trajectory[0]) {
            x += bulletSpeed * delta;
        } else if (x > trajectory[0]) {
            x -= bulletSpeed * delta;
        }

        //move in y
        if (y < trajectory[1]) {
            y += bulletSpeed * delta;
        } else if (y > trajectory[1]) {
            y -= bulletSpeed * delta;
        }

        //set new positions
        this.setX(x);
        this.setY(y);

        //safty check || if enemy dead reached is true
        if(enemyToAttack.getValue() == null || enemyToAttack.getValue().aliveProperty().get()) {
            reachedDestination.set(true);
        }


        //check if bullet reached target
        if ((x / trajectory[0] < 1.075) && ((x / trajectory[0]) > 0.925)) {
            if ((y / trajectory[1] < 1.075) && (y / trajectory[1] > 0.925)) {
                reachedDestination.set(true);
            }
        }
    }

    @Override
    public SimpleObjectProperty<GameObject> gameObjectProperty() {
        return null;
    }

    public SimpleBooleanProperty reachedDestinationProperty() {
        return reachedDestination;
    }
}
