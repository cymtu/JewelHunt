package org.jewelhunt.utils;

import javafx.scene.image.Image;
import org.jewelhunt.model.Jewels;

import java.io.IOException;
import java.util.Objects;

public class LoadImages {

    public static int SIZE_IMAGE = 64;

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

    public static Image getImage(Jewels jewel) {
        Image img = LoadImages.OPEN;

        switch (jewel) {
            case Nugget:
                img = LoadImages.NUGGET;
                break;
            case Amethyst:
                img = LoadImages.AMETHYST;
                break;
            case Chrysolite:
                img = LoadImages.CHRYSOLITE;
                break;
            case Pearl:
                img = LoadImages.PEARL;
                break;
            case Sapphire:
                img = LoadImages.SAPPHIRE;
                break;
            case Ruby:
                img = LoadImages.RUBY;
                break;
        }

        return img;
    }

}
