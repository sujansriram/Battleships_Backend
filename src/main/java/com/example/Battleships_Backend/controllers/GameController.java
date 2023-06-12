package com.example.Battleships_Backend.controllers;

import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.models.Reply;
import com.example.Battleships_Backend.repositories.GameRepository;
import com.example.Battleships_Backend.services.CellService;
import com.example.Battleships_Backend.services.GameService;
import com.example.Battleships_Backend.services.GridService;
import com.example.Battleships_Backend.services.ShipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

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
    @Autowired
    GameRepository gameRepository;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


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
    public ResponseEntity<Game> updateGridSetup(@RequestBody(required = false) Grid grid, @RequestParam(required = false) boolean isSinglePlayer){
        Game game;
        if (grid != null){
           game = gameService.addSetupGrid(grid);
        } else {
            game = gameService.startGame();
        }
        return new ResponseEntity<>(game, HttpStatus.OK);

    }

//    double check patch mapping on main branch
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

    @MessageMapping("/handleTurn")
    @SendTo("/topic/game")
    public ResponseEntity<Reply> handleMultiplayerTurn(@Payload Long cellId){
        Reply reply = gameService.handleTurn(cellId);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @EventListener(SessionConnectedEvent.class)
    public void checkConnection(SessionConnectedEvent event){
//        event listener will currently do nothing but tell me if a connection is made
        System.out.println("Connection received:" + event.getSource());
    }

}

