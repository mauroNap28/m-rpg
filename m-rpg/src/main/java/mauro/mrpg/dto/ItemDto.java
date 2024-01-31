package mauro.mrpg.dto;

import jakarta.persistence.*;
import lombok.*;
import mauro.mrpg.model.GameCharacter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {

    private String name;

    private String type;

    private String description;

    private int quantity;

    private Long characterId;

}
