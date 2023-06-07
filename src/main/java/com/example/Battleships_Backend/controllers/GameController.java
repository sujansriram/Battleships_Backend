package com.example.Battleships_Backend.controllers;

import com.example.Battleships_Backend.services.CellService;
import com.example.Battleships_Backend.services.GameService;
import com.example.Battleships_Backend.services.GridService;
import com.example.Battleships_Backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value ="/games")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    GridService gridService;

    @Autowired
    ShipService shipService;

    @Autowired
    CellService cellService;



}
