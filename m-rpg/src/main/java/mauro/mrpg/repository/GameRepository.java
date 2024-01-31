package mauro.mrpg.repository;

import mauro.mrpg.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {

    Optional<List<Game>> findByUser_Id(Long id);

}
