package tests;


import com.example.Battleships_Backend.models.Cell;
import com.example.Battleships_Backend.models.Game;
import com.example.Battleships_Backend.models.Grid;
import com.example.Battleships_Backend.models.Ship;
import com.example.Battleships_Backend.services.GameService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class GameServiceTest {

    @Autowired
    GameService gameService;


    Ship ship;
    Game game;
    Grid grid;
    Cell cell1;
    Cell cell2;

    @BeforeEach
    public void  setUp(){
        game = new Game();
        grid = new Grid("Grid", game);
        gameService.addGridToGame(grid, game);
        cell1 = new Cell(0,0, grid);
        cell2 = new Cell(0, 1, grid);
        ship = new Ship("Cruiser" , 2);
        List<Cell> cells = new ArrayList<>();
        cells.add(cell1);
        cells.add(cell2);
        ship.setCells(cells);

        cell1.setShip(ship);
        cell2.setShip(ship);
    }

    @Test
    public void canHandleHit(){
        gameService.handleTurn(cell1.getId());
        assertThat(cell1.isHasBeenHit()).isEqualTo(true);
    }

    @Test
    public void canHandleShipHit(){
        gameService.handleTurn(cell1.getId());
        gameService.handleTurn(cell2.getId());
        assertThat(ship.isHasSunk()).isEqualTo(true);
        assertThat(game.isFinished()).isEqualTo(true);
    }

    @Test
    public void canHandleShipHit(){
        gameService.handleTurn(cell1);
        assertThat(ship.getNumberOfTimesHit()).isEqualTo(1);
    }
}
