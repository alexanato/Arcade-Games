package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.Assets;
import com.example.arcadeposproject.GameManager;
import com.example.arcadeposproject.enums.State;
import com.example.arcadeposproject.models.TicTacToeModel;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class TicTacToeController extends BasicController {
    public TicTacToeModel model;
    @FXML
    private Canvas canvas;
    private GraphicsContext context;

    @Override
    public void init(GameManager gameManager) {
        super.init(gameManager);
        model = new TicTacToeModel();
        context = canvas.getGraphicsContext2D();
        drawBoard();
    }

    @FXML
    private void click(MouseEvent event) {
        byte x = (byte) (event.getX() / 133.33);
        byte y = (byte) (event.getY() / 133.33);
        model.onTurn(new byte[]{x, y});
        if(model.isWinning()){
            gameManager.sendResult(((model.getPlayer())? "O has won":"X has won"));
        } else if (model.stalemate()) {
            gameManager.sendResult("It’s a tie");
        }
        model.changeTurn();
        drawBoard();
    }

    private void drawBoard() {
        context.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        context.setFill(Color.BLACK);
        context.fillRect(133.33, 0, 10, canvas.getHeight());
        context.fillRect(266.66, 0, 10, canvas.getHeight());

        context.fillRect(0, 133.33, canvas.getWidth(), 10);
        context.fillRect(0, 266.66, canvas.getWidth(), 10);
        for (int i = 0; i < model.getBoard().length; i++) {
            for (int j = 0; j < model.getBoard()[i].length; j++) {
                switch (model.getBoard()[i][j]) {
                    case PLAYER1_X -> {
                        context.drawImage(Assets.X,133.33*i+5,133.33*j+5);
                    }
                    case PLAYER2_O -> {
                        context.drawImage(Assets.O,133.33*i+5,133.33*j+5);
                    }
                }
            }
        }
    }
}