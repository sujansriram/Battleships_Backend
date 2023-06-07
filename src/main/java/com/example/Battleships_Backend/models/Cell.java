package com.example.Battleships_Backend.models;

public class Cell {

    private Long id;
    private int xCoordinate;
    private int yCoordinate;
    private boolean hasBeenHit;
    private Ship ship;
    private Grid grid;


//    Constructor
    public Cell(int xCoordinate, int yCoordinate, Grid grid){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.hasBeenHit = false;
        this.ship = null;
        this.grid = grid;
    }

    public Cell(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public boolean isHasBeenHit() {
        return hasBeenHit;
    }

    public void setHasBeenHit(boolean hasBeenHit) {
        this.hasBeenHit = hasBeenHit;
    }

    public Ship getShip() {
        return ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }
}
