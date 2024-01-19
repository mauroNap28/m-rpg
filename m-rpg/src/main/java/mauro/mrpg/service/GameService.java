package mauro.mrpg.service;

import mauro.mrpg.dto.NewGameRequest;
import mauro.mrpg.model.Game;
import mauro.mrpg.model.User;
import mauro.mrpg.repository.GameRepository;
import mauro.mrpg.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class GameService {

    @Autowired
    GameRepository gameRepository;
    @Autowired
    UserRepository userRepository;

    public Game newGame(NewGameRequest request) {
         User user = userRepository.findById(request.getIdUser()).orElse(null);
         if (Objects.nonNull(user)) {
             if (!user.getGames().stream().map(Game::getName).toList().contains(request.getName())) {
                 user.getGames().add(
                     Game.builder()
                         .name(request.getName())
                         .dateOfCreation(LocalDateTime.now())
                         .build()
                 );
                 userRepository.save(user);
                 return user.getGames().getLast();
             } else {
                 throw new RuntimeException("Name taken already");
             }
         } else {
             throw new RuntimeException("User id not found.");
         }
    }

    public List<Game> getAllGamesByUserId(Long id) {
        User user = userRepository.findById(id).orElse(null);
        if (Objects.nonNull(user)) {
            return user.getGames();
        } else {
            throw new RuntimeException("User not found.");
        }
    }
}
