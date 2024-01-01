package com.example;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.example.levels.Level;
import com.example.sprites.BaseSprite;
import com.example.sprites.PlayerSprite;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int[][] tiles = {
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 2, 1, 4},
            {3, 5, 2, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 3, 2, 1, 4},
            {4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 2, 1, 4},
            {5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 3, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 2, 1, 4},
            {3, 5, 2, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 3, 2, 1, 4},
            {4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 2, 1, 4},
            {5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 2, 1, 4},
            {3, 5, 2, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 3, 2, 1, 4},
            {4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 2, 1, 4},
            {5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 3, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 2, 1, 4},
            {3, 5, 2, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 3, 2, 1, 4},
            {4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 2, 1, 4},
            {5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 2, 1, 4},
            {3, 5, 2, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 3, 2, 1, 4},
            {4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 2, 1, 4},
            {5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 3, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 2, 1, 4},
            {3, 5, 2, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 5, 4, 1, 3, 2, 3, 2, 1, 4},
            {4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 2, 1, 4},
            {5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 4, 1, 3, 5, 2, 3, 2, 1, 4},
            {1, 3, 2, 5, 4, 1, 2, 4, 5, 3, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 4, 5, 1, 2, 3, 2, 1, 4},
            {2, 4, 1, 3, 5, 2, 1, 4, 3, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 1, 3, 4, 5, 2, 3, 3, 2, 1, 4}
        };

        Level level = new Level("resources/TilesetGrass/overworld_tileset_grass.png", 16, 16, tiles, 40, 51);
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game Engine Template");
            GameEngine gameEngine = new GameEngine();
            gameEngine.setLevel(level);
            frame.add(gameEngine);
            frame.pack();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
            gameEngine.start();
        });
    }
}