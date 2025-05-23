package com.example.arcadeposproject;

import com.example.arcadeposproject.games.Snake;
import com.example.arcadeposproject.games.TicTacToe;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private GameManager gameManager;
    @Override
    public void start(Stage stage){
        gameManager = new GameManager(stage);
        gameManager.addGame(new TicTacToe("tic tac toe",Assets.TICTACTOE_LOGO,"TicTacToe.fxml"));
        gameManager.addGame(new Snake("Snake",Assets.SNAKE_LOGO,"Snake.fxml"));
        gameManager.addGame(new TicTacToe("a",Assets.PONG_LOGO,"TicTacToe.fxml"));
        gameManager.addGame(new TicTacToe("a",Assets.JUMPRUN_LOGO,"TicTacToe.fxml"));
        stage.setResizable(false);
        gameManager.returnToMenu();
    }
    public static void main(String[] args) {
        launch();
    }
}
