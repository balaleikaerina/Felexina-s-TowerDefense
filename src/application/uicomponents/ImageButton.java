package application.uicomponents;

import javafx.scene.control.Button;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageButton extends Button {

    private final String STYLE_NORMAL = "-fx-background-color: transparent; -fx-padding: 0, 0, 0, 0;";
    private boolean pressed = false;
    private final DropShadow DROP_SHADOW = new DropShadow(BlurType.GAUSSIAN, Color.web("#454545"), 5, 0.05, 0, 0);
    private ImageView firstView, secondView;


    public ImageButton(Image firstImage, Image secondImage, double height, double width) {

        this(firstImage, height, width);
        firstView = new ImageView(firstImage);
        ColorAdjust hoverColorChange = new ColorAdjust();

        firstView.setFitHeight(height);
        firstView.setFitHeight(width);
        firstView.setPreserveRatio(true);
        firstView.setSmooth(true);
        firstView.setEffect(hoverColorChange);

        secondView = new ImageView(secondImage);
        secondView.setFitHeight(height);
        secondView.setFitWidth(width);
        secondView.setPreserveRatio(true);
        secondView.setSmooth(true);
        secondView.setEffect(hoverColorChange);

        setGraphic(firstView);
        setStyle(STYLE_NORMAL);
        DROP_SHADOW.setHeight(20);
        DROP_SHADOW.setWidth(20);
        setEffect(DROP_SHADOW);

        setOnMouseEntered(event -> {
            hoverColorChange.setBrightness(-0.07);
        });

        setOnMouseExited(event -> {
            hoverColorChange.setBrightness(0);
        });

        setOnMousePressed(event -> {
            pressed = !pressed;
            if (pressed) {
                setGraphic(secondView);
            } else {
                setGraphic(firstView);
            }

        });
    }

    public void setPressedImg(boolean pressedState) {
        if(secondView != null) {
            if (pressedState) {
                pressed = true;
                setGraphic(secondView);
            } else {
                pressed = false;
                setGraphic(firstView);
            }
        }
    }

    public ImageButton(Image firstImage, double height, double width) {
        firstView = new ImageView(firstImage);
        ColorAdjust hoverColorChange = new ColorAdjust();

        firstView.setFitHeight(height);
        firstView.setFitHeight(width);
        firstView.setPreserveRatio(true);
        firstView.setSmooth(true);
        firstView.setEffect(hoverColorChange);

        setGraphic(firstView);
        setStyle(STYLE_NORMAL);
        DROP_SHADOW.setHeight(20);
        DROP_SHADOW.setWidth(20);
        setEffect(DROP_SHADOW);

        setOnMouseEntered(event -> {
            hoverColorChange.setBrightness(-0.07);
        });

        setOnMouseExited(event -> {
            hoverColorChange.setBrightness(0);
        });

    }

    public boolean getPressed() {
        return pressed;
    }
}

