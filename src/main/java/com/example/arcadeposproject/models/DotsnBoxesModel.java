package com.example.arcadeposproject.models;
import com.example.arcadeposproject.controllers.DotsnBoxesController;
import com.example.arcadeposproject.enums.Direction;
import com.example.arcadeposproject.enums.State;
import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;

public class DotsnBoxesModel {
    private ArrayList<Line> lines = new ArrayList<>();
    private Rectangle[][] grid = new Rectangle[10][10];
    public State currentPlayer = State.PLAYER1_X;
    public int[] playerPoints = new int[2];//player 1 = playerPoints[0]
    private boolean[][][] pointGivenPos = new boolean[9][9][2];//Ersten zwei stellen sind die Koordinate von einem besetzten Square und der letzte Index ist der Playerstate| false == player1

    public DotsnBoxesModel() {
        double cellSize = DotsnBoxesController.cellSize;
        for(int i = 0; i<10; i++){
            for(int j = 0; j<10; j++){
                grid[i][j] = new Rectangle(i* cellSize,j* cellSize-cellSize/10+cellSize/2,cellSize/5,cellSize/5);
            }
        }
    }
    public boolean isWinning(){
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                if(!getPointGivenPos()[i][j][0]){
                   return false;
                }
            }
        }
        return true;
    }

    public void addPoint(){
        for(int i = 0; i<9; i++){
            for(int j = 0; j<9; j++){
                if(!pointGivenPos[i][j][0] && validLine(new int[]{i,j},Direction.RIGHT) && validLine(new int[]{i,j},Direction.DOWN) && validLine(new int[]{i,j+1},Direction.RIGHT) && validLine(new int[]{i+1,j},Direction.DOWN)){
                    pointGivenPos[i][j][0] = true;
                        if (currentPlayer == State.PLAYER1_X) {
                            playerPoints[0]++;
                            pointGivenPos[i][j][1] = false;
                        } else {
                            pointGivenPos[i][j][1] = true;
                            playerPoints[1]++;
                        }
                    changePlayer();
                }
            }
        }
    }
    private boolean validLine(int[] startpos, Direction dir){
        for(Line line: lines){
            if(Arrays.equals(line.startpos, startpos) && startpos[0] + dir.getDir()[0] == line.endpos[0] && startpos[1] + dir.getDir()[1] == line.endpos[1]){
                return true;
            }
        }
        return false;
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
    public boolean[][][] getPointGivenPos() {
        return pointGivenPos;
    }

    public void setPointGivenPos(boolean[][][] pointGivenPos) {
        this.pointGivenPos = pointGivenPos;
    }


}
