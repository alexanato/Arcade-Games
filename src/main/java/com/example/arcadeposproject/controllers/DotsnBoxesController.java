package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.enums.Direction;
import com.example.arcadeposproject.enums.State;
import com.example.arcadeposproject.models.DotsnBoxesModel;
import com.example.arcadeposproject.models.Line;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class DotsnBoxesController extends BasicController {
    private DotsnBoxesModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    public static double cellSize;
    private int[] dragBegin;

    @FXML
    private void release(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY && dragBegin != null) {
            int roundx = (int) (event.getX()/cellSize);
            int roundy = (int) (event.getY()/cellSize);
            if((dragBegin[0]+1 == roundx && dragBegin[1] == roundy)||(dragBegin[0] == roundx && dragBegin[1]+1 == roundy)||(dragBegin[0]-1 == roundx && dragBegin[1] == roundy)||(dragBegin[0] == roundx && dragBegin[1]-1 == roundy)){
                model.changePlayer();
                model.addLine(new Line(dragBegin,new int[]{roundx,roundy},model.currentPlayer));
            }
            dragBegin = null;
            draw();
        }
    }
    @FXML
    private void move(MouseEvent event) {
        if(dragBegin != null) {
            draw();
            if(model.currentPlayer == State.PLAYER1_X){
                context.setStroke(Color.BLUE);
            }
            else{
                context.setStroke(Color.RED);
            }
            context.setLineWidth(10);
            context.strokeLine(dragBegin[0]*cellSize+5, dragBegin[1]*cellSize-cellSize/10+cellSize/2+5, event.getX(), event.getY());
        }
    }
    @FXML
    private void pressed(MouseEvent event) {
        if(event.getButton() == MouseButton.PRIMARY){
            Rectangle clickRect = new Rectangle(event.getX(), event.getY(),1,1);
            for(int i = 0;i<model.getGrid().length;i++){
                for(int j = 0;j<model.getGrid()[i].length;j++){
                    if(model.getGrid()[i][j].getBoundsInLocal().intersects(clickRect.getBoundsInLocal())){
                        dragBegin = new int[]{i,j};
                    }
                }
            }
        }
    }
    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        cellSize = canvas.getWidth()/10;
    }

    private void draw(){
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setLineWidth(10);
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
            if(line.getDirection() == Direction.UP || line.getDirection() == Direction.DOWN){
                context.strokeLine(line.startpos[0]*cellSize+3, line.startpos[1]*cellSize+cellSize/2, line.endpos[0]*cellSize+3, line.endpos[1]*cellSize+cellSize/2);
            }
            else{
                context.strokeLine(line.startpos[0]*cellSize+3, line.startpos[1]*cellSize+cellSize/2, line.endpos[0]*cellSize+3, line.endpos[1]*cellSize+cellSize/2);
            }
        }

    }

    @Override
    public void start() {
        model.addLine(new Line(new int[]{0,2}, new int[]{0,3},State.PLAYER1_X));
        model.addLine(new Line(new int[]{0,2}, new int[]{1,2},State.PLAYER2_O));
        model.addLine(new Line(new int[]{0,3}, new int[]{0,4},State.PLAYER2_O));
        draw();
    }
    @Override
    public void stop() {

    }
    public void setModel(DotsnBoxesModel model) {
        this.model = model;
    }

}
