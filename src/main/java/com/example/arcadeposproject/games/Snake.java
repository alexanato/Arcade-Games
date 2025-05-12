package com.example.arcadeposproject.games;

import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.controllers.BasicController;
import com.example.arcadeposproject.controllers.SnakeController;
import com.example.arcadeposproject.controllers.TicTacToeController;
import com.example.arcadeposproject.models.SnakeModel;
import com.example.arcadeposproject.models.TicTacToeModel;
import javafx.scene.image.Image;

public class Snake extends Game {
    private SnakeController controller;
    public Snake(String name, Image logo, String viewPath) {
        super(name, logo, viewPath);
    }

    @Override
    public void start(GameManager gameManager, BasicController controller) {
        this.controller = (SnakeController) controller;
        this.controller.setModel(new SnakeModel());
        this.controller.start();
    }
}
