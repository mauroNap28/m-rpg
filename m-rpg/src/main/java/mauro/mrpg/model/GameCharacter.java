package mauro.mrpg.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GameCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer level;

    private Long exp;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @Enumerated(EnumType.STRING)
    private List<CharacterClass> characterClassList;


}
