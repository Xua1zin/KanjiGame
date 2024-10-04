package com.Game.KanjiGame_Back_End.Service;

import com.Game.KanjiGame_Back_End.DTO.KanjiQuizDTO;
import com.Game.KanjiGame_Back_End.Entity.CategoryEntity;
import com.Game.KanjiGame_Back_End.Entity.KanjiEntity;
import com.Game.KanjiGame_Back_End.Entity.LevelEntity;
import com.Game.KanjiGame_Back_End.Repository.CategoryRepository;
import com.Game.KanjiGame_Back_End.Repository.KanjiRepository;
import com.Game.KanjiGame_Back_End.Repository.LevelRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class KanjiServiceTest {
    @Autowired
    KanjiService kanjiService;
    @MockBean
    KanjiRepository kanjiRepository;
    @MockBean
    LevelRepository levelRepository;
    @MockBean
    CategoryRepository categoryRepository;

    @Test
    void getRandomKanjiAndChoicesLevelSuccess() {
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
        String levelName = "N5";
        LevelEntity level = new LevelEntity();
        level.setName(levelName);
        level.setUuid(UUID.randomUUID());

        kanji1.setLevel(level);
        kanji2.setLevel(level);
        kanji3.setLevel(level);
        kanji4.setLevel(level);
        kanji5.setLevel(level);

        List<KanjiEntity> kanjiEntityList = Arrays.asList(kanji1, kanji2, kanji3, kanji4, kanji5);
        when(levelRepository.findByName(levelName)).thenReturn(level);
        when(kanjiRepository.findByLevelUuid(level.getUuid())).thenReturn(kanjiEntityList);

        KanjiQuizDTO result = kanjiService.getRandomKanjiAndChoices(categoryName, levelName);

        assertTrue(
                kanjiEntityList.stream().map(KanjiEntity::getCharacter).collect(Collectors.toList())
                        .contains(result.getKanji()), "Kanji retornado não está na lista"
        );
        assertEquals(result.getChoices().size(), 5);
    }

    @Test
    void getRandomKanjiAndChoicesCategorySuccess() {
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

        String categoryName = "Food";
        String levelName = null;
        CategoryEntity category = new CategoryEntity();
        category.setName(categoryName);
        category.setUuid(UUID.randomUUID());
        List<CategoryEntity> categories = new ArrayList<>();
        categories.add(category);

        kanji1.setCategories(categories);
        kanji2.setCategories(categories);
        kanji3.setCategories(categories);
        kanji4.setCategories(categories);
        kanji5.setCategories(categories);

        List<KanjiEntity> kanjiEntityList = Arrays.asList(kanji1, kanji2, kanji3, kanji4, kanji5);
        when(categoryRepository.findByName(categoryName)).thenReturn(category);
        when(kanjiRepository.findByCategoriesUuid(category.getUuid())).thenReturn(kanjiEntityList);

        KanjiQuizDTO result = kanjiService.getRandomKanjiAndChoices(categoryName, levelName);

        assertTrue(
                kanjiEntityList.stream().map(KanjiEntity::getCharacter).collect(Collectors.toList())
                        .contains(result.getKanji()), "Kanji retornado não está na lista"
        );
        assertEquals(result.getChoices().size(), 5);
    }

    @Test
    void getRandomKanjiAndChoicesLevelAndCategorySuccess() {
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

        String categoryName = "Food";
        String levelName = "N5";
        LevelEntity level = new LevelEntity();
        level.setName(levelName);
        level.setUuid(UUID.randomUUID());

        CategoryEntity category = new CategoryEntity();
        category.setName(categoryName);
        category.setUuid(UUID.randomUUID());
        List<CategoryEntity> categories = new ArrayList<>();
        categories.add(category);

        kanji1.setCategories(categories);
        kanji2.setCategories(categories);
        kanji3.setCategories(categories);
        kanji4.setCategories(categories);
        kanji5.setCategories(categories);
        kanji1.setLevel(level);
        kanji2.setLevel(level);
        kanji3.setLevel(level);
        kanji4.setLevel(level);
        kanji5.setLevel(level);

        List<KanjiEntity> kanjiEntityList = Arrays.asList(kanji1, kanji2, kanji3, kanji4, kanji5);
        when(categoryRepository.findByName(categoryName)).thenReturn(category);
        when(levelRepository.findByName(levelName)).thenReturn(level);
        when(kanjiRepository.findByLevelUuidAndCategoriesUuid(level.getUuid(), category.getUuid())).thenReturn(kanjiEntityList);

        KanjiQuizDTO result = kanjiService.getRandomKanjiAndChoices(categoryName, levelName);

        assertTrue(
                kanjiEntityList.stream().map(KanjiEntity::getCharacter).collect(Collectors.toList())
                        .contains(result.getKanji()), "Kanji retornado não está na lista"
        );
        assertEquals(result.getChoices().size(), 5);
    }

    @Test
    void getRandomKanjiAndChoicesFindAllSuccess() {
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

        KanjiQuizDTO result = kanjiService.getRandomKanjiAndChoices(categoryName, levelName);

        assertTrue(
                kanjiEntityList.stream().map(KanjiEntity::getCharacter).collect(Collectors.toList())
                        .contains(result.getKanji()), "Kanji retornado não está na lista"
        );
        assertEquals(result.getChoices().size(), 5);
    }

    @Test
    void getRandomKanjiAndChoicesFailure(){
        String category = null;
        String level = null;
        when(kanjiRepository.findAll()).thenThrow(new RuntimeException());

        KanjiQuizDTO result = kanjiService.getRandomKanjiAndChoices(category, level);

        assertNotNull(result);
        assertNull(result.getKanji());
        assertEquals(0, result.getChoices().size());
    }

    @Test
    void getRandomKanjiAndChoicesKanjiListEmpty(){
        String category = null;
        String level = null;
        List<KanjiEntity> kanjiEntityList = new ArrayList<>();
        when(kanjiRepository.findAll()).thenReturn(kanjiEntityList);

        KanjiQuizDTO result = kanjiService.getRandomKanjiAndChoices(category, level);

        assertNotNull(result);
        assertNull(result.getKanji());
        assertTrue(result.getChoices().isEmpty());
    }

    @Test
    void getRandomKanjiAndChoicesLessThanFiveKanjis() {
        String category = null;
        String level = null;

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

        List<KanjiEntity> kanjiEntityList = Arrays.asList(kanji1, kanji2, kanji3);
        when(kanjiRepository.findAll()).thenReturn(kanjiEntityList);

        KanjiQuizDTO result = kanjiService.getRandomKanjiAndChoices(category, level);

        assertNotNull(result);
        assertNull(result.getKanji());
        assertTrue(result.getChoices().isEmpty());
    }

}