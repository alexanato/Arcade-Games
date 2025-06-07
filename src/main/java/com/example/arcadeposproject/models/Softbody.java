package com.example.arcadeposproject.models;

import javafx.scene.paint.Color;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletSpring2D;

import java.util.ArrayList;
import java.util.List;

public class Softbody {
    public List<VerletParticle2D> points = new ArrayList<>();
    public List<VerletSpring2D> springs = new ArrayList<>();
    public List<VerletSpring2D> boundary_Edge = new ArrayList<>();
    private Rect rect;
    private Color color;
    public Softbody(String bodyName){
    }
    public void update(){
        rect = calcRect();
    }
    public Vec2D[] collision(ArrayList<Softbody> softbodies){
        for (Softbody softbody: softbodies){
            if(softbody != this){
                if(getRect().intersectsRect(softbody.getRect())){
                    ArrayList<VerletParticle2D> penetratePoints = checkRayCastPointCollision(softbody, checkRectPointCollision(softbody));
                    for (VerletParticle2D point : penetratePoints) {
                        NearestSpringData data = getNearestSpring(point, softbody);
                        Vec2D penetration = data.dir;
                        float depth = penetration.magnitude();
                        Vec2D normal = penetration.normalizeTo(1);
                        float k = 0.1f; // "Weichheit" der Kollision

                        Vec2D force = normal.scale(-k * depth);
                        point.addForce(force);

                        // Optional: kleine Rückkraft auf den anderen Körper
                        data.spring.a.addForce(force.scale(-0.9f));
                        data.spring.b.addForce(force.scale(-0.9f));

                        // Optional: Dämpfung
                        Vec2D damping = point.getVelocity().scale(0.1f);
                        point.addForce(damping);
                    }
                }
            }
        }
        return new Vec2D[]{new Vec2D(), new Vec2D()};
    }
    private NearestSpringData getNearestSpring(VerletParticle2D point,Softbody softbody){
        Vec2D distance = new Vec2D(Float.MAX_VALUE,Float.MAX_VALUE);
        VerletSpring2D outSpring = null;
        for (VerletSpring2D spring: softbody. boundary_Edge){
            Vec2D dir = utils.calcDirToLine(spring.b,spring.a,point);
            if(distance.magnitude() > dir.magnitude()){
                distance =dir;
                outSpring = spring;
            }
        }
        return new NearestSpringData(distance.scale(1),outSpring);
    }
    private ArrayList<VerletParticle2D> checkRayCastPointCollision(Softbody softbody,ArrayList<VerletParticle2D> rectPoints){
        ArrayList<VerletParticle2D> collidePoints = new ArrayList<VerletParticle2D>();
        for(VerletParticle2D point:rectPoints){
            int count = 0;
            for (VerletSpring2D spring : softbody.boundary_Edge){
                if(utils.intersectRaySegment(point,spring.a,spring.b)){
                    count++;
                }
            }
            if(count%2 ==1)collidePoints.add(point);
        }
        return collidePoints;
    }
    private ArrayList<VerletParticle2D> checkRectPointCollision(Softbody softbody){
        ArrayList<VerletParticle2D> collidePoints = new ArrayList<VerletParticle2D>();
        for (VerletParticle2D point : points){
            if(new Rect(point.x, point.y,1,1 ).intersectsRect(softbody.getRect())){
                collidePoints.add(point);
            }
        }
        return collidePoints;
    }
    public Rect getRect(){
        return rect;
    }
    private Rect calcRect(){
        Vec2D min = new Vec2D(Float.MAX_VALUE, Float.MAX_VALUE);
        Vec2D max = new Vec2D(Float.MIN_VALUE, Float.MIN_VALUE);
        for (VerletParticle2D point : points) {
            if (point.x < min.x) min.x = point.x;
            if (point.y < min.y) min.y = point.y;
            if (point.x > max.x) max.x = point.x;
            if (point.y > max.y) max.y = point.y;
        }
        return new Rect(min, max);
    }
}
