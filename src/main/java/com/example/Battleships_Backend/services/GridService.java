package com.example.Battleships_Backend.services;

import com.example.Battleships_Backend.repositories.GridRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GridService {

    @Autowired
    GridRepository gridRepository;
}
