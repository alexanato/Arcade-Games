package com.example.arcadeposproject;

import com.example.arcadeposproject.games.*;
import javafx.application.Application;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private GameManager gameManager;
    @Override
    public void start(Stage stage){
        gameManager = new GameManager(stage);
        gameManager.addGame(new TicTacToe("tic tac toe",Assets.TICTACTOE_LOGO,"TicTacToe.fxml"));
        gameManager.addGame(new Snake("Snake",Assets.SNAKE_LOGO,"Snake.fxml"));
        gameManager.addGame(new Pong("Pong",Assets.PONG_LOGO,"Pong.fxml"));
        gameManager.addGame(new Dame("Dame",Assets.DAME_LOGO,"Dame.fxml"));
        gameManager.addGame(new Gomoku("Gomoku",Assets.GOMOKU_LOGO,"Gomoku.fxml"));
        gameManager.addGame(new DotsnBoxes("Dots and Boxes",Assets.DOTSNBOX_Logo,"DotsnBoxes.fxml"));
        stage.setResizable(false);
        gameManager.returnToMenu();
    }

    public static void main(String[] args) {
        launch();
    }
}
