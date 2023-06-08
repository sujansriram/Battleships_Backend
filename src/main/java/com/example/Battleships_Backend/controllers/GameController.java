package com.example.Battleships_Backend.controllers;

import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.models.Reply;
import com.example.Battleships_Backend.services.CellService;
import com.example.Battleships_Backend.services.GameService;
import com.example.Battleships_Backend.services.GridService;
import com.example.Battleships_Backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping
    public ResponseEntity<Game> getGame(){
        Game game = gameService.getGame();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestParam boolean isSinglePlayer){
        Game game = gameService.createGame(isSinglePlayer);
        return new ResponseEntity<>(game, HttpStatus.CREATED);
    }

    @PatchMapping
    public ResponseEntity<Game> updateGridSetup(@RequestBody(required = false) Grid grid){
        Game game;
        if (grid != null){
           game = gameService.addSetupGrid(grid);
        } else {
            game = gameService.startGame();
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

    @PatchMapping(value = "/{id}")
    public ResponseEntity<Reply> handleTurn(@PathVariable("id") Long id, @RequestParam Long cellId){
        Reply reply = gameService.handleTurn(cellId);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Game> resetGame(){
        Game game = gameService.resetGame();
        return new ResponseEntity<>(game, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<Long> deleteGame(){
        gameService.deleteGame();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}

