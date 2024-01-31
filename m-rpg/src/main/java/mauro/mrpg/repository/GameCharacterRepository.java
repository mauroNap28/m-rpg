package mauro.mrpg.repository;

import mauro.mrpg.model.GameCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameCharacterRepository extends JpaRepository<GameCharacter, Long> {

}
