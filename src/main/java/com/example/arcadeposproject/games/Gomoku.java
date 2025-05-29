package com.example.arcadeposproject.games;

import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.controllers.BasicController;
import com.example.arcadeposproject.controllers.GomokuController;
import com.example.arcadeposproject.controllers.PongController;
import com.example.arcadeposproject.models.GomokuModel;
import com.example.arcadeposproject.models.PongModel;
import javafx.scene.image.Image;

public class Gomoku extends Game{

    private GomokuController controller;

    public Gomoku(String name, Image logo, String viewPath) {
        super(name, logo, viewPath);
    }
    @Override
    public void start(GameManager gameManager, BasicController controller) {
        this.controller = (GomokuController) controller;
        this.controller.setModel(new GomokuModel());
        this.controller.start();
    }
}
