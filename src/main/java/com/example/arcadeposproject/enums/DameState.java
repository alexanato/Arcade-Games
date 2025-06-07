package com.example.arcadeposproject.enums;

public enum DameState {
    EMPTY(0),
    RED(1),
    RED_KING(1),
    BLACK(2),
    BLACK_KING(2);
    private final int number;
    DameState(int number) {
        this.number = number;
    }
    public int getNumber(){
        return number;
    }
}
