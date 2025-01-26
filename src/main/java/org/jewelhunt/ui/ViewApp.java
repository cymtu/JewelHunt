package org.jewelhunt.ui;

import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import org.jewelhunt.controllers.Controller;

public class ViewApp extends Region {

    private final Canvas canvas;

    public ViewApp(Controller controller) {
        canvas = new Canvas();
        getChildren().add(canvas);
        setOnMouseClicked(controller::setOnMouseClicked);
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

    public void strokeText(String s, double v, double v1) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.strokeText(s, v, v1);
    }

    public void setStroke(Color color) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setStroke(color);
    }

    public void setFont(Font font) {
        GraphicsContext graphicsContext2D = canvas.getGraphicsContext2D();
        graphicsContext2D.setFont(font);
        graphicsContext2D.setTextAlign(TextAlignment.CENTER);
        graphicsContext2D.setTextBaseline(VPos.CENTER);
    }
}
