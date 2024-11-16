package org.jewelhunt.ui;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
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
        setHeight(v);
    }

    public void setWidthCanvas(double v) {
        canvas.setWidth(v);
        setWidth(v);
    }

    public void drawImage(Image image, int size_image, int line, int column) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.drawImage(image, size_image * column, size_image * line);
    }

    public void strokeText(String s, int size_image, int line, int column) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.strokeText(s, size_image * column + size_image / 2, size_image * line + size_image / 2);
    }

    public void strokeText(String s, double v, double v1) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.strokeText(s, v, v1);
    }

    public void setStroke(Color color) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setStroke(color);
    }
}
