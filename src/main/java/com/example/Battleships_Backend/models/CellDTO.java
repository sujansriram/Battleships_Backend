package com.example.Battleships_Backend.models;

public class CellDTO {
    private int xCoordinate;
    private int yCoordinate;
    private boolean hasBeenHit;
    private Long shipId;
    private int gridId;

    public CellDTO(int xCoordinate, int yCoordinate, boolean hasBeenHit, Long shipId, int gridId){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.hasBeenHit = hasBeenHit;
        this.shipId = shipId;
        this.gridId = gridId;
    }

    public CellDTO(){

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

    public Long getShipId() {
        return shipId;
    }

    public void setShipId(Long shipId) {
        this.shipId = shipId;
    }

    public int getGridId() {
        return gridId;
    }

    public void setGridId(int gridId) {
        this.gridId = gridId;
    }
}
