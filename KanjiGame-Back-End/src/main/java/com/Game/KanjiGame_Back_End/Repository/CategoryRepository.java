package com.Game.KanjiGame_Back_End.Repository;

import com.Game.KanjiGame_Back_End.Entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    CategoryEntity findByName(String name);
}
