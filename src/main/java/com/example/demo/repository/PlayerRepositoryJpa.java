package com.example.demo.repository;

import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.PlayerDataAverages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface PlayerRepositoryJpa extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {

    @Query("SELECT AVG(p.experience) as experienceAverage, AVG(p.level) as levelAverage FROM player p")
    PlayerDataAverages getPlayerDataAverages();
}
