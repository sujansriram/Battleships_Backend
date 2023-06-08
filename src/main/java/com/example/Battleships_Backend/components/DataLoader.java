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
import com.example.Battleships_Backend.services.GridService;
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
    GridService gridService;

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
//        game.setGridPlayerOne(new Grid("Player One", game));
//        game.setGridPlayerTwo(new Grid("Player Two", game));
//        gameRepository.save(game);
//        Cell cell1 = new Cell(0,0, game.getGridPlayerOne());
//        Cell cell2 = new Cell(0, 1, game.getGridPlayerOne());
//        Cell cell3 = new Cell(0, 0, game.getGridPlayerTwo());
//        Cell cell4 = new Cell(0, 1, game.getGridPlayerTwo());
//        gridService.addCellToGrid(cell1,game.getGridPlayerOne());
//        gridService.addCellToGrid(cell2,game.getGridPlayerOne());
//        gridService.addCellToGrid(cell3,game.getGridPlayerTwo());
//        gridService.addCellToGrid(cell4,game.getGridPlayerTwo());
//
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
    }
}
