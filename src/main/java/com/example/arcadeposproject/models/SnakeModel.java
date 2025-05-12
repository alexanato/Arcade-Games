package com.example.arcadeposproject.models;

import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.enums.Direction;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class SnakeModel {
    private Direction currentDir = Direction.NONE;
    private Direction nextDir = Direction.NONE;
    private ArrayList<SnakePiece> snake = new ArrayList<>();
    private int[] apple;
    public final int size = 10;

    public void keyPressed(KeyEvent key) {
        switch (key.getCode()) {
            case W:
            case UP:
                if (currentDir != Direction.DOWN) nextDir = Direction.UP;
                break;
            case S:
            case DOWN:
                if (currentDir != Direction.UP) nextDir = Direction.DOWN;
                break;
            case D:
            case RIGHT:
                if (currentDir != Direction.LEFT) nextDir = Direction.RIGHT;
                break;
            case A:
            case LEFT:
                if (currentDir != Direction.RIGHT) nextDir = Direction.LEFT;
                break;
        }
    }

    public void update() {
        currentDir = nextDir;
        boolean appleCollision = checkAppleCollision();
        move(appleCollision);
        if (appleCollision) generateApple();
    }

    public void start() {
        snake.add(new SnakePiece(5, 5, Direction.NONE));
        apple = new int[2];
        generateApple();
    }

    private void move(boolean appleCollision) {
        addNextPiece();
        if (!appleCollision) removeLastPiece();
        updateDir();
        snake.get(snake.size() - 1).setDirection(Direction.NONE);
    }

    private void addNextPiece() {
        int[] direction = currentDir.getDir();
        int[] firstPiecePos = snake.get(snake.size() - 1).getPos();
        int[] pos = new int[]{firstPiecePos[0] + direction[0], firstPiecePos[1] + direction[1]};
        snake.add(new SnakePiece(pos,Direction.NONE));
    }

    private void updateDir() {
        for (int i = 0; i < snake.size() - 1; i++) {
            for (Direction dir : Direction.values()) {
                int[] direction = new int[]{dir.getDir()[0],dir.getDir()[1]} ;
                if (snake.get(i).x + direction[0] == snake.get(i + 1).x && snake.get(i).y + direction[1] == snake.get(i + 1).y) {
                    snake.get(i).setDirection(dir);
                }
            }
        }

    }

    private void removeLastPiece() {
        snake.remove(0);
    }

    private boolean checkAppleCollision() {
        int[] direction = currentDir.getDir();
        int[] firstPiecePos = snake.get(snake.size() - 1).getPos();
        int[] pos = new int[]{firstPiecePos[0] + direction[0], firstPiecePos[1] + direction[1]};
        return Arrays.equals(apple, pos);
    }

    public boolean checkCollision() {
        SnakePiece firstPiece = snake.get(snake.size() - 1);
        for (int i = 0; i < snake.size() - 1; i++) if (snake.get(i).equals(firstPiece)) return true;
        return firstPiece.x < 0 || firstPiece.x >= size || firstPiece.y < 0 || firstPiece.y >= size;
    }

    private void generateApple() {
        int x;
        int y;
        do {
            x = GameManager.random.nextInt(0, size);
            y = GameManager.random.nextInt(0, size);
        } while (snake.contains(new SnakePiece(x, y, Direction.NONE)));
        apple[0] = x;
        apple[1] = y;
    }

    public ArrayList<SnakePiece> getSnake() {
        return snake;
    }

    public int[] getApple() {
        return apple;
    }
}