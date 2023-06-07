package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.models.Reply;
import com.example.Battleships_Backend.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    public Game resetGame() {
    }

    public Reply handleTurn(Cell cell) {
    }

    public Game getGame() {
    }

    public Game createGame() {
    }

    public Game addSetupGrid(Grid grid) {
    }

    public Game startGame() {
    }
}
