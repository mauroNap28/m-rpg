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

    @Enumerated(EnumType.STRING)
    private ClassesEnum characterClass;

    private Integer levels;

}
