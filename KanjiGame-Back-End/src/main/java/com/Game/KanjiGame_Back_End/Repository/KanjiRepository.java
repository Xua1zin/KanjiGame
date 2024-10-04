package com.Game.KanjiGame_Back_End.Repository;

import com.Game.KanjiGame_Back_End.Entity.KanjiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface KanjiRepository extends JpaRepository<KanjiEntity, UUID> {
    List<KanjiEntity> findByCategoriesUuid(UUID categoryUuid);

    List<KanjiEntity> findByLevelUuid(UUID levelUuid);

    List<KanjiEntity> findByLevelUuidAndCategoriesUuid(UUID levelUuid, UUID categoryUuid);
}
