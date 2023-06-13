package com.example.Battleships_Backend.controllers;

import com.example.Battleships_Backend.components.UserCounter;
import com.example.Battleships_Backend.models.Reply;
import com.example.Battleships_Backend.services.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.messaging.SessionConnectedEvent;

@Controller
public class WebSocketController {

    @Autowired
    UserCounter userCounter;

    @Autowired
    GameService gameService;

    @MessageMapping("/handleTurn")
    @SendTo("/topic/game")
    public ResponseEntity<Reply> handleMultiplayerTurn(@Payload Long cellId){
        Reply reply = gameService.handleTurn(cellId);
        return new ResponseEntity<>(reply, HttpStatus.OK);
    }

    @MessageMapping("/register-player")
    @SendTo("/topic/game")
    public ResponseEntity<Integer> addUser(){
        Integer numberOfUsers = userCounter.getUserCount();
        System.out.println(numberOfUsers);
        return new ResponseEntity<>(numberOfUsers, HttpStatus.OK);
    }

    @EventListener(SessionConnectedEvent.class)
    public void checkConnection(SessionConnectedEvent event){
//        event listener will currently do nothing but tell me if a connection is made
        userCounter.addUser();
        System.out.println("Connection received:" + event.getSource());
    }
}
