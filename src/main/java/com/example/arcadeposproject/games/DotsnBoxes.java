package com.example.arcadeposproject.games;

import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.controllers.BasicController;
import com.example.arcadeposproject.controllers.DotsnBoxesController;
import com.example.arcadeposproject.controllers.GomokuController;
import com.example.arcadeposproject.models.DotsnBoxesModel;
import com.example.arcadeposproject.models.GomokuModel;
import javafx.scene.image.Image;

public class DotsnBoxes extends Game {
    private DotsnBoxesController controller;

    public DotsnBoxes(String name, Image logo, String viewPath) {
        super(name, logo, viewPath);
    }
    @Override
    public void start(GameManager gameManager, BasicController controller) {
        this.controller = (DotsnBoxesController) controller;
        this.controller.setModel(new DotsnBoxesModel());
        this.controller.start();
    }
}
