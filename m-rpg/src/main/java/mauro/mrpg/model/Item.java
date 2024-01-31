package mauro.mrpg.model;

import jakarta.persistence.*;
import lombok.*;
import mauro.mrpg.dto.enums.StatsEnum;

import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String type;

    private String description;

    @ManyToOne
    private GameCharacter character;
}
