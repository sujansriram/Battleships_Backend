package com.example.Battleships_Backend.repositories;

import com.example.Battleships_Backend.models.Game;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameRepository extends JpaRepository<Game, Long> {

}
