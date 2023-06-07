package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.repositories.CellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CellService {

    @Autowired
    CellRepository cellRepository;


}
