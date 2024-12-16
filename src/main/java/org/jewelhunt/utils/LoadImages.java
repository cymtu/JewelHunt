package org.jewelhunt.utils;

import javafx.scene.image.Image;

import java.io.IOException;
import java.util.Objects;

public class LoadImages {
    public static Image CLOSED;
    public static Image OPEN;
    public static Image NUGGET;
    public static Image AMETHYST;
    public static Image CHRYSOLITE;
    public static Image PEARL;
    public static Image SAPPHIRE;
    public static Image RUBY;
    public static Image COAL;

    public static void load() {
        CLOSED = loadImage("closed");
        OPEN = loadImage("open");
        NUGGET = loadImage("nugget");
        AMETHYST = loadImage("amethyst");
        CHRYSOLITE = loadImage("chrysolite");
        PEARL = loadImage("pearl");
        SAPPHIRE = loadImage("sapphire");
        RUBY = loadImage("ruby");
        COAL = loadImage("coal");
    }

    public static Image loadImage(String name) {
        String filename = "/images/" + name + ".png";
        Image image;
        try {
            image = new Image(Objects.requireNonNull(LoadImages.class.getResource(filename)).openStream());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return image;
    }

}
