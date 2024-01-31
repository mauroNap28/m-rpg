package mauro.mrpg.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import mauro.mrpg.dto.enums.ClassesEnum;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CharacterClassDto {

    @Enumerated(EnumType.STRING)
    private ClassesEnum characterClass;

    private Integer levels;

}
