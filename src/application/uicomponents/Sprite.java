package application.uicomponents;

import application.gamelogic.GameObject;
import javafx.beans.property.SimpleObjectProperty;

public interface Sprite {
    void render();
    void render(double delta);
    SimpleObjectProperty<GameObject> gameObjectProperty();
}
