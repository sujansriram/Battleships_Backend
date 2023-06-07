package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.repositories.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Array;
import java.util.ArrayList;

@Service
public class GridService {

    @Autowired
    GridRepository gridRepository;

    public void addCellToGrid(Cell cell, Grid grid){
        ArrayList<Cell> cells = grid.getCells();
        cells.add(cell);
        grid.setCells(cells);
    }

    public void initialiseCells(Grid grid) {
        for (int i=0; i<8; i++){
            for (int j=0; j<8; j++){
                Cell cell = new Cell(i, j, grid);
                addCellToGrid(cell, grid);
            }
        }
    }

    public void resetCells(ArrayList<Grid> grids) {
        for (Grid grid : grids){
            grid.setCells(new ArrayList<>());
            initialiseCells(grid);
        }
    }
}
