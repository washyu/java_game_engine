package com.example.sprites;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public class BaseSprite {
    private Map<String, BufferedImage[]> animations;
    protected String currentAnimation;
    protected int currentFrame;
    private int x;
    private int y;

    public BaseSprite(int x, int y) {
        animations = new HashMap<>();
        currentAnimation = null;
        currentFrame = 0;
        this.x = x;
        this.y = y;
    }

    public void addAnimation(String name, BufferedImage[] frames) {
        animations.put(name, frames);
        if (currentAnimation == null) {
            currentAnimation = name;
        }
    }

    public void setCurrentAnimation(String name) {
        if (animations.containsKey(name)) {
            currentAnimation = name;
            currentFrame = 0; // Reset current frame when switching animations
        }
    }

    public BufferedImage getCurrentFrame() {
        if (currentAnimation != null && animations.containsKey(currentAnimation)) {
            BufferedImage[] frames = animations.get(currentAnimation);
            if (currentFrame >= 0 && currentFrame < frames.length) {
                return frames[currentFrame];
            }
        }
        return null;
    }

    public void update() {
        if (currentAnimation != null && animations.containsKey(currentAnimation)) {
            BufferedImage[] frames = animations.get(currentAnimation);
            currentFrame = (currentFrame + 1) % frames.length;
        }
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}

