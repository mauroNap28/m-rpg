package mauro.mrpg.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewCharacterRequest extends GameCharacterDto {

    private Long gameId;
}
