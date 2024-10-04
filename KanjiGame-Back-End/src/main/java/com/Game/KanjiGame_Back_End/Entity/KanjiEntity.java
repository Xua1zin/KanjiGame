package com.Game.KanjiGame_Back_End.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "kanji")
public class KanjiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @NotBlank
    private String character;

    @NotBlank
    private String reading;

    @NotBlank
    private String meaning;

    @ManyToMany
    @JoinTable(
            name = "kanji_category",
            joinColumns = @JoinColumn(name = "kanji_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<CategoryEntity> categories = new ArrayList<>();

    @ManyToOne
    @JoinTable(name = "level_id")
    private LevelEntity level;
}
