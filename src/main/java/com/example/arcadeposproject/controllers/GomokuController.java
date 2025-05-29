package com.example.arcadeposproject.controllers;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.models.GomokuModel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.input.MouseEvent;
public class GomokuController extends BasicController {
    @FXML
    private Canvas canvas;
    private GomokuModel model;
    private GraphicsContext context;
    private double cellsize;
    @FXML
    private void preview(MouseEvent event) {
        draw();
        int roundX = (int) (event.getX()/cellsize);
        int roundY = (int) (event.getY()/cellsize);
        roundX = (int) (roundX * cellsize);
        roundY = (int) (roundY * cellsize);
        context.setFill(Color.WHITE);
        context.fillOval(roundX, roundY, cellsize, cellsize);
    }
    @FXML
    private void click(MouseEvent event) {
        int roundX = (int) (event.getX()/cellsize);
        int roundY = (int) (event.getY()/cellsize);
        model.place(new int[] {roundX, roundY});
        draw();

    }

    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        context = canvas.getGraphicsContext2D();
        cellsize = canvas.getWidth()/15;
    }
    public void draw(){
        context.setFill(Color.rgb(76,45,16));
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());;
        context.setFill(Color.WHITE);
        for(double i = 0;i<=canvas.getWidth();i+=cellsize){
            context.fillRect(i+cellsize/2,0, 1, canvas.getHeight());
        }
        for(double j = 0; j<=canvas.getHeight();j+= cellsize){
            context.fillRect(0, j+cellsize/2, canvas.getWidth(), 1);
        }
    }

    public void start(){
        draw();
    }

    public void stop() {

    }

    public void setModel(GomokuModel gomokuModel) {
        this.model = gomokuModel;
    }
}

