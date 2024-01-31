package mauro.mrpg.service;

import mauro.mrpg.dto.CharacterClassDto;
import mauro.mrpg.dto.GameCharacterDto;
import mauro.mrpg.dto.NewCharacterRequest;
import mauro.mrpg.model.CharacterClass;
import mauro.mrpg.model.Game;
import mauro.mrpg.model.GameCharacter;
import mauro.mrpg.repository.GameCharacterRepository;
import mauro.mrpg.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class GameCharacterService {

    @Autowired
    GameCharacterRepository gameCharacterRepository;

    @Autowired
    GameRepository gameRepository;

    public GameCharacter newCharacter(NewCharacterRequest request) {
        Game game = gameRepository.findById(request.getGameId())
            .orElseThrow(() -> new RuntimeException("Game not found."));
        if (Objects.nonNull(game.getGameCharacter()))
            throw new RuntimeException("The game have a game character");
        else {
            List<CharacterClass> characterClassList = new ArrayList<>();
            characterClassList.add(request.getCharacterClass());
            characterClassList.get(0).setLevels(1);
            game.setGameCharacter(
                GameCharacter.builder()
                    .name(request.getName())
                    .level(1)
                    .exp(0L)
                    .characterClassList(characterClassList)
                    .build()
            );
            gameRepository.save(game);
            return game.getGameCharacter();
        }
    }



    public void deleteCharacter(Long id) {
        gameCharacterRepository.deleteById(id);
    }

    public int getCharacterLevel(Long id) {
        GameCharacter character = gameCharacterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Character not found."));
        int result = 0;
        for (CharacterClass cc : character.getCharacterClassList()) {
            result += cc.getLevels();
        }
        return result;
    }

    public GameCharacter levelUp(Long id, CharacterClassDto request) {
        GameCharacter gameCharacter = gameCharacterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("GameCharacter not found"));
        if (gameCharacter.getCharacterClassList().stream().map(CharacterClass::getCharacterClass).toList().contains(request.getCharacterClass())) {
            for (CharacterClass characterClass : gameCharacter.getCharacterClassList()) {
                if (characterClass.getCharacterClass().equals(request.getCharacterClass())) {
                    characterClass.setLevels(characterClass.getLevels() + 1);
                    gameCharacter.setLevel(gameCharacter.getLevel() + 1);
                }
            }
        } else {
            gameCharacter.getCharacterClassList().add(
                CharacterClass.builder()
                    .characterClass(request.getCharacterClass())
                    .levels(1)
                    .build()
            );
        }
        gameCharacterRepository.save(gameCharacter);
        return gameCharacter;
    }

    public GameCharacter getCharacter(Long id) {
        return gameCharacterRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Character not found."));
    }
}
