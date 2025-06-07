package com.example.arcadeposproject.games;

import com.example.arcadeposproject.Game;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.controllers.BasicController;
import com.example.arcadeposproject.controllers.GomokuController;
import com.example.arcadeposproject.controllers.JumpAndRundController;
import com.example.arcadeposproject.models.GomokuModel;
import com.example.arcadeposproject.models.JumpAndRundModel;
import javafx.scene.image.Image;

public class JumpAndRund extends Game {
    private JumpAndRundController controller;

    public JumpAndRund(String name, Image logo, String viewPath) {
        super(name, logo, viewPath);
    }
    @Override
    public void start(GameManager gameManager, BasicController controller) {
        this.controller = (JumpAndRundController) controller;
        this.controller.setModel(new JumpAndRundModel());
        this.controller.start();
    }
}
