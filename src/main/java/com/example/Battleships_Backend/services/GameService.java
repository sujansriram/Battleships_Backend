package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.models.Reply;
import com.example.Battleships_Backend.repositories.GameRepository;
import com.example.Battleships_Backend.repositories.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GameService {
    @Autowired
    GridRepository gridRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    GridService gridService;

    Game game;

    public Game resetGame() {
        game = getGame();
        List<Grid> grids = game.getGrids();
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

    public void addGridToGame(Grid grid, Game game){
        List<Grid> grids = game.getGrids();
        grids.add(grid);
        game.setGrids(grids);
        gameRepository.save(game);
    }

    public Game createGame() {
        Game newGame = new Game();
        gameRepository.save(newGame);
        Grid gridPlayerOne = new Grid("Player 1", newGame);
        Grid gridPlayerTwo = new Grid("Player 2", newGame);
        gridService.initialiseCells(gridPlayerOne);
        gridService.initialiseCells(gridPlayerTwo);
        addGridToGame(gridPlayerOne, newGame);
        addGridToGame(gridPlayerTwo, newGame);
//        Game newGame = new Game(gridPlayerOne, gridPlayerTwo);
        gameRepository.save(newGame);
        gridRepository.save(gridPlayerOne);
        gridRepository.save(gridPlayerTwo);
        return newGame;
    }

    public Game addSetupGrid(Grid grid) {
        game = getGame();
        int id = grid.getId() - 1;
        List<Grid> grids = game.getGrids();
        grids.set(id, grid);
        game.setGrids(grids);
        gameRepository.save(game);
        return game;
    }

    public Game startGame() {
        game = getGame();
        game.setStarted(true);
        gameRepository.save(game);
        return game;
    }

    public void deleteGame() {
        game = getGame();
        List<Grid> grids = game.getGrids();
        for(Grid grid : grids){
            gridService.deleteGrid(grid);
        }
        gameRepository.delete(game);
    }
}
