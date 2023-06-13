package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Ship;
import com.example.Battleships_Backend.repositories.CellRepository;
import com.example.Battleships_Backend.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CellService {

    @Autowired
    CellRepository cellRepository;

    @Autowired
    ShipService shipService;



    public Cell addShipToCell(Long cellId, Ship ship) {
        Cell cell = cellRepository.findById(cellId).get();
        cell.setShip(ship);
        cellRepository.save(cell);
        shipService.addCellToShip(cellId, ship);
        return cell;
    }
}
