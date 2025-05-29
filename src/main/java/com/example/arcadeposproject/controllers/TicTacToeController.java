package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.Assets;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.enums.State;
import com.example.arcadeposproject.models.TicTacToeModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TicTacToeController extends BasicController {
    private TicTacToeModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    private boolean blinkingState = false;

    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
    }
    public void start(){
        drawBoard();
    }
    public void stop() {

    }
    @FXML
    private void click(MouseEvent event) {
        if(model.isWinning())return;
        byte x = (byte) (event.getX() / 133.33);
        byte y = (byte) (event.getY() / 133.33);

        model.onTurn(new byte[]{x, y});
        if(model.isWinning()){
            endAnimation();
        } else if (model.stalemate()) {
            gameManager.sendResult("tie!!");
        }
        drawBoard();
    }
    private  void  endAnimation(){
        Timeline blinking = new Timeline(
                new KeyFrame(javafx.util.Duration.millis(100), e -> {
                    blinkingState = !blinkingState;
                    drawBoard();
                })
        );
        blinking.setCycleCount(Timeline.INDEFINITE);
        Timeline animation = new Timeline(
                new KeyFrame(javafx.util.Duration.millis(500), e -> {
                    System.out.println("DAS ERSTE");
                    gameManager.sendResult(((!model.getPlayer())? "O has won":"X has won"));
                    stop();
                })
        );
        blinking.play();
        animation.play();
    }
    private void drawBoard() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.BLACK);
        context.fillRect(133.33, 0, 10, canvas.getHeight());
        context.fillRect(266.66, 0, 10, canvas.getHeight());

        context.fillRect(0, 133.33, canvas.getWidth(), 10);
        context.fillRect(0, 266.66, canvas.getWidth(), 10);
        byte[][] winningPos = model.getWinningPos();
        for (int i = 0; i < model.getBoard().length; i++) {
            for (int j = 0; j < model.getBoard()[i].length; j++) {
                switch (model.getBoard()[i][j]) {
                    case PLAYER1_X -> {
                        context.drawImage(Assets.X,133.33*i+1,133.33*j+1);
                    }
                    case PLAYER2_O -> {
                        context.drawImage(Assets.O,133.33*i+1,133.33*j+1);
                    }
                }
                if(winningPos != null&&!blinkingState){
                    for (byte[] pos: winningPos){
                        if(pos[0] == i && pos[1] ==j){
                            switch (model.getBoard()[i][j]) {
                                case PLAYER1_X -> {
                                    context.drawImage(Assets.X_WHITE,133.33*i+1,133.33*j+1);
                                }
                                case PLAYER2_O -> {
                                    context.drawImage(Assets.O_WHITE,133.33*i+1,133.33*j+1);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public TicTacToeModel getModel() {
        return model;
    }
    public void setModel(TicTacToeModel model) {
        if(model != null)this.model = model;
    }
}