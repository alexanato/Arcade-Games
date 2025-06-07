package com.example.arcadeposproject.models;

import toxi.geom.Vec2D;
import toxi.physics2d.VerletSpring2D;

public class NearestSpringData {
    public final Vec2D dir;
    public final VerletSpring2D spring;
    public NearestSpringData(Vec2D dir,VerletSpring2D spring){
        this.dir = dir;
        this.spring = spring;
    }
}
