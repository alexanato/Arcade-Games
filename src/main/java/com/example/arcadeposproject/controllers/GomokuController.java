package com.example.arcadeposproject.controllers;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.enums.State;
import com.example.arcadeposproject.models.GomokuModel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;

import javax.swing.*;

public class GomokuController extends BasicController {
    @FXML
    private Canvas canvas;
    private GomokuModel model;
    private GraphicsContext context;
    private double cellsize;
    private int[] previewPos = new int[2];
    @FXML
    private void preview(MouseEvent event) {
        draw();
        int roundX = (int) (event.getX()/cellsize);
        int roundY = (int) (event.getY()/cellsize);
        previewPos[0] = roundX;
        previewPos[1] = roundY;


    }
    @FXML
    private void click(MouseEvent event) {
        int roundX = (int) (event.getX()/cellsize);
        int roundY = (int) (event.getY()/cellsize);
        model.place(new int[] {roundX, roundY});
        model.isWinning(roundX, roundY);
        draw();
        if(model.isWinning(roundX,roundY) && !model.currentPlayer){
            gameManager.sendResult("White won!");
        }
        else if(model.isWinning(roundX,roundY)){
            gameManager.sendResult("Black won!");
        }
    }

    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        cellsize = canvas.getWidth()/15;
    }
    public void draw(){
        context.setFill(Color.rgb(76,45,16));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());;
        context.setFill(Color.WHITE);
        for(double i = 0;i<=canvas.getWidth();i+=cellsize){
            context.fillRect(i+cellsize/2,0, 1, canvas.getHeight());
        }
        for(double j = 0; j<=canvas.getHeight();j+= cellsize){
            context.fillRect(0, j+cellsize/2, canvas.getWidth(), 1);
        }
        if(!model.currentPlayer){
            context.setFill(Color.BLACK);
            context.fillOval(previewPos[0]*cellsize+cellsize/4, previewPos[1]*cellsize+cellsize/4, cellsize/2, cellsize/2);
        }
        else{
            context.setFill(Color.WHITE);
            context.fillOval(previewPos[0]*cellsize+cellsize/4, previewPos[1]*cellsize+cellsize/4, cellsize/2, cellsize/2);
        }
        for(int i =0;i< model.board.length;i++){
            for(int j =0;j< model.board[i].length;j++){
                if(model.board[i][j] != State.EMPTY){
                    if(model.board[i][j] == State.PLAYER1_X){
                        context.setFill(Color.BLACK);
                        context.fillOval(i * cellsize + cellsize / 4, j * cellsize + cellsize / 4, cellsize / 2, cellsize / 2);
                    }
                    else {
                        context.setFill(Color.WHITE);
                        context.fillOval(i * cellsize + cellsize / 4, j * cellsize + cellsize / 4, cellsize / 2, cellsize / 2);
                    }
                }
            }
        }
    }

    public void start(){
        draw();
    }

    public void stop() {

    }

    public void setModel(GomokuModel gomokuModel) {
        this.model = gomokuModel;
    }
}

