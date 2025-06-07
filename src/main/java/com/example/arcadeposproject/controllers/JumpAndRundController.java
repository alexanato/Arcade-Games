package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.FPS;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.SoftBody;
import com.example.arcadeposproject.models.JumpAndRundModel;
import com.example.arcadeposproject.models.Softbody;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import toxi.geom.Rect;
import toxi.geom.Vec2D;
import toxi.physics2d.VerletParticle2D;
import toxi.physics2d.VerletSpring2D;
import toxi.physics2d.behaviors.GravityBehavior2D;

import java.util.ArrayList;

public class JumpAndRundController extends BasicController{
    public static int canvasheight;
    public static int canvaswidth;
    private JumpAndRundModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;
    private AnimationTimer gameLoop;
    private ArrayList<KeyCode> input = new ArrayList<>();
    public FPS fps = new FPS();
    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed(this::handleMousePressed);
        canvas.setOnMouseDragged(this::handleMouseDragged);
        canvas.setOnMouseReleased(e -> {
            selected.unlock();
            selected = null;
        });
        gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                model.physics.update();

                model.update(fps.getDeltaTime());
                for(Softbody softbody : model.softbodies){
                    softbody.update();
                }

                model.keyHandle(input);
                fps.Frame(now);
                draw();
            }
        };
        handeInput();
        canvasheight = (int) canvas.getHeight();
        canvaswidth = (int) canvas.getWidth();
    }

    @Override
    public void start() {
        Softbody softbody = new Softbody("square");
        Softbody softbody2 = new Softbody("square2");
        softbody.points.add(new VerletParticle2D(200,0,3));
        softbody.points.add(new VerletParticle2D(300,0,3));
        softbody.points.add(new VerletParticle2D(200,100,3));
        softbody.points.add(new VerletParticle2D(300,100,3));

        softbody.springs.add(new VerletSpring2D( softbody.points.get(0),softbody.points.get(1),100,0.5f));
        softbody.springs.add(new VerletSpring2D( softbody.points.get(0),softbody.points.get(2),100,0.5f));
        softbody.springs.add(new VerletSpring2D( softbody.points.get(2),softbody.points.get(3),100,0.5f));
        softbody.springs.add(new VerletSpring2D( softbody.points.get(1),softbody.points.get(3),100,0.5f));

        softbody.boundary_Edge.add( softbody.springs.get(1));
        softbody.boundary_Edge.add( softbody.springs.get(0));
        softbody.boundary_Edge.add( softbody.springs.get(2));
        softbody.boundary_Edge.add( softbody.springs.get(3));

        softbody.springs.add(new VerletSpring2D( softbody.points.get(0),softbody.points.get(3),100 * 1.41f,1));

        softbody2.points.add(new VerletParticle2D(200,200,3));
        softbody2.points.add(new VerletParticle2D(300,200,3));
        softbody2.points.add(new VerletParticle2D(200,300,3));
        softbody2.points.add(new VerletParticle2D(300,300,3));

        softbody2.springs.add(new VerletSpring2D( softbody2.points.get(0),softbody2.points.get(1),100,1));
        softbody2.springs.add(new VerletSpring2D( softbody2.points.get(0),softbody2.points.get(2),100,1));
        softbody2.springs.add(new VerletSpring2D( softbody2.points.get(2),softbody2.points.get(3),100,1));
        softbody2.springs.add(new VerletSpring2D( softbody2.points.get(1),softbody2.points.get(3),100,1));

        softbody2.boundary_Edge.add( softbody2.springs.get(0));
        softbody2.boundary_Edge.add( softbody2.springs.get(1));
        softbody2.boundary_Edge.add( softbody2.springs.get(2));
        softbody2.boundary_Edge.add( softbody2.springs.get(3));

        softbody2.springs.add(new VerletSpring2D( softbody2.points.get(1),softbody2.points.get(2),100* 1.4f,1));
        for (VerletParticle2D particle2D : softbody.points){
            model.physics.addParticle(particle2D);
        }
        for (VerletSpring2D spring2D : softbody.springs){
            model.physics.addSpring(spring2D);
        }
        for (VerletParticle2D particle2D : softbody2.points){
            model.physics.addParticle(particle2D);
        }
        for (VerletSpring2D spring2D : softbody2.springs){
            model.physics.addSpring(spring2D);
        }
        model.softbodies.add(softbody);
        model.softbodies.add(softbody2);
        model.physics.addBehavior(new GravityBehavior2D(new Vec2D(0,1f)));
        model.physics.setWorldBounds(new Rect(0,0,(int)canvas.getWidth(),(int)canvas.getHeight()));
        gameLoop.start();
    }
    private VerletParticle2D selected;
    private void handleMousePressed(MouseEvent e) {
        Vec2D mouse = new Vec2D((float)e.getX(), (float)e.getY());
        for (VerletParticle2D p : model.softbodies.get(0).points) {
            if (p.distanceTo(mouse) < 20) {
                p.lock();
                selected = p;
                return;
            }
        }

    }
    private void handleMouseDragged(MouseEvent e) {
        if (selected != null) {
            selected.clearForce();
            float mx = (float)e.getX();
            float my = (float)e.getY();
            selected.set(mx, my);
            selected.getPreviousPosition().set(mx, my);
        }
    }
    @Override
    public void stop() {

    }

    private void draw() {
        context.setFill(Color.BLACK);
        context.fillRect(0,0,canvas.getWidth(),canvas.getHeight());
        context.setFill(Color.RED);
        context.setLineWidth(1);
        context.setStroke(Color.WHITE);
        for (Softbody softbody:model.softbodies){
            for(VerletSpring2D spring :softbody.springs){
                context.strokeLine(spring.a.x, spring.a.y,spring.b.x, spring.b.y);
            }
            for(VerletParticle2D point :softbody.points){
                context.fillOval(point.x-2.5, point.y-2.5, 5,5);
            }
        }
        context.setFill(Color.GREEN);
        context.setLineWidth(3);
        context.setStroke(Color.PINK);
        Vec2D[] dirs = model.softbodies.get(0).collision(model.softbodies);
        //context.strokeLine(dirs[1].x,dirs[1].y,dirs[1].x+dirs[0].x,dirs[1].y+dirs[0].y);
    }

    private void handeInput() {
        canvas.getScene().setOnKeyPressed((KeyEvent event) -> {
            boolean contain = false;
            for (KeyCode code : input) {
                if (event.getCode() == code) {
                    contain = true;
                    break;
                }
            }
            if (!contain) {
                input.add(event.getCode());
            }
        });
        canvas.getScene().setOnKeyReleased((KeyEvent event) -> {
            input.remove(event.getCode());
        });
    }

    public void setModel(JumpAndRundModel model) {
        this.model = model;
    }
}
