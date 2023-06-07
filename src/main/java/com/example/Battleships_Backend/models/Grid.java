package com.example.Battleships_Backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;

@Entity(name = "grids")
public class Grid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "player_name")
    private String playerName;

    @OneToMany(mappedBy = "grid")
    @JsonIgnoreProperties({"grids"})
    private ArrayList<Cell> cells;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnoreProperties({"grids"})
    private Game game;

//    Constructor
    public Grid(String playerName){
        this.playerName = playerName;
        this.cells = new ArrayList<>();
    }

    public Grid(){
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Cell> getCells() {
        return cells;
    }

    public void setCells(ArrayList<Cell> cells) {
        this.cells = cells;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
