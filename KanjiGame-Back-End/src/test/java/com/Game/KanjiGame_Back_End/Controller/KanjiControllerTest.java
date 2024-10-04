package com.Game.KanjiGame_Back_End.Controller;

import com.Game.KanjiGame_Back_End.DTO.KanjiQuizDTO;
import com.Game.KanjiGame_Back_End.Entity.KanjiEntity;
import com.Game.KanjiGame_Back_End.Entity.LevelEntity;
import com.Game.KanjiGame_Back_End.Repository.KanjiRepository;
import com.Game.KanjiGame_Back_End.Repository.LevelRepository;
import com.Game.KanjiGame_Back_End.Service.KanjiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class KanjiControllerTest {
    @Autowired
    KanjiController kanjiController;
    @MockBean
    KanjiRepository kanjiRepository;

    @Test
    void getRandomKanjiAndChoicesSuccess() {
        KanjiEntity kanji1 = new KanjiEntity();
        kanji1.setUuid(UUID.randomUUID());
        kanji1.setCharacter("日");
        kanji1.setReading("にち");

        KanjiEntity kanji2 = new KanjiEntity();
        kanji2.setUuid(UUID.randomUUID());
        kanji2.setCharacter("月");
        kanji2.setReading("つき");

        KanjiEntity kanji3 = new KanjiEntity();
        kanji3.setUuid(UUID.randomUUID());
        kanji3.setCharacter("山");
        kanji3.setReading("やま");

        KanjiEntity kanji4 = new KanjiEntity();
        kanji4.setUuid(UUID.randomUUID());
        kanji4.setCharacter("母");
        kanji4.setReading("はは");

        KanjiEntity kanji5 = new KanjiEntity();
        kanji5.setUuid(UUID.randomUUID());
        kanji5.setCharacter("魚");
        kanji5.setReading("さかな");

        String categoryName = null;
        String levelName = null;

        List<KanjiEntity> kanjiEntityList = Arrays.asList(kanji1, kanji2, kanji3, kanji4, kanji5);
        when(kanjiRepository.findAll()).thenReturn(kanjiEntityList);

        ResponseEntity<KanjiQuizDTO> response = kanjiController.getRandomKanjiAndChoices(categoryName, levelName);

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}