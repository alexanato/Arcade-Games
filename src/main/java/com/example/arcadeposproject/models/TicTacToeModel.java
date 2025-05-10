package com.example.arcadeposproject.models;

import com.example.arcadeposproject.enums.State;

import java.util.Scanner;

public class TicTacToeModel {
    private State[][] board;
    private Scanner scanner;
    public State[][] getBoard(){
        return board;
    }
    public boolean getPlayer(){
        return player;
    }
    // false player1 true player 2
    private boolean player;
    public TicTacToeModel(){
        board = new State[3][3];
        for (int i = 0; i< board.length;i++){
            for (int j = 0; j <board[i].length;j++){
                board[i][j] = State.EMPTY;
            }
        }
        scanner = new Scanner(System.in);
    }
    private boolean isPlaceable(byte[] pos){
        if(board[pos[0]][pos[1]] == State.EMPTY) return true;
        return false;
    }
    private boolean place(byte[] pos){
        if(!isPlaceable(pos))return false;
        board[pos[0]][pos[1]] = ((player)?State.PLAYER2_O:State.PLAYER1_X);
        return true;
    }
    public void changeTurn(){
        player = !player;
    }
    public boolean isWinning(){
        State currentPlayer = ((player)?State.PLAYER2_O:State.PLAYER1_X);
        if(board[0][0].equals(currentPlayer)&&board[1][0].equals(currentPlayer)&&board[2][0].equals(currentPlayer)) return true;
        if(board[0][1].equals(currentPlayer)&&board[1][1].equals(currentPlayer)&&board[2][1].equals(currentPlayer)) return true;
        if(board[0][2].equals(currentPlayer)&&board[1][2].equals(currentPlayer)&&board[2][2].equals(currentPlayer)) return true;

        if(board[0][0].equals(currentPlayer)&&board[0][1].equals(currentPlayer)&&board[0][2].equals(currentPlayer)) return true;
        if(board[1][0].equals(currentPlayer)&&board[1][1].equals(currentPlayer)&&board[1][2].equals(currentPlayer)) return true;
        if(board[2][0].equals(currentPlayer)&&board[2][1].equals(currentPlayer)&&board[2][2].equals(currentPlayer)) return true;

        if(board[0][0].equals(currentPlayer)&&board[1][1].equals(currentPlayer)&&board[2][2].equals(currentPlayer)) return true;
        if(board[0][2].equals(currentPlayer)&&board[1][1].equals(currentPlayer)&&board[2][0].equals(currentPlayer)) return true;
        return false;
    }
    public boolean stalemate(){
        for (int i = 0; i< board.length;i++){
            for (int j = 0; j <board[i].length;j++){
                if(board[i][j].equals(State.EMPTY))return false;
            }
        }
        return true;
    }
    public void onTurn(byte[] input){
        if(!place(input)) return;
    }
}
