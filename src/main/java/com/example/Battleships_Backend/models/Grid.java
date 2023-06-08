package com.example.Battleships_Backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "grids")
public class Grid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "player_name")
    private String playerName;

    @OneToMany(mappedBy = "grid")
    @JsonIgnoreProperties({"grids"})
    private List<Cell> cells;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnoreProperties({"grids"})
    private Game game;

//    Constructor
    public Grid(String playerName, Game game){
        this.playerName = playerName;
        this.cells = new ArrayList<>();
        this.game = game;
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

    public List<Cell> getCells() {
        return cells;
    }

    public void setCells(List<Cell> cells) {
        this.cells = cells;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
