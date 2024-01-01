package com.example.levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Level {
    private Tileset tileset;
    private int[][] tiles;

    private int tileWidth;
    private int tileHeight;

    private int visibleRows;
    private int visibleColumns;

    private int offsetX;
    private int offsetY;

    public Level(String tilesetPath, int tileWidth, int tileHeight, int[][] tiles, int visibleRows, int visibleColumns) {
        this.tileset = new Tileset(tilesetPath, tileWidth, tileHeight);
        this.tiles = tiles;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        this.visibleRows = visibleRows;
        this.visibleColumns = visibleColumns;
        this.offsetX = 0;
        this.offsetY = 0;
    }

    public void setCameraPosition(int x, int y) {
        // Adjust the offset based on the camera position
        this.offsetX = x;
        this.offsetY = y;
    
        // Wrap around the tileset if the camera reaches the boundaries
        int mapWidth = tiles[0].length * tileWidth;
        int mapHeight = tiles.length * tileHeight;
    
        if (this.offsetX < 0) {
            this.offsetX += mapWidth;
        }
        this.offsetX %= mapWidth;
    
        if (this.offsetY < 0) {
            this.offsetY += mapHeight;
        }
        this.offsetY %= mapHeight;
    }

    
    // Getter method for the camera X position
    public int getCameraX() {
        return offsetX;
    }

    // Getter method for the camera Y position
    public int getCameraY() {
        return offsetY;
    }
    

    public void draw(Graphics g, int screenWidth, int screenHeight) {
        int startCol = offsetX / tileWidth;
        int startRow = offsetY / tileHeight;

        for (int y = 0; y < visibleRows; y++) {
            for (int x = 0; x < visibleColumns; x++) {
                int col = (startCol + x) % tiles[0].length;
                int row = (startRow + y) % tiles.length;

                int tileX = x * tileWidth - (offsetX % tileWidth);
                int tileY = y * tileHeight - (offsetY % tileHeight);

                BufferedImage tile = tileset.getTile(tiles[row][col], 0);
                g.drawImage(tile, tileX, tileY, null);
            }
        }
    }
}


