package com.example.arcadeposproject.games;

import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.controllers.BasicController;
import com.example.arcadeposproject.controllers.TicTacToeController;
import com.example.arcadeposproject.models.TicTacToeModel;
import javafx.scene.image.Image;

public class TicTacToe extends Game {
    private TicTacToeController controller;
    public TicTacToe(String name, Image logo,String viewPath) {
        super(name, logo,viewPath);
    }
    public void start(GameManager gameManager,BasicController controller) {
        TicTacToeModel model = new TicTacToeModel();
        this.controller = (TicTacToeController) controller;
        this.controller.model = model;
    }
}
