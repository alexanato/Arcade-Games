package com.example.arcadeposproject.models;

import com.example.arcadeposproject.enums.DameState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DameModel {
    public DameState[][] board;
    public int currentPlayer = 1;
    //[0] anzahl,[1] = from pos [2] = to pos
    public int[][][] nextTurn;
    public boolean isnextJump(int[] from,int[] to){
        for(int i= 0;i<nextTurn.length;i++){
            if(Arrays.deepEquals(nextTurn[i], new int[][]{from, to})){
                return true;
            }
        }
        return false;
    }
    public int[][][] NextJumpPossible(int[] pos){
        List<int[][]> outputList = new ArrayList<>();
        DameDragData data = new DameDragData();
        data.state = board[pos[0]][pos[1]];
        data.dragBeginn = pos;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isValidJump(new int[]{i, j}, data)) {
                    int[][] move = new int[2][2];
                    move[0] = pos;
                    move[1] = new int[]{i, j};
                    outputList.add(move);
                }
            }
        }

        if (outputList.isEmpty()) {
            return null;
        }

        return outputList.toArray(new int[outputList.size()][][]);
    }
    public void changeTurn(){
        currentPlayer = (currentPlayer+2)%2+1;
    }
    public DameModel(){
        board = new DameState[8][8];
        for(int i = 0;i<board.length;i++){
            Arrays.fill(board[i],DameState.EMPTY);
        }
        for(int x = 0; x<7;x++){
            for (int y = 0; y<3;y++){
                if((x+y)%2 == 0){
                    board[x+1][y] = DameState.BLACK;
                }
                else {
                    board[x][y] = DameState.BLACK;
                }
                if((x+y)%2 == 1){
                    board[x+1][y+5] = DameState.RED;
                }
                else {
                    board[x][y+5] = DameState.RED;
                }
            }
        }
    }
    public boolean isValidJump(int[] pos,DameDragData dragData){
        int direction =((dragData.state == DameState.BLACK)?1:-1);
        for(int i = -1;i<2;i +=2){
            if(board[pos[0]][pos[1]] == DameState.EMPTY) {
                if((dragData.dragBeginn[0]-i*2==pos[0]&&dragData.dragBeginn[1]+i*2==pos[1])) {
                    DameState state = board[dragData.dragBeginn[0]-i][dragData.dragBeginn[1]+i];
                    if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                        return true;
                    }
                }
                if((dragData.dragBeginn[0]+i*2==pos[0]&&dragData.dragBeginn[1]+i*2==pos[1])) {
                    DameState state = board[dragData.dragBeginn[0]+i][dragData.dragBeginn[1]+i];
                    if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                        return true;
                    }
                }
            }
        }
        if(board[pos[0]][pos[1]] == DameState.EMPTY) {
            if((dragData.dragBeginn[0]-direction*2==pos[0]&&dragData.dragBeginn[1]+direction*2==pos[1])) {
                DameState state = board[dragData.dragBeginn[0]-direction][dragData.dragBeginn[1]+direction];
                if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                    return true;
                }
            }
            if((dragData.dragBeginn[0]+direction*2==pos[0]&&dragData.dragBeginn[1]+direction*2==pos[1])) {
                DameState state = board[dragData.dragBeginn[0]+direction][dragData.dragBeginn[1]+direction];
                if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                    return true;
                }
            }
        }
        return false;
    }
    public boolean isValid(int[] pos,DameDragData dragData){
        int direction =((dragData.state == DameState.BLACK)?1:-1);
        if(DameState.BLACK_KING == dragData.state||DameState.RED_KING == dragData.state){
            for(int i = -1;i<2;i +=2){
                if(board[pos[0]][pos[1]] == DameState.EMPTY) {
                    if((dragData.dragBeginn[0]-i==pos[0]&&dragData.dragBeginn[1]+i==pos[1])) return true;
                    if((dragData.dragBeginn[0]+i==pos[0]&&dragData.dragBeginn[1]+i==pos[1])) return true;
                };
                if(board[pos[0]][pos[1]] == DameState.EMPTY) {
                    if((dragData.dragBeginn[0]-i*2==pos[0]&&dragData.dragBeginn[1]+i*2==pos[1])) {
                        DameState state = board[dragData.dragBeginn[0]-i][dragData.dragBeginn[1]+i];
                        if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                            board[dragData.dragBeginn[0]-i][dragData.dragBeginn[1]+i] = DameState.EMPTY;
                            return true;
                        }
                    }
                }
                if(board[pos[0]][pos[1]] == DameState.EMPTY) {
                    if((dragData.dragBeginn[0]+i*2==pos[0]&&dragData.dragBeginn[1]+i*2==pos[1])) {
                        DameState state = board[dragData.dragBeginn[0]+i][dragData.dragBeginn[1]+i];
                        if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                            board[dragData.dragBeginn[0]+i][dragData.dragBeginn[1]+i] = DameState.EMPTY;
                            return true;
                        }
                    }
                }
            }
        }
        if(board[pos[0]][pos[1]] == DameState.EMPTY) {
            if((dragData.dragBeginn[0]-direction==pos[0]&&dragData.dragBeginn[1]+direction==pos[1])) return true;
            if((dragData.dragBeginn[0]+direction==pos[0]&&dragData.dragBeginn[1]+direction==pos[1])) return true;
        };
        if(board[pos[0]][pos[1]] == DameState.EMPTY) {
            if((dragData.dragBeginn[0]-direction*2==pos[0]&&dragData.dragBeginn[1]+direction*2==pos[1])) {
                DameState state = board[dragData.dragBeginn[0]-direction][dragData.dragBeginn[1]+direction];
                if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                    board[dragData.dragBeginn[0]-direction][dragData.dragBeginn[1]+direction] = DameState.EMPTY;
                    return true;
                }
            }
        }
        if(board[pos[0]][pos[1]] == DameState.EMPTY) {
            if((dragData.dragBeginn[0]+direction*2==pos[0]&&dragData.dragBeginn[1]+direction*2==pos[1])) {
                DameState state = board[dragData.dragBeginn[0]+direction][dragData.dragBeginn[1]+direction];
                if((state!=DameState.EMPTY&&state.getNumber()!=dragData.state.getNumber())) {
                    board[dragData.dragBeginn[0]+direction][dragData.dragBeginn[1]+direction] = DameState.EMPTY;
                    return true;
                }
            }
        }
        return false;
    }
    public static ArrayList<Integer[][]> addtoList(int[]... arrays) {
        ArrayList<Integer[][]> result = new ArrayList<>();

        for (int[] arr : arrays) {
            if (arr == null) continue; // Sicherheit

            // Umwandlung in Integer[1][n]
            Integer[][] matrix = new Integer[1][arr.length];
            for (int i = 0; i < arr.length; i++) {
                matrix[0][i] = arr[i];  // Autoboxing
            }

            // Zur Liste hinzufügen
            result.add(matrix);
        }

        return result;
    }
}
