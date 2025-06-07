package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.enums.State;
import com.example.arcadeposproject.models.DotsnBoxesModel;
import com.example.arcadeposproject.models.Line;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DotsnBoxesController extends BasicController {
    private DotsnBoxesModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    private double cellSize;

    @FXML
    private void release(MouseEvent event) {

    }
    @FXML
    private void move(MouseEvent event) {

    }
    @FXML
    private void pressed(MouseEvent event) {

    }
    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        cellSize = canvas.getWidth()/10;
    }

    private void drawDragPos(){

    }

    private void draw(){
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setLineWidth(cellSize/2.5);
        context.setFill(Color.WHITE);
        for(int i = 0; i<canvas.getWidth(); i+= (int) cellSize){
            for(int j = (int) (cellSize/2); j<canvas.getHeight(); j+= (int) cellSize){
                context.fillOval(i, j-cellSize/10, cellSize/5, cellSize/5);
            }
        }
        for(Line line: model.getLines()){
            if(line.player == State.PLAYER1_X){
                context.setStroke(Color.RED);
            }
            else{
                context.setStroke(Color.BLUE);
            }
            context.strokeLine(line.startpos[0]*cellSize, line.startpos[1]*cellSize+cellSize/2, line.endpos[0]*cellSize, line.endpos[1]*cellSize+cellSize/2);
        }

    }

    @Override
    public void start() {
        model.addLine(new Line(new int[]{0,2}, new int[]{0,3},State.PLAYER1_X));
        model.addLine(new Line(new int[]{0,2}, new int[]{1,2},State.PLAYER1_X));
        draw();
    }
    @Override
    public void stop() {

    }
    public void setModel(DotsnBoxesModel model) {
        this.model = model;
    }

}
