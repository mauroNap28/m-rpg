package mauro.mrpg.controller;

import mauro.mrpg.dto.CharacterClassDto;
import mauro.mrpg.dto.GameCharacterDto;
import mauro.mrpg.dto.NewCharacterRequest;
import mauro.mrpg.service.GameCharacterService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game-character")
public class GameCharacterController {

    @Autowired
    GameCharacterService gameCharacterService;

    @PostMapping
    public ResponseEntity<?> newCharacter(@RequestBody NewCharacterRequest request) {
        try {
            return new ResponseEntity<>(gameCharacterService.newCharacter(request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCharacter(@PathVariable("id") Long id) {
        try {
            gameCharacterService.deleteCharacter(id);
            return new ResponseEntity<>("Character deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/character-level/{id}")
    public ResponseEntity<?> characterTotalLevel(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(gameCharacterService.getCharacterLevel(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/level-up/{id}")
    public ResponseEntity<?> levelUp(@PathVariable("id") Long id,
                                     @RequestBody CharacterClassDto request) {
        try {
            return new ResponseEntity<>(gameCharacterService.levelUp(id, request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCharacter(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(gameCharacterService.getCharacter(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
