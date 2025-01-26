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
    public static Image EMERALD;
    public static Image SAPPHIRE;
    public static Image RUBY;
    public static Image STONE;

    public static void load() {
        CLOSED = loadImage("closed");
        OPEN = loadImage("open");
        NUGGET = loadImage("nugget");
        AMETHYST = loadImage("amethyst");
        CHRYSOLITE = loadImage("chrysolite");
        EMERALD = loadImage("emerald");
        SAPPHIRE = loadImage("sapphire");
        RUBY = loadImage("ruby");
        STONE = loadImage("stone");
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

    public static Image getImage(String imgName) {
        Image img;

        switch (imgName) {
            case "Nugget":
                img = NUGGET;
                break;
            case "Amethyst":
                img = AMETHYST;
                break;
            case "Chrysolite":
                img = CHRYSOLITE;
                break;
            case "Emerald":
                img = EMERALD;
                break;
            case "Sapphire":
                img = SAPPHIRE;
                break;
            case "Ruby":
                img = RUBY;
                break;
            case "Closed":
                img = CLOSED;
                break;
            case "Stone":
                img = STONE;
                break;
            default:
                img = OPEN;
        }

        return img;
    }

}
