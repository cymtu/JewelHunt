package org.jewelhunt.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import org.jewelhunt.controllers.Controller;
import org.jewelhunt.utils.LoadImages;

public class ViewApp extends Region {

    private final Canvas canvas;

    public ViewApp(Controller controller) {
        canvas = new Canvas();
        getChildren().add(canvas);
        setOnMouseClicked(mouseEvent -> controller.setOnMouseClicked(mouseEvent));
    }

    public void setHeightCanvas(double v) {
        canvas.setHeight(v);
    }

    public void setWidthCanvas(double v) {
        canvas.setWidth(v);
    }

    public void drawImage(Image image, int size_image, int line, int column) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.drawImage(image, size_image * column, size_image * line);
    }

    public void strokeText(String s, int size_image, int line, int column) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.strokeText(s, size_image * column + size_image / 2, LoadImages.SIZE_IMAGE * line + size_image / 2);
    }

}
