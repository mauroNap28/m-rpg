package mauro.mrpg.controller;

import mauro.mrpg.dto.UserProfileRequest;
import mauro.mrpg.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-profile")
public class UserProfileController {

    @Autowired
    UserProfileService userProfileService;

    @PutMapping("/{id}")
    public ResponseEntity<?> createUserProfile(@RequestBody UserProfileRequest request,
                                                @PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userProfileService.createRegistry(request, id), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserProfile(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(userProfileService.getUserProfile(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
