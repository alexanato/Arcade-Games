package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.models.TicTacToeModel;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class ResultController extends BasicController{
    @FXML
    private Label result;
    @Override
    public void init(GameManager gameManager,String message) {
        super.init(gameManager,message);
        result.setText(message);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @FXML
    private void replay(){
        gameManager.reloadGame();
    }
    @FXML
    private void menu(){
        gameManager.returnToMenu();
    }
}
