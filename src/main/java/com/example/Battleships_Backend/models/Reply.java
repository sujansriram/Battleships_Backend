package com.example.Battleships_Backend.models;

public class Reply {


    private Game game;
    private Cell cell;


    public Reply(Game game, Cell cell){
        this.game = game;
        this.cell = cell;
    }

    public Reply(){
    }


    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
