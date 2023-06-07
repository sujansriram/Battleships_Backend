package com.example.Battleships_Backend.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity(name = "grids")
public class Grid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "player_name")
    private String playerName;

    @OneToMany(mappedBy = "cell")
    @JsonIgnoreProperties({"grids"})
    private Cell[][] cells;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @JsonIgnoreProperties({"grids"})
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
