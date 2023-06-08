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
        gridService.resetCells(game.getGridPlayerOne());
        gridService.resetCells(game.getGridPlayerTwo());
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
        }else {
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
        Grid gridOne = game.getGridPlayerOne();
        Grid gridTwo = game.getGridPlayerTwo();
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

    public Game createGame(boolean isSinglePlayer) {
        Game newGame = new Game(isSinglePlayer);
        System.out.println(newGame);
        gameRepository.save(newGame);
        game.setGridPlayerOne(new Grid("Player One", newGame));
        game.setGridPlayerTwo(new Grid("Player Two", newGame));
        System.out.println(game.getGridPlayerOne());
        gameRepository.save(newGame);
        gridService.initialiseCells(newGame.getGridPlayerOne());
        gridService.initialiseCells(newGame.getGridPlayerTwo());
//        Game newGame = new Game(gridPlayerOne, gridPlayerTwo);
        gameRepository.save(newGame);
        gridRepository.save(newGame.getGridPlayerOne());
        gridRepository.save(newGame.getGridPlayerTwo());
        return newGame;
    }

    public Game addSetupGridPlayerOne(Grid grid) {
        game = getGame();
        game.setGridPlayerOne(grid);
        gameRepository.save(game);
        return game;
    }

    public Game addSetupGridPlayerTwo(Grid grid) {
        game = getGame();
        game.setGridPlayerTwo(grid);
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
        Grid gridPlayerOne = game.getGridPlayerOne();
        Grid gridPlayerTwo = game.getGridPlayerTwo();
        gridService.deleteGrid(gridPlayerOne);
        gridService.deleteGrid(gridPlayerTwo);
        gameRepository.delete(game);
    }
}
