package com.example.arcadeposproject.models;

import com.example.arcadeposproject.enums.State;

public class Line {
    public final int[] startpos;
    public final int[] endpos;
    public final State player;
    public Line(int[] startpos, int[] endpos, State player) {
        this.startpos = startpos;
        this.endpos = endpos;
        this.player = player;
    }
}
