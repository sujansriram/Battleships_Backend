package com.example.Battleships_Backend.repositories;

import com.example.Battleships_Backend.models.Grid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridRepository extends JpaRepository<Grid, Integer> {

}
