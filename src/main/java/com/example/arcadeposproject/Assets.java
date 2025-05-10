package com.example.arcadeposproject;

import javafx.scene.image.Image;

import java.util.Objects;

public class Assets {
    public static final Image TICTACTOE_LOGO = load("Assets/TicTacToeLogo.png");
    public static final Image SNAKE_LOGO = load("Assets/Empty.png");
    public static final Image X = load("Assets/X.png");
    public static final Image O = load("Assets/O.png");
    private static Image load(String resourcePath) {
        return new Image(
                Objects.requireNonNull(Assets.class.getResourceAsStream(resourcePath))
        );
    }
}
