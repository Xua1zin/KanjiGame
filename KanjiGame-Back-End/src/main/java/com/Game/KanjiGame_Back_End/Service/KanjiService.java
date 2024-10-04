package com.Game.KanjiGame_Back_End.Service;

import com.Game.KanjiGame_Back_End.DTO.KanjiQuizDTO;
import com.Game.KanjiGame_Back_End.Entity.CategoryEntity;
import com.Game.KanjiGame_Back_End.Entity.KanjiEntity;
import com.Game.KanjiGame_Back_End.Entity.LevelEntity;
import com.Game.KanjiGame_Back_End.Repository.CategoryRepository;
import com.Game.KanjiGame_Back_End.Repository.KanjiRepository;
import com.Game.KanjiGame_Back_End.Repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class KanjiService {
    @Autowired
    private KanjiRepository kanjiRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private LevelRepository levelRepository;

    public KanjiQuizDTO getRandomKanjiAndChoices(String categoryName, String levelName){
        try {
            List<KanjiEntity> allKanjis = new ArrayList<>();

            if(categoryName != null && levelName == null) {
                CategoryEntity categoryEntity = categoryRepository.findByName(categoryName);
                allKanjis = kanjiRepository.findByCategoriesUuid(categoryEntity.getUuid());
            } else if(levelName != null && categoryName == null){
                LevelEntity levelEntity = levelRepository.findByName(levelName);
                allKanjis = kanjiRepository.findByLevelUuid(levelEntity.getUuid());
            }
            else if (levelName != null && categoryName != null){
                LevelEntity levelEntity = levelRepository.findByName(levelName);
                CategoryEntity categoryEntity = categoryRepository.findByName(categoryName);
                allKanjis = kanjiRepository.findByLevelUuidAndCategoriesUuid(levelEntity.getUuid(), categoryEntity.getUuid());
            } else {
                allKanjis = kanjiRepository.findAll();
            }

            if(allKanjis.isEmpty()){
                throw new RuntimeException("No kanji found");
            }
            if (allKanjis.size() < 5) {
                throw new RuntimeException("Not enough kanjis to generate choices");
            }

            KanjiEntity answer = allKanjis.get(new Random().nextInt(allKanjis.size()));

            Set<String> choices = new HashSet<>();
            choices.add(answer.getReading());

            while (choices.size() < 5) {
                KanjiEntity kanjiEntity = allKanjis.get(new Random().nextInt(allKanjis.size()));
                choices.add(kanjiEntity.getReading());
            }

            List<String> choicesList = new ArrayList<>(choices);
            Collections.shuffle(choicesList);

            return new KanjiQuizDTO(answer.getCharacter(), choicesList, answer.getReading());
        } catch (RuntimeException e) {
            System.out.println("Error making the question: " + e.getMessage());
            return new KanjiQuizDTO(null, Collections.emptyList(), null);
        }
    }
}
