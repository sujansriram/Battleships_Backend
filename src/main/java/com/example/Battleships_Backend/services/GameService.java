package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.models.Reply;
import com.example.Battleships_Backend.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GridService gridService;

    Game game = gameRepository.findAll().get(0);

    public Game resetGame() {
        ArrayList<Grid> grids = game.getGrids();
        gridService.resetCells(grids);
        game.setGrids(grids);
        game.setStarted(false);
        game.setFinished(false);
        game.setPlayerOneTurn(true);
        gameRepository.save(game);
        return game;
    }

//    public Reply handleTurn(Cell cell) {
//    }

    public Game getGame() {
        return gameRepository.findAll().get(0);
    }

    public Game createGame() {
        Grid gridPlayerOne = new Grid("Player 1");
        Grid gridPlayerTwo = new Grid("Player 2");
        gridService.initialiseCells(gridPlayerOne);
        gridService.initialiseCells(gridPlayerTwo);
        Game newGame = new Game(gridPlayerOne, gridPlayerTwo);
        gameRepository.save(newGame);
        return newGame;
    }

    public Game addSetupGrid(Grid grid) {
        int id = grid.getId() - 1;
        ArrayList<Grid> grids = game.getGrids();
        grids.set(id, grid);
        game.setGrids(grids);
        gameRepository.save(game);
        return game;
    }

    public Game startGame() {
        game.setStarted(true);
        gameRepository.save(game);
        return game;
    }
}
