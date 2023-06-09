package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.*;
import com.example.Battleships_Backend.repositories.CellRepository;
import com.example.Battleships_Backend.repositories.GameRepository;
import com.example.Battleships_Backend.repositories.GridRepository;
import com.example.Battleships_Backend.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
        gridService.resetCells(grids);
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
            if (!game.isPlayerOneTurn() && game.isSinglePlayer()) {
                Grid grid = gridRepository.findAll().get(0);
                handleComputerTurn(grid);
            }
        }
        gameRepository.save(game);
        return new Reply(game, cell);
    }

    public List<Long> availableCells(Grid grid){
        List<Long> availableCells = new ArrayList<>();
        List<Cell> gridCells = grid.getCells();
        for(Cell cell : gridCells){
            if((cell.getxCoordinate() + cell.getyCoordinate()) % 2 == 0){
                availableCells.add(cell.getId());
            }
        }
        return availableCells;
    }

    private void handleComputerTurn(Grid grid) {
        List<Long> availableCells = availableCells(grid);
        Random random = new Random();
        int index = random.nextInt(availableCells.size());
        Long randomCellId = availableCells.get(index);
        handleTurn(randomCellId);
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

    public void addGridToGame(Grid grid, Game game){
        List<Grid> grids = game.getGrids();
        grids.add(grid);
        game.setGrids(grids);
        gameRepository.save(game);
    }

    public Game createGame(boolean isSinglePlayer) {
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

    public void connect(Game game) {
        game.setSinglePlayer(false);
        gameRepository.save(game);
    }
}
