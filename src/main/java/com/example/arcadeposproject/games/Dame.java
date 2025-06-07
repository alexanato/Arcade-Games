package com.example.arcadeposproject.games;

import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.controllers.BasicController;
import com.example.arcadeposproject.controllers.DameController;
import com.example.arcadeposproject.controllers.PongController;
import com.example.arcadeposproject.controllers.SnakeController;
import com.example.arcadeposproject.models.DameModel;
import com.example.arcadeposproject.models.PongModel;
import com.example.arcadeposproject.models.SnakeModel;
import javafx.scene.image.Image;

public class Dame extends Game {
    private DameController controller;
    public Dame(String name, Image logo, String viewPath) {
        super(name, logo, viewPath);
    }
    @Override
    public void start(GameManager gameManager, BasicController controller) {
        this.controller = (DameController) controller;
        this.controller.setModel(new DameModel());
        this.controller.start();
    }
}
