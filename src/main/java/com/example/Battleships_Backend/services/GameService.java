package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.*;
import com.example.Battleships_Backend.repositories.CellRepository;
import com.example.Battleships_Backend.repositories.GameRepository;
import com.example.Battleships_Backend.repositories.GridRepository;
import com.example.Battleships_Backend.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class GameService {
    @Autowired
    GridRepository gridRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    CellRepository cellRepository;

    @Autowired
    GridService gridService;

    Game game;

    public Game resetGame() {
        game = getGame();
        List<Grid> grids = game.getGrids();
        gridService.resetGrid(grids.get(0));
        gridService.resetGrid(grids.get(1));
        game.setGrids(grids);
        game.setStarted(false);
        game.setFinished(false);
        game.setPlayerOneTurn(true);
        gameRepository.save(game);
        return game;
    }

    public void incrementTimesHit(Ship ship){
        ship.setNumberOfTimesHit(ship.getNumberOfTimesHit() + 1);
    }

    public void toggleTurn(Game game){
        game.setPlayerOneTurn(!game.isPlayerOneTurn());
    }
    public Reply handleTurn(Long cellId) {
        game = getGame();
        Cell cell = cellRepository.findById(cellId).get();
        Ship ship = cell.getShip();
        if (ship != null){
            incrementTimesHit(ship);
            if(ship.getNumberOfTimesHit() == ship.getSize()){
                ship.setHasSunk(true);
            }
            shipRepository.save(ship);
        }
        cell.setHasBeenHit(true);
        cellRepository.save(cell);
        if(checkGameFinished(game)){
            game.setFinished(true);
        }
        else {
            toggleTurn(game);
        }
        gameRepository.save(game);
        return new Reply(game, cell);
    }


    public boolean checkGameFinished(Game game){
        Grid gridPlayerOne;
        Grid gridPlayerTwo;
        Grid gridOne = game.getGrids().get(0);
        Grid gridTwo = game.getGrids().get(1);
        if(gridOne.getId() % 2 == 0){
            gridPlayerTwo = gridOne;
            gridPlayerOne = gridTwo;
        } else {
            gridPlayerTwo = gridTwo;
            gridPlayerOne = gridOne;
        }

        if(game.isPlayerOneTurn()){
            for (Cell cell: gridPlayerTwo.getCells()){
                if(cell.getShip() != null && !cell.isHasBeenHit()){
                    return false;
                }
            }
        } else {
            for (Cell cell: gridPlayerOne.getCells()){
                if(cell.getShip() != null && !cell.isHasBeenHit()){
                    return false;
                }
            }
        } return true;
    }

    public Game getGame() {
        return gameRepository.findAll().get(0);
    }

    public List<Game> getGames(){
        return gameRepository.findAll();
    }


    public void addGridToGame(Grid grid, Game game){
        List<Grid> grids = game.getGrids();
        grids.add(grid);
        game.setGrids(grids);
        gameRepository.save(game);
    }

    public Game createGame(boolean isSinglePlayer) {
        List<Game> games = getGames();
        if (games.size() == 0){
            Game newGame = new Game(isSinglePlayer);
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
            game = newGame;
        } else{
            game = games.get(0);
            List<Grid> grids = game.getGrids();
            gridService.initialiseCells(grids.get(0));
            gridService.initialiseCells(grids.get(1));
            game.setSinglePlayer(isSinglePlayer);
            gameRepository.save(game);
        }
        return game;
    }

    public Game addSetupGrid(Grid grid) {

//        need to go through grid, get each cell, find cells with
        List<Cell> cells = grid.getCells();
        for(Cell cell : cells){
            cell.setGrid(grid);
            cellRepository.save(cell);
        }

        game = getGame();
//        int id = grid.getId() - 1;
//        List<Grid> grids = game.getGrids();
//        grids.set(id, grid);
        grid.setGame(game);
//        game.setGrids(grids);
        gridRepository.save(grid);
//        gameRepository.save(game);
        System.out.println(game);
        return game;
    }

    public Game startGame() {
        game = getGame();
        game.setStarted(true);
        gameRepository.save(game);
        return game;
    }

}
