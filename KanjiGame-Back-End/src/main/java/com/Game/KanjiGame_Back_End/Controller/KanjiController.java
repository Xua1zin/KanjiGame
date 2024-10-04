package com.Game.KanjiGame_Back_End.Controller;

import com.Game.KanjiGame_Back_End.DTO.KanjiQuizDTO;
import com.Game.KanjiGame_Back_End.Entity.KanjiEntity;
import com.Game.KanjiGame_Back_End.Service.KanjiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kanji")
public class KanjiController {
    @Autowired
    KanjiService kanjiService;

    @GetMapping("/r34d1ng")
    public ResponseEntity<KanjiQuizDTO> getRandomKanjiAndChoices(@RequestParam String categoryName,
                                                                 @RequestParam String levelName){
            return ResponseEntity.ok(kanjiService.getRandomKanjiAndChoices(categoryName, levelName));
    }
}
