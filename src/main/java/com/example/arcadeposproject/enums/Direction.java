package com.example.arcadeposproject.enums;

public enum Direction {
    NONE(new int[]{0,0}),
    UP(new int[]{0,-1}),
    DOWN(new int[]{0,1}),
    RIGHT(new int[]{1,0}),
    LEFT(new int[]{-1,0}),
    UPLEFT(new int[]{-1,-1}),
    DOWNLEFT(new int[]{-1,1}),
    UPRIGHT(new int[]{1,-1}),
    DOWNRIGHT(new int[]{1,1});

    private final int[] dir;
    Direction(int[] dir) {
        this.dir = dir;
    }
    public int[] getDir(){
        return dir;
    }
}
