package mauro.mrpg.dto;

import lombok.*;
import mauro.mrpg.model.CharacterClass;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacterDto {

    private String name;

    private Integer level;

    private Long exp;

    private CharacterClass characterClass;

}
