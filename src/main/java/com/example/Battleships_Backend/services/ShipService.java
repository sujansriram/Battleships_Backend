package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Ship;
import com.example.Battleships_Backend.repositories.CellRepository;
import com.example.Battleships_Backend.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    @Autowired
    CellRepository cellRepository;

    public List<Ship> getShips() {
        return shipRepository.findAll();
    }

    public void addCellToShip(Long cellId, Ship ship) {
        List<Cell> cells = ship.getCells();
        Cell cell = cellRepository.findById(cellId).get();
        cells.add(cell);
        ship.setCells(cells);
    }
}
