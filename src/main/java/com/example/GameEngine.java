package com.example;

import javax.swing.*;

import com.example.levels.Level;
import com.example.sprites.BaseSprite;
import com.example.sprites.PlayerSprite;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class GameEngine extends JPanel implements Runnable, KeyListener {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private static final int TARGET_FPS = 30;
    private static final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

    private boolean running;
    private Thread gameThread;
    private List<BaseSprite> sprites; // Added: List to store sprites
    private Level level;
    private int fpsCount;
    private Font font;
    private BufferedImage buffer;

    private PlayerSprite playerSprite;
    private boolean KEY_UP;
    private boolean KEY_DOWN;
    private boolean KEY_LEFT;
    private boolean KEY_RIGHT;

    public GameEngine() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        addKeyListener(this);
        font = new Font("Arial", Font.PLAIN, 12);
        buffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
        sprites = new ArrayList<BaseSprite>();
        playerSprite = new PlayerSprite(100, 100);
        playerSprite.setCurrentAnimation("walk_down");
        this.sprites.add(playerSprite);
    } 

    // Implement the KeyListener interface
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            KEY_UP = true;
        } else if (key == KeyEvent.VK_S) {
            KEY_DOWN = true;
        } else if (key == KeyEvent.VK_A) {
            KEY_LEFT = true;
        } else if (key == KeyEvent.VK_D) {
            KEY_RIGHT = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_W) {
            KEY_UP = false;
        } else if (key == KeyEvent.VK_S) {
            KEY_DOWN = false;
        } else if (key == KeyEvent.VK_A) {
            KEY_LEFT = false;
        } else if (key == KeyEvent.VK_D) {
            KEY_RIGHT = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Handle key typed events if needed
    } 

    public void start() {
        if (gameThread == null) {
            running = true;
            gameThread = new Thread(this);
            gameThread.start();
        }        
    }

    public void addSprite(BaseSprite sprite) { // Added: Method to add a sprite to the list
        sprites.add(sprite);
    }

    public void removeSprite(BaseSprite sprite) { // Added: Method to remove a sprite from the list
        sprites.remove(sprite);
    }

    public void setLevel(Level level) {
        this.level = level;
    }


    public void stop() {
        running = false;
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void update() {
        // ... (existing update logic)
        for (BaseSprite sprite : sprites) {
            sprite.update();
        }
    }

    private void render() {
        // Render game objects to the off-screen buffer
        Graphics2D g = (Graphics2D) buffer.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH, HEIGHT);
    
        int screenWidth = getWidth();
        int screenHeight = getHeight();

        //level.setCameraPosition(playerX - screenWidth / 2, playerY - screenHeight / 2);
    
        // Render the level with the updated camera position
        //level.draw(g, screenWidth, screenHeight);
    
        // Render the player sprite at its position within the level
        BufferedImage currentFrame = playerSprite.getCurrentFrame();
        if (currentFrame != null) {
            // Calculate the player's position relative to the level tiles
            int playerRenderX = screenWidth / 2 - playerSprite.getWidth() / 2;
            int playerRenderY = screenHeight / 2 - playerSprite.getHeight() / 2;
            g.drawImage(currentFrame, playerRenderX, playerRenderY, null);
        }
    
        g.setColor(Color.WHITE);
        g.setFont(font);
        g.drawString("FPS: " + fpsCount, 10, 20);
        g.dispose();
    }
    

    private void renderToScreen() {
        // Render the off-screen buffer to the screen
        Graphics2D g = (Graphics2D) getGraphics();
        if (g != null) {
            g.drawImage(buffer, 0, 0, null);
            g.dispose();
        }
    }

    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;
        int frames = 0;

        while (running) {
            long now = System.nanoTime();
            lastLoopTime = now;

            movePlayer();
            
            // Update game logic here
            update();

            // Render game objects to off-screen buffer
            render();

            // Render off-screen buffer to screen
            renderToScreen();

            frames++;

            if (now - lastFpsTime >= 1000000000) {
                fpsCount = frames;
                frames = 0;
                lastFpsTime = now;
            }

            long sleepTime = (lastLoopTime - System.nanoTime() + OPTIMAL_TIME) / 1000000;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void movePlayer() {
        if (KEY_UP) {
            playerSprite.moveUp();
        }
        else if (KEY_DOWN) {
            playerSprite.moveDown();
        }
        else if (KEY_LEFT) {
            playerSprite.moveLeft();
        }
        else if (KEY_RIGHT) {
            playerSprite.moveRight();
        }
        else {
            playerSprite.moveStop();
        }
    }
}



