package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.Assets;
import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.models.SnakeModel;
import com.example.arcadeposproject.models.SnakePiece;
import com.example.arcadeposproject.models.TicTacToeModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.time.Duration;

public class SnakeController extends BasicController {
    private SnakeModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    private Timeline gameLoop;

    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        gameLoop = new Timeline(
                new KeyFrame(javafx.util.Duration.millis(200), e -> {
                    model.update();
                    if (model.checkCollision()) stop();
                    draw();
                })
        );
        gameLoop.setCycleCount(Timeline.INDEFINITE);
        handeInput();
    }

    private void handeInput() {
        canvas.getScene().setOnKeyPressed((KeyEvent event) -> model.keyPressed(event));
    }

    public void start() {
        gameLoop.play();
        model.start();
        draw();
    }

    public void stop() {
        gameLoop.stop();
        gameManager.sendResult("You Lose!!");
    }

    private void draw() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.RED);
        int[] applePos = model.getApple();
        context.fillRect(applePos[0] * 40, applePos[1] * 40, 40, 40);
        context.setFill(Color.WHITE);
        for (SnakePiece snakePiece : model.getSnake()) {
            int offSetX = 0;
            int offSetY = 0;
            int offSetW = 0;
            int offSetH = 0;
            switch (snakePiece.getDirection()) {
                case UP:
                    offSetY = 20;
                    offSetH = 20;
                    break;
                case DOWN:
                    offSetH = 20;
                    break;
                case LEFT:
                    offSetW = 20;
                    offSetX =20;
                    break;
                case RIGHT:
                    offSetW = 20;
                    break;
            }
            context.fillRect(snakePiece.x * 40 + 10- offSetX, snakePiece.y * 40 + 10- offSetY, 20+ offSetW, 20+offSetH);
        }
    }

    public SnakeModel getModel() {
        return model;
    }

    public void setModel(SnakeModel model) {
        if (model != null) this.model = model;
    }
}
