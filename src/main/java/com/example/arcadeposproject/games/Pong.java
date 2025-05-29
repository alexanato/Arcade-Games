package com.example.arcadeposproject.games;

import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.controllers.BasicController;
import com.example.arcadeposproject.controllers.PongController;
import com.example.arcadeposproject.controllers.SnakeController;
import com.example.arcadeposproject.models.PongModel;
import com.example.arcadeposproject.models.SnakeModel;
import javafx.scene.image.Image;

public class Pong extends Game {
    private PongController controller;
    public Pong(String name, Image logo, String viewPath) {
        super(name, logo, viewPath);
    }

    @Override
    public void start(GameManager gameManager, BasicController controller) {
        this.controller = (PongController) controller;
        this.controller.setModel(new PongModel());
        this.controller.start();
    }
}
