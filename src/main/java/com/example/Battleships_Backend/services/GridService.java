package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.repositories.CellRepository;
import com.example.Battleships_Backend.repositories.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class GridService {

    @Autowired
    GridRepository gridRepository;
    @Autowired
    CellRepository cellRepository;

    public void addCellToGrid(Cell cell, Grid grid){
        List<Cell> cells = grid.getCells();
        cells.add(cell);
        grid.setCells(cells);
        gridRepository.save(grid);
        cellRepository.save(cell);
    }

    public void initialiseCells(Grid grid) {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                Cell cell = new Cell(i, j, grid);
                addCellToGrid(cell, grid);
            }
        }
    }

    public void resetCells(List<Grid> grids) {
        for (Grid grid : grids){
            grid.setCells(new ArrayList<>());
            initialiseCells(grid);
            gridRepository.save(grid);
        }
    }

    public void deleteGrid(Grid grid) {
        List<Cell> cells = grid.getCells();
        for(Cell cell : cells){
            cellRepository.delete(cell);
        }
        gridRepository.delete(grid);
    }
}
