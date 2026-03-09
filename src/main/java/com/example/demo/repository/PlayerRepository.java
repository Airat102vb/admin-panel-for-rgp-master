package com.example.demo.repository;

import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.SelectPlayers;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {

    Player insert(Player player);

    Optional<Player> findById(Long id);

    Player update(Player player);

    void deleteById(Long id);

    Long count(SelectPlayers selectPlayers);

    List<Player> findAll(SelectPlayers selectPlayers);
}
