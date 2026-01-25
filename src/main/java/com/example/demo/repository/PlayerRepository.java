package com.example.demo.repository;

import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.SelectPlayers;

import java.util.List;

public interface PlayerRepository {

    Player savePlayer(Player player);

    List<Player> selectPlayers(SelectPlayers selectPlayers);

    Integer countPlayers(SelectPlayers selectPlayers);

    Player selectPlayer(Long id);

    Player updatePlayer(Long id, Player player);

    void deletePlayer(Long id);
}
