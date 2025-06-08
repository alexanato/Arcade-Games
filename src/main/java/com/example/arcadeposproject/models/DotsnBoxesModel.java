package com.example.arcadeposproject.models;

import com.example.arcadeposproject.controllers.DotsnBoxesController;
import com.example.arcadeposproject.enums.State;
import javafx.scene.shape.Rectangle;


import java.util.ArrayList;
import java.util.Arrays;

public class DotsnBoxesModel {
    private ArrayList<Line> lines = new ArrayList<>();
    private Rectangle[][] grid = new Rectangle[10][10];
    public State currentPlayer = State.PLAYER1_X;
    public DotsnBoxesModel() {
        double cellSize = DotsnBoxesController.cellSize;
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                grid[i][j] = new Rectangle(i* cellSize,j* cellSize-cellSize/10+cellSize/2,cellSize/5,cellSize/5);
            }
        }
    }
    public void changePlayer(){
        if(currentPlayer == State.PLAYER1_X){
            currentPlayer = State.PLAYER2_O;
        }
        else{
            currentPlayer = State.PLAYER1_X;
        }
    }
    public Rectangle[][] getGrid() {
        return grid;
    }

    public void setGrid(Rectangle[][] grid) {
        this.grid = grid;
    }

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        for(Line l : lines){
            if((Arrays.equals(l.startpos, line.startpos) && Arrays.equals(l.endpos, line.endpos))){
                return;
            }
        }
        lines.add(line);
    }

}
