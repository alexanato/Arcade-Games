package com.example.arcadeposproject.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MainMenuController extends BasicController{
    @FXML
    private ImageView preview;
    private int currentGameIndex;
    public int getCurrentGameIndex(){
        return getCurrentGameIndex();
    }
    public void  setCurrentGameIndex(int index){
        if(index<0)this.currentGameIndex = gameManager.gameCount()-1;
        else this.currentGameIndex = index;
    }
    @FXML
    private void nextGame(ActionEvent event) {
        int id =  Integer.parseInt(((Button) event.getSource()).getId());
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
}
