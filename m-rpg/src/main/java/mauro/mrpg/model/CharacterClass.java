package mauro.mrpg.model;

import jakarta.persistence.*;
import lombok.*;
import mauro.mrpg.dto.enums.ClassesEnum;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ClassesEnum characterClass;

    private Integer levels;

    @ManyToOne(cascade = CascadeType.ALL)
    private GameCharacter gameCharacter;

}
