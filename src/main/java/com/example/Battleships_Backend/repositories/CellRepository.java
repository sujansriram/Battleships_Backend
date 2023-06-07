package com.example.Battleships_Backend.repositories;

import com.example.Battleships_Backend.models.Cell;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellRepository extends JpaRepository<Cell, Long> {

}
