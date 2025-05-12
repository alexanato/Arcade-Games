package com.example.arcadeposproject.enums;

public enum Direction {
    NONE(new int[]{0,0}),
    UP(new int[]{0,-1}),
    DOWN(new int[]{0,1}),
    RIGHT(new int[]{1,0}),
    LEFT(new int[]{-1,0});
    private final int[] dir;
    Direction(int[] dir) {
        this.dir = dir;
    }
    public int[] getDir(){
        return dir;
    }
}
