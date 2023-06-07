package com.example.Battleships_Backend.models;

public class Grid {

    private Long id;
    private String playerName;
    private Cell[][] cells;
    private Game game;

//    Constructor
    public Grid(String playerName){
        this.playerName = playerName;
        this.cells = new Cell[8][8];
    }

    public Grid(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
