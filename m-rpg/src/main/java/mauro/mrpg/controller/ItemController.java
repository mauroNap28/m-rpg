package mauro.mrpg.controller;

import mauro.mrpg.dto.ItemDto;
import mauro.mrpg.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/item")
public class ItemController {

    @Autowired
    ItemService itemService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllItemsByCharacterId(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(itemService.getItemsByCharacterId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<?> postItem(@RequestBody ItemDto request) {
        try {
            return new ResponseEntity<>(itemService.postItem(request), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable("id") Long id) {
        try {
            itemService.deleteItem(id);
            return new ResponseEntity<>("item deleted.", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putItem(@PathVariable("id") Long id,
                                     @RequestBody ItemDto request) {
        try {
            return new ResponseEntity<>(itemService.putItem(id, request), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
