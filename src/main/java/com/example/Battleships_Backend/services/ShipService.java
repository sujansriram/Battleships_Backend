package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShipService {

    @Autowired
    ShipRepository shipRepository;



}
