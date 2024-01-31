package mauro.mrpg.controller;

import mauro.mrpg.dto.NewGameRequest;
import mauro.mrpg.model.Game;
import mauro.mrpg.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    GameService gameService;

    @PostMapping
    public ResponseEntity<?> newGame(@RequestBody NewGameRequest request) {
        try {
            return new ResponseEntity<>(gameService.newGame(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/games/{id}")
    public ResponseEntity<?> getAllGamesByUserId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(gameService.getAllGamesByUserId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGameById(@PathVariable("id") Long id) {
        try {
            gameService.deleteById(id);
            return new ResponseEntity<>(("Game with id: " + id + " deleted."), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
