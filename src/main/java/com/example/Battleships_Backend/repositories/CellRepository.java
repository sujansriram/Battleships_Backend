package com.example.Battleships_Backend.repositories;

import com.example.Battleships_Backend.models.Cell;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CellRepository extends JpaRepository<Cell, Long> {

}
