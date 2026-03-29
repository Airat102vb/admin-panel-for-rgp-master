package com.example.demo.repository;

import com.example.demo.repository.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PlayerRepositoryJpa extends JpaRepository<Player, Long>, JpaSpecificationExecutor<Player> {
}
