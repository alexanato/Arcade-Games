package com.example.arcadeposproject;

import javafx.scene.image.Image;

import java.util.Objects;

public class Assets {
    public static final Image TICTACTOE_LOGO = load("Assets/TicTacToeLogo.png");
    public static final Image SNAKE_LOGO = load("Assets/SnakeLogo.png");
    public static final Image PONG_LOGO = load("Assets/PongLogo.png");
    public static final Image SNAKE_EYE = load("Assets/snakeEye.png");
    public static final Image DAME_LOGO = load("Assets/Dame.png");
    public static final Image GOMOKU_LOGO = load("Assets/Gomuko.png");
    public static final Image DOTSNBOX_Logo = load("Assets/Dots.png");
    public static final Image X = load("Assets/X.png");
    public static final Image X_WHITE = load("Assets/XWhite.png");
    public static final Image O = load("Assets/O.png");
    public static final Image O_WHITE = load("Assets/OWhite.png");
    private static Image load(String resourcePath) {
        return new Image(
                Objects.requireNonNull(Assets.class.getResourceAsStream(resourcePath))
        );
    }
}
