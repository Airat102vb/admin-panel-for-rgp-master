package com.example.demo.service;

import com.example.demo.dto.PlayerFilterDto;
import com.example.demo.dto.PutPlayerDto;
import com.example.demo.entity.Player;

import java.util.List;

public interface PlayerService {

    Player savePlayer(Player player);

    List<Player> findPlayers(PlayerFilterDto playerFilterDto);

    Integer countPlayers(PlayerFilterDto playerFilterDto);

    Player findPlayer(Long id);

    Player updatePlayer(Long id, PutPlayerDto playerUpdates);

    void delete(Long id);
}
