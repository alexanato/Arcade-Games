package com.example.arcadeposproject.models;

import com.example.arcadeposproject.controllers.JumpAndRundController;
import javafx.scene.input.KeyCode;
import toxi.physics2d.VerletPhysics2D;

import java.util.ArrayList;

public class JumpAndRundModel {
    public VerletPhysics2D physics = new VerletPhysics2D();
    public ArrayList<Softbody> softbodies = new ArrayList<>();
    public void keyHandle(ArrayList<KeyCode> input){

    }
    public void update(double deltaTime){
    }
}
