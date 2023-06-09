package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.models.Ship;
import com.example.Battleships_Backend.repositories.ShipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShipService {

    @Autowired
    ShipRepository shipRepository;

    public List<Ship> getShips() {
        return shipRepository.findAll();
    }
}
