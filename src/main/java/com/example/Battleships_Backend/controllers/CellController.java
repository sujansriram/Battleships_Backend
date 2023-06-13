package com.example.Battleships_Backend.controllers;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Reply;
import com.example.Battleships_Backend.models.Ship;
import com.example.Battleships_Backend.services.CellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value ="/cells")
public class CellController {

    @Autowired
    CellService cellService;


    @PatchMapping(value = "/{cellId}")
    public ResponseEntity<Cell> addShipToCell (@PathVariable("cellId") Long cellId, @RequestBody Ship ship){
        Cell cell = cellService.addShipToCell(cellId, ship);
        return new ResponseEntity<>(cell, HttpStatus.OK);
    }
}
