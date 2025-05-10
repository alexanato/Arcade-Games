package com.example.arcadeposproject;

import com.example.arcadeposproject.controllers.BasicController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GameManager {
    private final Stage stage;
    private final List<Game> games = new ArrayList<>();
    private int currentGameIndex;
    public GameManager(Stage stage) {
        this.stage = stage;
    }
    public void addGame(Game game) {
        games.add(game);
    }
    public int gameCount(){
        return games.size();
    }
    public void reloadGame(){
        launch(currentGameIndex);
    }
    public Game getGame(int index){
        return games.get(index);
    }
    public Image getGameLogo(int index){
        return games.get(index).logo;
    }
    public void launch(int gameIndex){
        Game game = getGame(gameIndex);
        game.start(this,switchTo(game.viewPath));
        currentGameIndex = gameIndex;
    }
    public void returnToMenu(){
        switchTo("MainMenu.fxml");
    }
    public void sendResult(String message){
        switchTo("Result.fxml",message );
    }
    public BasicController switchTo(String fxmlFile){
        BasicController controller = null;
        try{
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource ("View/"+fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
            controller = (BasicController) loader.getController();
            controller.init(this);
        }catch (IOException e){
            e.printStackTrace();
        }
        return controller;
    }
    public BasicController switchTo(String fxmlFile,String message){
        BasicController controller = null;
        try{
            FXMLLoader loader = new FXMLLoader(MainApp.class.getResource ("View/"+fxmlFile));
            Parent root = loader.load();
            stage.setScene(new Scene(root));
            stage.show();
            controller = (BasicController) loader.getController();
            controller.init(this,message);
        }catch (IOException e){
            e.printStackTrace();
        }
        return controller;
    }
}