package com.example.levels;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Tileset {
    private BufferedImage image;
    private int tileWidth;
    private int tileHeight;

    public Tileset(String imagePath, int tileWidth, int tileHeight) {
        try {
            this.image = ImageIO.read(new File(imagePath));
            this.tileWidth = tileWidth;
            this.tileHeight = tileHeight;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getTile(int tileX, int tileY) {
        return image.getSubimage(tileX * tileWidth, tileY * tileHeight, tileWidth, tileHeight);
    }

    public int getTileWidth() {
        return tileWidth;
    }

    public int getTileHeight() {
        return tileHeight;
    }
}

