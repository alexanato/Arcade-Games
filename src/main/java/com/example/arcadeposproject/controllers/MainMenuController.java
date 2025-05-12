package com.example.arcadeposproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainMenuController extends BasicController{
    @FXML
    private ImageView preview;
    private int currentGameIndex;
    public int getCurrentGameIndex(){
        return getCurrentGameIndex();
    }
    private void  setCurrentGameIndex(int index){
        if(index<0)this.currentGameIndex = gameManager.gameCount()-1;
        else this.currentGameIndex = index;
    }
    @FXML
    private void nextGame(MouseEvent event) {
        Node source = (Node) event.getSource();
        int id =  Integer.parseInt(source.getId());
        setCurrentGameIndex ((currentGameIndex+id)%(gameManager.gameCount()));
        updatePreview();
    }
    private void updatePreview(){
        preview.setImage(gameManager.getGameLogo(currentGameIndex));
    }
    @FXML
    private void playGame() {
        gameManager.launch(currentGameIndex);
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
