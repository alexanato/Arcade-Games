package com.example.arcadeposproject.models;

import com.example.arcadeposproject.FPS;
import com.example.arcadeposproject.controllers.PongController;
import javafx.geometry.Bounds;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class PongModel {
    public double[] ball = new double[2];
    public double[] ballDirection = new double[2];
    private double[][] players = new double[2][2];
    private int speed = 600;
    public int ballSpeed = 300;

    public int[] points = new int[2];
    private double deltaTime;
    public void update(double deltaTime){
        this.deltaTime = deltaTime;
        System.out.println(Arrays.toString(ballDirection) );
        int ret = checkPointsCollision();
        if(ret != 2){
            points[ret]++;
            respawnBall();
        };
        if(checkEdgeCollision()){
            ballDirection[1] = ballDirection[1]* -1;
        }
        ball[0] += ballDirection[0] * deltaTime;
        ball[1] += ballDirection[1]* deltaTime;
        if(checkPlayerCollision()){
            ballDirection[0] = ballDirection[0] * -1;
        }
    }
    public void keyHandlePlayer1(ArrayList<KeyCode> input){
        for (int i = 0 ; i<input.size();i++){
            switch (input.get(i)){
                case UP->{
                    movePlayer(1,-speed);
                }
                case DOWN -> {
                    movePlayer(1,speed);
                }
            }
        }

    }
    public void keyHandlePlayer2(ArrayList<KeyCode> input){
        for (int i = 0 ; i<input.size();i++){
            switch (input.get(i)){
                case W -> {
                    movePlayer( 0,-speed);
                }
                case S ->{
                    movePlayer(0,speed);
                }
            }
        }
    }

    private void movePlayer(int player,int y){
        System.out.println(players[player][1]);
        if(players[player][1] < 0&&y>0){
            players[player][1] +=y*deltaTime;
        } else if (players[player][1] > 400 - PongController.playerheight && y<0) {
            players[player][1] +=y*deltaTime;
        }else if(players[player][1] >0&&players[player][1] < 400 - PongController.playerheight ){
            players[player][1] +=y*deltaTime;
        }
    }
    private boolean checkEdgeCollision(){

        return ball[1]<0||ball[1]>PongController.canvasheight-PongController.ballSize;
    }
    public void respawnBall(){
        Random random = new Random();
        ball[0] = (double) PongController.canvaswidth / 2;
        ball[1] = (double) PongController.canvasheight / 2;
        ballDirection[0] = (random.nextInt(0,2)==0?1:-1) * ballSpeed;
        ballDirection[1] = (random.nextInt(0,2)==0?1:-1) * ballSpeed;
    }
    //0 = keine
    //1 = links
    //2 = rechts
    private int checkPointsCollision(){
        if(ball[0] <=0) return 0;
        if(ball[0] >=PongController.canvaswidth) return 1;
        return 2;
    }
    private boolean checkPlayerCollision(){
        Rectangle r1 = new Rectangle(players[0][0],players[0][1],12,100);
        Rectangle r3 = new Rectangle(players[1][0],players[1][1],12,100);
        Rectangle r2 = new Rectangle(ball[0],ball[1],10,10);
        return r1.getBoundsInLocal(). intersects(r2.getBoundsInLocal()) ||r3.getBoundsInLocal(). intersects(r2.getBoundsInLocal());
    }
    public double[][] getPlayers() {
        return players;
    }

    public void setPlayers(double[][] players) {
        this.players = players;
    }
}
