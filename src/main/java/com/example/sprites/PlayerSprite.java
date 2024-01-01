package com.example.sprites;

import javax.imageio.ImageIO;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PlayerSprite extends BaseSprite {
    private static final int FRAME_WIDTH = 64; // Width of each frame in the sprite sheet
    private static final int FRAME_HEIGHT = 64; // Height of each frame in the sprite sheet
    private static final int ANIMATION_FRAMES = 9;
    private static final int MOVE_SPEED = 5;
    private static boolean walking = false;

    private Map<String, BufferedImage[]> mainBodyAnimations;
    private Map<String, BufferedImage[]> legArmorAnimations;
    //private Map<String, BufferedImage[]> weaponAnimations;

     
    private Map<String, BufferedImage[]> loadPlayerAnimation(String fileName, String animationName) {
        Map<String, BufferedImage[]> animation = new HashMap<>();
        // Load player sprite sheet
        try {
            BufferedImage spriteSheet = ImageIO.read(new File(fileName));
            // Define the rows and columns for each animation in the sprite sheet
            BufferedImage[] LeftFrames = new BufferedImage[ANIMATION_FRAMES]; // Assuming 2 frames for the walk left animation
            BufferedImage[] RightFrames = new BufferedImage[ANIMATION_FRAMES];
            BufferedImage[] UpFrames = new BufferedImage[ANIMATION_FRAMES]; // Assuming 2 frames for the walk left animation
            BufferedImage[] DownFrames = new BufferedImage[ANIMATION_FRAMES];
             // Assuming 2 frames for the walk right animation
            // Extract frames from the sprite sheet for each animation
            for (int i = 0; i < ANIMATION_FRAMES; i++) {
                UpFrames[i] = spriteSheet.getSubimage(i * FRAME_WIDTH, 0 * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
                LeftFrames[i] = spriteSheet.getSubimage(i * FRAME_WIDTH, 1 * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
                DownFrames[i] = spriteSheet.getSubimage(i * FRAME_WIDTH, 2 * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
                RightFrames[i] = spriteSheet.getSubimage(i * FRAME_WIDTH, 3 * FRAME_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT);
            }
            // Add animations to the player sprite
            animation.put(String.format("%s_left", animationName), LeftFrames);
            animation.put(String.format("%s_right", animationName), RightFrames);
            animation.put(String.format("%s_up", animationName), UpFrames);
            animation.put(String.format("%s_down", animationName), DownFrames);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return animation;
    }

    public void moveStop() {
        currentFrame = 0;
        walking = false;
    }

    public void moveUp() {
        walking = true;
        if(!currentAnimation.equals("walk_up")) {
            setCurrentAnimation("walk_up");
        }
        setY(getY() - MOVE_SPEED);
    }

    public void moveDown() {
        walking = true;
        if(!currentAnimation.equals("walk_down")) {
            setCurrentAnimation("walk_down");
        }
        setY(getY() + MOVE_SPEED);
    }

    public void moveLeft() {
        walking = true;
        if(!currentAnimation.equals("walk_left")) {
            setCurrentAnimation("walk_left");
        }
        setX(getX() - MOVE_SPEED);
    }

    public void moveRight() {
        walking = true;
        if(!currentAnimation.equals("walk_right")) {
            setCurrentAnimation("walk_right");
        }
        setX(getX() + MOVE_SPEED);
    }
    
    public PlayerSprite(int x, int y) {
        super(x, y);
        mainBodyAnimations = loadPlayerAnimation("resources/walkcycle/BODY_male.png", "walk");
        legArmorAnimations = loadPlayerAnimation("resources/walkcycle/LEGS_pants_greenish.png", "walk");
    }

    @Override
    public BufferedImage getCurrentFrame() {
        BufferedImage compositeFrame = new BufferedImage(FRAME_WIDTH, FRAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = compositeFrame.createGraphics();

        g.drawImage(mainBodyAnimations.get(currentAnimation)[currentFrame], 0, 0 , null);
        g.drawImage(legArmorAnimations.get(currentAnimation)[currentFrame], 0, 0, null);
        g.dispose();
        return compositeFrame;
    }

    @Override
    public void setCurrentAnimation(String name) {
        currentAnimation = name;
        currentFrame = 0; // Reset current frame when switching animations
    }

    @Override
    public void update() {
        if(walking){
            currentFrame = (currentFrame + 1) % ANIMATION_FRAMES;
        }
    }

    public int getWidth() {
        return FRAME_WIDTH;
    }

    public int getHeight() {
        return FRAME_HEIGHT;
    }
}

