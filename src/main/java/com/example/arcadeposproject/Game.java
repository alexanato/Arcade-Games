package com.example.arcadeposproject;

import com.example.arcadeposproject.controllers.BasicController;
import javafx.scene.image.Image;

public abstract class Game {
    public final String name;
    public final Image logo;
    public final String viewPath;
    public Game(String name,Image logo,String viewPath){
        this.name = name;
        this.logo = logo;
        this.viewPath = viewPath;
    }
    public abstract void start(GameManager gameManager, BasicController controller);
}
