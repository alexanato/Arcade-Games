package com.example.arcadeposproject.controllers;

import com.example.arcadeposproject.GameManager;

public abstract class BasicController {
    protected GameManager gameManager;
    protected String message;
    public void init(GameManager gameManager){
        this.gameManager = gameManager;
    }
    public void init(GameManager gameManager,String message){
        this.gameManager = gameManager;
        this.message = message;
    }
}
