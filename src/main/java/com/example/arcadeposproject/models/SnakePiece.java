package com.example.arcadeposproject.models;

import com.example.arcadeposproject.enums.Direction;

public class SnakePiece {
    public final int x;
    public final int y;
    private Direction direction;

    public SnakePiece(int x, int y,Direction direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
    public SnakePiece(int[] pos,Direction direction) {
        this.x = pos[0];
        this.y = pos[1];
        this.direction = direction;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SnakePiece)) return false;
        SnakePiece piece = (SnakePiece) o;
        return x == piece.x && y == piece.y;
    }
    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + x;
        result = 31 * result + y;
        return result;
    }
    public int[] getPos(){
        return new int[]{x,y};
    }
    public void setDirection(Direction dir){
        this.direction =dir;
    }
    public Direction getDirection(){
        return direction;
    }
}
