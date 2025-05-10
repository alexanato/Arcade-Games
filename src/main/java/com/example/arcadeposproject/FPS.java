package com.example.arcadeposproject;

public class FPS {
    private static long lastTime = 0;
    private static int frames = 0;
    private static long lastFrameTime = 0;
    private static double fps = 0;
    private static double deltaTime;
    public static void Frame(long now){
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

    public static double getFps() {
        return fps;
    }
    public static double getDeltaTime() {
        return deltaTime;
    }
}
