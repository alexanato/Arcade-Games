package com.example.arcadeposproject.models;

import com.example.arcadeposproject.enums.Direction;
import com.example.arcadeposproject.enums.State;

import java.util.Arrays;

public class GomokuModel {
    public boolean currentPlayer = false;//false = player 1
    private State[][] board;

    public GomokuModel() {
        board = new State[15][15];
        for (int i = 0; i < board.length;i++) {
            Arrays.fill(board[i], State.EMPTY);
        }
    }

    public boolean isPlaceable(int[] pos){
        if(board[pos[0]][pos[1]] == State.EMPTY) return true;
        return false;
    }

    public boolean place(int[] pos){
        if(!isPlaceable(pos))return false;
        board[pos[0]][pos[1]] = ((currentPlayer)?State.PLAYER2_O:State.PLAYER1_X);
        changeTurn();
        return true;
    }
    public boolean isWinning(int x,int y){
        State playerState = board[x][y];
        int toWin = 4;
        for(Direction dir : Direction.values()){
            if(dir  == Direction.NONE) continue;
            int[] dirPos = new int[]{x+dir.getDir()[0],y+dir.getDir()[1]};
            int inRow =0;
            for (int i = 0; i<toWin;i++){
                if(!isInBoard(dirPos)||board[dirPos[0]][dirPos[1]] != playerState)break;
                System.out.println(Arrays.toString(dirPos));
                dirPos = new int[]{dirPos[0]+dir.getDir()[0],dirPos[1]+dir.getDir()[1]};
                inRow++;
            }
            if(inRow ==4) return true;
        }
        return false;
    }
    public boolean isInBoard(int[] pos){
        try {
            State state = board[pos[0]][pos[1]];
            return true;
        }catch (ArrayIndexOutOfBoundsException e){
            return false;
        }
    }
    public void changeTurn(){
        currentPlayer = !currentPlayer;
    }

    public boolean stalemate(){
        for (int i = 0; i< board.length;i++){
            for (int j = 0; j <board[i].length;j++){
                if(board[i][j].equals(State.EMPTY))return false;
            }
        }
        return true;
    }

}
