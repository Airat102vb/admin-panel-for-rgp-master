package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.service.dto.CreatePlayerDto;
import com.example.demo.service.dto.GetPlayersDto;
import com.example.demo.service.dto.PlayerDto;
import com.example.demo.service.dto.UpdatePlayerDto;

import java.util.List;

public interface PlayerService {

    PlayerDto createPlayer(CreatePlayerDto createPlayerDto);

    List<PlayerDto> findPlayers(GetPlayersDto getPlayersDto);

    Long countPlayers(GetPlayersDto getPlayersDto);

    PlayerDto findPlayer(Long id);

    PlayerDto updatePlayer(Long id, UpdatePlayerDto updatePlayerDto);

    void delete(Long id);
}
