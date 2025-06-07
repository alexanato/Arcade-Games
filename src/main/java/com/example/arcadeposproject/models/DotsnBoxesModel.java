package com.example.arcadeposproject.models;

import java.util.ArrayList;

public class DotsnBoxesModel {
    private ArrayList<Line> lines = new ArrayList<>();

    public ArrayList<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        lines.add(line);
    }

}
