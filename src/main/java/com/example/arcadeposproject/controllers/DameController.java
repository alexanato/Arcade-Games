package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.FPS;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.enums.DameState;
import com.example.arcadeposproject.models.DameDragData;
import com.example.arcadeposproject.models.DameModel;
import com.example.arcadeposproject.models.PongModel;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Arrays;

public class DameController  extends BasicController{

    private DameModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    private double cellSize;
    private DameDragData dragData;

    @FXML
    private void release(MouseEvent event) {
        if (dragData == null) return;

        int roundX = (int) (event.getX() / cellSize);
        int roundY = (int) (event.getY() / cellSize);
        try {
            DameState state =  model.board[roundX][roundY];
        }catch (ArrayIndexOutOfBoundsException a){
            return;
        }
        int[] target = new int[]{roundX, roundY};

        if (model.isValid(target, dragData)) {

            // Damenumwandlung
            if (roundY == 7 && dragData.state == DameState.BLACK) {
                dragData.state = DameState.BLACK_KING;
            }
            if (roundY == 0 && dragData.state == DameState.RED) {
                dragData.state = DameState.RED_KING;
            }

            model.board[roundX][roundY] = dragData.state;

            // Aktuellen Spieler ermitteln

            // Spielerwechsel
            model.changeTurn();

            // Sieg prüfen (ob Gegner keine Steine mehr hat)


        } else {
            // Ungültiger Zug → Figur zurücksetzen
            int[] start = dragData.dragBeginn;
            model.board[start[0]][start[1]] = dragData.state;
        }
        int currentPlayer = dragData.state.getNumber();
        int opponent = currentPlayer;
        if (model.isWinning(opponent)) {
            if (currentPlayer == 0) {
                System.out.println("a");
                gameManager.sendResult("Red won");
            } else {
                System.out.println("b");
                gameManager.sendResult("Black won");
            }
        }
        dragData = null;
        draw();
    }
    @FXML
    private void move(MouseEvent event) {
        if(dragData != null) {
            dragData.dragPos = new double[]{event.getX()-cellSize/3,event.getY()-cellSize/3};
            draw();
            drawDragPos();
        }
    }
    @FXML
    private void pressed(MouseEvent event) {
        int roundX = (int)(event.getX()/cellSize);
        int roundY = (int)(event.getY()/cellSize);
        if(event.getButton().name().equals("PRIMARY")&&model.board[roundX][roundY] != DameState.EMPTY&&model.board[roundX][roundY].getNumber() == model.currentPlayer){
            dragData = new DameDragData();
            dragData.dragBeginn = new int[]{roundX,roundY};
            dragData.state =model.board[roundX][roundY];
            model.board[roundX][roundY] = DameState.EMPTY;
            dragData.dragPos = new double[]{event.getX()-cellSize/3,event.getY()-cellSize/3};
            draw();
            drawDragPos();
        }
    }
    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        cellSize = canvas.getWidth()/8;
    }
    private void drawDragPos(){
        if(dragData == null) return;
        if(dragData.state == DameState.BLACK){
            context.setFill(Color.BLACK);
            context.fillOval(dragData.dragPos[0],dragData.dragPos[1],cellSize/1.5,cellSize/1.5);
        }
        if(dragData.state == DameState.RED){
            context.setFill(Color.BEIGE);
            context.fillOval(dragData.dragPos[0],dragData.dragPos[1],cellSize/1.5,cellSize/1.5);
        }
        else if (dragData.state == DameState.RED_KING){
            context.setFill(Color.BEIGE);
            context.fillOval(dragData.dragPos[0],dragData.dragPos[1],cellSize/1.5,cellSize/1.5);
            context.setFill(Color.color(0.6,0.6,0.6));
            context.fillOval(dragData.dragPos[0]+cellSize/6,dragData.dragPos[1]+cellSize/6,cellSize/3,cellSize/3);
        }
        else if (dragData.state == DameState.BLACK_KING){
            context.setFill(Color.BLACK);
            context.fillOval(dragData.dragPos[0],dragData.dragPos[1],cellSize/1.5,cellSize/1.5);
            context.setFill(Color.GREY);
            context.fillOval(dragData.dragPos[0]+cellSize/6,dragData.dragPos[1]+cellSize/6,cellSize/3,cellSize/3);
        }
    }
    private void draw(){
        for (int x = 0; x<8;x++){
            for (int y = 0; y<8;y++){
                if((x+y)%2 == 0) context.setFill(Color.BLACK);
                else context.setFill(Color.DARKRED);
                context.fillRect(x*cellSize,y*cellSize,cellSize,cellSize);
            }
        }
        for (int x = 0; x<model.board.length;x++){
            for (int y = 0; y<model.board[x].length;y++){
                if(model.board[x][y] == DameState.BLACK){
                    context.setFill(Color.BLACK);
                    context.fillOval(x*cellSize+cellSize/6,y*cellSize+cellSize/6,cellSize/1.5,cellSize/1.5);
                }
                else if(model.board[x][y] == DameState.RED){
                    context.setFill(Color.BEIGE);
                    context.fillOval(x*cellSize+cellSize/6,y*cellSize+cellSize/6,cellSize/1.5,cellSize/1.5);
                }
                else if (model.board[x][y] == DameState.RED_KING){
                    context.setFill(Color.BEIGE);
                    context.fillOval(x*cellSize+cellSize/6,y*cellSize+cellSize/6,cellSize/1.5,cellSize/1.5);
                    context.setFill(Color.color(0.6,0.6,0.6));
                    context.fillOval(x*cellSize+cellSize/3,y*cellSize+cellSize/3,cellSize/3,cellSize/3);
                }
                else if (model.board[x][y] == DameState.BLACK_KING){
                    context.setFill(Color.BLACK);
                    context.fillOval(x*cellSize+cellSize/6,y*cellSize+cellSize/6,cellSize/1.5,cellSize/1.5);
                    context.setFill(Color.GREY);
                    context.fillOval(x*cellSize+cellSize/3,y*cellSize+cellSize/3,cellSize/3,cellSize/3);
                }
            }
        }
    }

    @Override
    public void start() {
        draw();
    }
    @Override
    public void stop() {

    }
    public void setModel(DameModel model) {
        this.model = model;
    }
}
