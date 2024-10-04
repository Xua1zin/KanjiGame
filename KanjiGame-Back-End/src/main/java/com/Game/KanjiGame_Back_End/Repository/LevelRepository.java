package com.Game.KanjiGame_Back_End.Repository;

import com.Game.KanjiGame_Back_End.Entity.LevelEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LevelRepository extends JpaRepository<LevelEntity, UUID> {
    LevelEntity findByName(String name);
}
