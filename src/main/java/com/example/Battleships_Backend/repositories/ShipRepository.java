package com.example.Battleships_Backend.repositories;


import com.example.Battleships_Backend.models.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {

}
