package com.example.Battleships_Backend.models;

import java.util.ArrayList;

public class Game {


    private Long id;
    private boolean isStarted;
    private boolean isFinished;
    private boolean playerOneTurn;
    private ArrayList<Grid> grids;

    public Game(Grid gridPlayerOne, Grid gridPlayerTwo){
        this.isStarted = false;
        this.isFinished = false;
        this.playerOneTurn = true;
        this.grids = new ArrayList<>();
        this.grids.add(gridPlayerOne);
        this.grids.add(gridPlayerTwo);
    }

    public Game(){
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public void setPlayerOneTurn(boolean playerOneTurn) {
        this.playerOneTurn = playerOneTurn;
    }

    public ArrayList<Grid> getGrids() {
        return grids;
    }

    public void setGrids(ArrayList<Grid> grids) {
        this.grids = grids;
    }
}
