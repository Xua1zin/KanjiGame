package com.Game.KanjiGame_Back_End.DTO;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class KanjiQuizDTO {
    private String kanji;
    private List<String> choices;
    private String correctAnswer;
}
