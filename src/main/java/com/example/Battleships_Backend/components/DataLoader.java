package com.example.Battleships_Backend.components;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.models.Ship;
import com.example.Battleships_Backend.repositories.CellRepository;
import com.example.Battleships_Backend.repositories.GameRepository;
import com.example.Battleships_Backend.repositories.GridRepository;
import com.example.Battleships_Backend.repositories.ShipRepository;
import com.example.Battleships_Backend.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    GameService gameService;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    GridRepository gridRepository;

    @Autowired
    CellRepository cellRepository;

//    public DataLoader(){}

    @Override
    public void run(ApplicationArguments args) throws Exception{
//        Game game = new Game(true);
//        gameRepository.save(game);
//        Grid grid1 = new Grid("PlayerOne", game);
//        Grid grid2 = new Grid("PlayerTwo", game);
//        gridRepository.save(grid1);
//        gridRepository.save(grid2);
//        Cell cell1 = new Cell(0,0, grid1);
//        Cell cell2 = new Cell(0, 1, grid1);
//        Cell cell3 = new Cell(0, 0, grid2);
//        Cell cell4 = new Cell(0, 1, grid2);
//        gameService.addGridToGame(grid1, game);
//        gameService.addGridToGame(grid2, game);
//        game.setStarted(true);
//        Ship ship1 = new Ship("Cruiser" , 2);
//        Ship ship2 = new Ship("Cruiser" , 2);
//
//        List<Cell> cells1 = new ArrayList<>();
//        cells1.add(cell1);
//        cells1.add(cell2);
//        ship1.setCells(cells1);
//
//        List<Cell> cells2 = new ArrayList<>();
//        cells2.add(cell3);
//        cells2.add(cell4);
//        ship2.setCells(cells2);
//
//        cell1.setShip(ship1);
//        cell2.setShip(ship1);
//        cell3.setShip(ship2);
//        cell4.setShip(ship2);
//
//        gameRepository.save(game);
//        shipRepository.save(ship1);
//        shipRepository.save(ship2);
//        cellRepository.save(cell1);
//        cellRepository.save(cell2);
//        cellRepository.save(cell3);
//        cellRepository.save(cell4);

        Ship carrierPlayerOne = new Ship("Carrier", 5, true);
        shipRepository.save(carrierPlayerOne);

        Ship battleshipPlayerOne = new Ship("Battleship", 4, true);
        shipRepository.save(battleshipPlayerOne);

        Ship cruiserPlayerOne = new Ship("Cruiser", 3, true);
        shipRepository.save(cruiserPlayerOne);

        Ship submarinePlayerOne = new Ship("Submarine", 3, true);
        shipRepository.save(submarinePlayerOne);

        Ship destroyerPlayerOne = new Ship("Destroyer", 2, true);
        shipRepository.save(destroyerPlayerOne);


        Ship carrierPlayerTwo = new Ship("Carrier", 5, false);
        shipRepository.save(carrierPlayerTwo);

        Ship battleshipPlayerTwo = new Ship("Battleship", 4, false);
        shipRepository.save(battleshipPlayerTwo);

        Ship cruiserPlayerTwo = new Ship("Cruiser", 3, false);
        shipRepository.save(cruiserPlayerTwo);

        Ship submarinePlayerTwo = new Ship("Submarine", 3, false);
        shipRepository.save(submarinePlayerTwo);

        Ship destroyerPlayerTwo = new Ship("Destroyer", 2, false);
        shipRepository.save(destroyerPlayerTwo);
    }
}
