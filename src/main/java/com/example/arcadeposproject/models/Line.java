package com.example.arcadeposproject.models;

import com.example.arcadeposproject.enums.Direction;
import com.example.arcadeposproject.enums.State;

import java.util.Arrays;

public class Line {
    public final int[] startpos;
    public final int[] endpos;
    public final State player;
    public Line(int[] startpos, int[] endpos, State player) {
        this.startpos = startpos;
        this.endpos = endpos;
        this.player = player;
    }
    public Direction getDirection() {
        int deltaX = endpos[0] - startpos[0];
        int deltaY = endpos[1] - startpos[1];
        for(Direction d : Direction.values()) {
            if(Arrays.equals(d.getDir(), new int[]{deltaX, deltaY})) {
                return d;
            }
        }
        return null;
    }
}
