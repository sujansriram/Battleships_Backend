package com.example.Battleships_Backend.controllers;

import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Ship;
import com.example.Battleships_Backend.services.GridService;
import com.example.Battleships_Backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value ="/ships")
public class ShipController {

    @Autowired
    ShipService shipService;

    @GetMapping
    public ResponseEntity<List<Ship>> getShips(){
        List<Ship> ships = shipService.getShips();
        return new ResponseEntity<>(ships, HttpStatus.OK);
    }
}
