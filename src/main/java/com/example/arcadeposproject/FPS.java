package com.example.arcadeposproject;

public class FPS {
    private long lastTime = 0;
    private int frames = 0;
    private long lastFrameTime = 0;
    private double fps = 0;
    private double deltaTime;
    public void Frame(long now){
        if (lastTime > 0) {
            long delta = now - lastTime;

            deltaTime = delta / 1_000_000_000.0;
            fps = 1 / deltaTime;

            frames++;

            if (now - lastFrameTime >= 1_000_000_000) {
                lastFrameTime = now;
                frames = 0;
            }
        }
        lastTime = now;
    }
    public double getFps() {
        return fps;
    }
    public  double getDeltaTime() {
        return deltaTime;
    }
}
