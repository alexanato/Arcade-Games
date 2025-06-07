package com.example.arcadeposproject;

import com.example.arcadeposproject.games.*;
import com.example.arcadeposproject.models.utils;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private GameManager gameManager;
    @Override
    public void start(Stage stage){
        System.out.println(utils.load("data"));
        gameManager = new GameManager(stage);
        gameManager.addGame(new TicTacToe("tic tac toe",Assets.TICTACTOE_LOGO,"TicTacToe.fxml"));
        gameManager.addGame(new Snake("Snake",Assets.SNAKE_LOGO,"Snake.fxml"));
        gameManager.addGame(new Pong("a",Assets.PONG_LOGO,"Pong.fxml"));
        gameManager.addGame(new Dame("a",Assets.JUMPRUN_LOGO,"Dame.fxml"));
        gameManager.addGame(new Gomoku("a",Assets.JUMPRUN_LOGO,"Gomoku.fxml"));
        gameManager.addGame(new JumpAndRund("a",Assets.SNAKE_LOGO,"JumpAndRun.fxml"));
        stage.setResizable(false);
        gameManager.returnToMenu();
        gameManager.launch(5);
    }
    public static void main(String[] args) {
        launch();
    }
}
