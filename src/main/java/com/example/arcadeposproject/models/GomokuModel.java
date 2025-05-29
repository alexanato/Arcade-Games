package com.example.arcadeposproject.models;

import com.example.arcadeposproject.enums.State;

import java.util.Arrays;

public class GomokuModel {
    public boolean currentPlayer = false;//false = player 1
    private State[][] board;

    public GomokuModel() {
        board = new State[15][15];
        Arrays.fill(board, State.EMPTY);
    }

    public boolean isPlaceable(int[] pos){
        if(board[pos[0]][pos[1]] == State.EMPTY) return true;
        return false;
    }

    public boolean place(int[] pos){
        if(!isPlaceable(pos))return false;
        board[pos[0]][pos[1]] = ((currentPlayer)?State.PLAYER2_O:State.PLAYER1_X);
        return true;
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
