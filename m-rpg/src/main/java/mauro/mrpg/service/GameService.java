package mauro.mrpg.service;

import mauro.mrpg.dto.NewGameRequest;
import mauro.mrpg.model.Game;
import mauro.mrpg.model.GameCharacter;
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
        List<Game> games = gameRepository.findByUser_Id(request.getIdUser())
            .orElse(null);
        if (Objects.nonNull(games)) {
            List<String> gameNames = games.stream().map(Game::getName).toList();
            if (gameNames.contains(request.getName())) {
                throw new RuntimeException("Taken name.");
            }
        }
        games.add(
            Game.builder()
                .name(request.getName())
                .dateOfCreation(LocalDateTime.now())
                .user(userRepository.findById(request.getIdUser())
                    .orElseThrow(() -> new RuntimeException("User not found.")))
                .build()
        );
        gameRepository.saveAll(games);
        return games.getLast();
    }

    public List<Game> getAllGamesByUserId(Long id) {
        return gameRepository.findByUser_Id(id)
            .orElseThrow(() -> new RuntimeException("User has no games."));
    }

    public void deleteById(Long id) {
        gameRepository.deleteById(id);
    }
}
