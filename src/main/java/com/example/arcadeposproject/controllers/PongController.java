package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.FPS;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.models.PongModel;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Set;

public class PongController extends BasicController {

    public static int canvasheight;
    public static int canvaswidth;
    public static int playerheight = 80;
    private PongModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    private AnimationTimer gameLoop;
    private ArrayList<KeyCode> input = new ArrayList<>();
    private FPS fps = new FPS();
    public static int ballSize = 10;

    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.update(fps.getDeltaTime());
                model.keyHandlePlayer1(input);
                model.keyHandlePlayer2(input);
                fps.Frame(now);
                draw();
            }
        };
        handeInput();
        canvasheight = (int) canvas.getHeight();
        canvaswidth = (int) canvas.getWidth();
    }

    @Override
    public void start() {
        gameLoop.start();
        double[][] players = {{20, 100}, {568, 100}};
        model.setPlayers(players);
        model.respawnBall();
    }

    @Override
    public void stop() {

    }

    private void draw() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.WHITE);
        context.fillRect(model.getPlayers()[0][0], model.getPlayers()[0][1], 12, 100);
        context.fillRect(model.getPlayers()[1][0], model.getPlayers()[1][1], 12, 100);
        for (int i = 0; i < canvasheight; i += 40) {
            context.fillRect(canvas.getWidth() / 2 - 20, i - 20, 20, 20);
        }
        context.setFont(Font.font("Comic Sans MS", 30));
        context.fillText(""+model.points[1], canvas.getWidth() / 2 - 60, 40);
        context.fillText(""+model.points[0], canvas.getWidth() / 2 + 10, 40);
        context.fillRect(model.ball[0], model.ball[1], ballSize, ballSize);

    }

    private void handeInput() {
        canvas.getScene().setOnKeyPressed((KeyEvent event) -> {
            boolean contain = false;
            for (KeyCode code : input) {
                if (event.getCode() == code) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                input.add(event.getCode());
            }
        });
        canvas.getScene().setOnKeyReleased((KeyEvent event) -> {
            input.remove(event.getCode());
        });
    }

    public void setModel(PongModel model) {
        this.model = model;
    }
}
