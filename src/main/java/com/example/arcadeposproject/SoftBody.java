package com.example.arcadeposproject;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import toxi.geom.Polygon2D;
import toxi.geom.Vec2D;
import toxi.geom.Line2D;
import toxi.physics2d.VerletPhysics2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletSpring2D;

import java.util.ArrayList;
import java.util.List;

public class SoftBody extends Application {

    private VerletPhysics2D physics;
    private List<VerletParticle2D> bodyA = new ArrayList<>();
    private List<VerletParticle2D> bodyB = new ArrayList<>();
    private List<VerletSpring2D> springsA = new ArrayList<>();
    private List<VerletSpring2D> springsB = new ArrayList<>();
    private VerletParticle2D selected = null;

    private final float WIDTH = 800;
    private final float HEIGHT = 600;
    private final float GROUND_Y = HEIGHT - 50;
    private final float SELECTION_RADIUS = 15;

    @Override
    public void start(Stage stage) {
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        initPhysics();

        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(e -> selected = null);

        new AnimationTimer() {
            public void handle(long now) {
                applyGravity();
                physics.update();
                handleGroundCollision();
                checkPolygonCollision();
                draw(gc);
            }
        }.start();

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.setTitle("Softbody mit Polygon-Kollision");
        stage.show();
    }

    private void initPhysics() {
        physics = new VerletPhysics2D();
        physics.setDrag(0.05f);

        createPolygonSoftbody(200, 150, bodyA, springsA);
        createPolygonSoftbody(400, 300, bodyB, springsB);
    }

    private void createPolygonSoftbody(float x, float y, List<VerletParticle2D> body, List<VerletSpring2D> springs) {
        int sides = 6;
        float radius = 60;
        for (int i = 0; i < sides; i++) {
            float angle = (float)(2 * Math.PI * i / sides);
            float px = x + (float)Math.cos(angle) * radius;
            float py = y + (float)Math.sin(angle) * radius;
            VerletParticle2D p = new VerletParticle2D(px, py);
            physics.addParticle(p);
            body.add(p);
        }

        for (int i = 0; i < body.size(); i++) {
            VerletParticle2D a = body.get(i);
            VerletParticle2D b = body.get((i + 1) % body.size());
            VerletSpring2D s = new VerletSpring2D(a, b, a.distanceTo(b), 0.01f);
            physics.addSpring(s);
            springs.add(s);
        }
    }

    private void applyGravity() {
        Vec2D gravity = new Vec2D(0, 0.5f);
        for (VerletParticle2D p : bodyA) p.addForce(gravity);
        for (VerletParticle2D p : bodyB) p.addForce(gravity);
    }

    private void handleGroundCollision() {
        float damping = 0.5f;
        for (VerletParticle2D p : bodyA) {
            if (p.y > GROUND_Y) {
                p.y = GROUND_Y;
                float velY = p.y - p.getPreviousPosition().y;
                velY *= -damping;
                p.getPreviousPosition().y = p.y + velY;
            }
        }
        for (VerletParticle2D p : bodyB) {
            if (p.y > GROUND_Y) {
                p.y = GROUND_Y;
                float velY = p.y - p.getPreviousPosition().y;
                velY *= -damping;
                p.getPreviousPosition().y = p.y + velY;
            }
        }
    }

    private void checkPolygonCollision() {
        Polygon2D polyA = new Polygon2D();
        Polygon2D polyB = new Polygon2D();

        for (VerletParticle2D p : bodyA) polyA.add(p);
        for (VerletParticle2D p : bodyB) polyB.add(p);

        for (int i = 0; i < polyA.getNumPoints(); i++) {
            Vec2D a1 = polyA.vertices.get(i);
            Vec2D a2 = polyA.vertices.get((i + 1) % polyA.getNumPoints());
            Line2D edgeA = new Line2D(a1, a2);

            for (int j = 0; j < polyB.getNumPoints(); j++) {
                Vec2D b1 = polyB.vertices.get(j);
                Vec2D b2 = polyB.vertices.get((j + 1) % polyB.getNumPoints());
                Line2D edgeB = new Line2D(b1, b2);

                Vec2D intersection = edgeA.intersectLine(edgeB).getPos();
                if (intersection != null) {
                    // Schiebe beide Körper auseinander
                    Vec2D offset = edgeA.getDirection().getPerpendicular().normalizeTo(5);
                    for (VerletParticle2D p : bodyA) p.subSelf(offset);
                    for (VerletParticle2D p : bodyB) p.addSelf(offset);
                    return;
                }
            }
        }
    }

    private void handleMousePressed(MouseEvent e) {
        Vec2D mouse = new Vec2D((float)e.getX(), (float)e.getY());
        for (VerletParticle2D p : bodyA) {
            if (p.distanceTo(mouse) < SELECTION_RADIUS) {
                selected = p;
                return;
            }
        }
        for (VerletParticle2D p : bodyB) {
            if (p.distanceTo(mouse) < SELECTION_RADIUS) {
                selected = p;
                return;
            }
        }
    }

    private void handleMouseDragged(MouseEvent e) {
        if (selected != null) {
            float mx = (float)e.getX();
            float my = (float)e.getY();
            selected.set(mx, my);
            selected.getPreviousPosition().set(mx, my);
        }
    }

    private void draw(GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        gc.setStroke(Color.GRAY);
        gc.strokeLine(0, GROUND_Y, WIDTH, GROUND_Y);

        drawBody(gc, bodyA, springsA, Color.BLUE);
        drawBody(gc, bodyB, springsB, Color.GREEN);
    }

    private void drawBody(GraphicsContext gc, List<VerletParticle2D> body, List<VerletSpring2D> springs, Color color) {
        gc.setStroke(color);
        gc.setLineWidth(2);
        for (VerletSpring2D s : springs) {
            gc.strokeLine(s.a.x, s.a.y, s.b.x, s.b.y);
        }

        for (VerletParticle2D p : body) {
            gc.setFill(p == selected ? Color.ORANGE : Color.RED);
            gc.fillOval(p.x - 4, p.y - 4, 8, 8);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}