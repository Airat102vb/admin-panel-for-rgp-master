package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.service.dto.CreatePlayerDto;
import com.example.demo.service.dto.GetPlayersDto;
import com.example.demo.service.dto.UpdatePlayerDto;

import java.util.List;

public interface PlayerService {

    PostPlayerResponse createPlayer(CreatePlayerDto createPlayerDto);

    List<GetPlayersResponse> findPlayers(GetPlayersDto getPlayersDto);

    Long countPlayers(GetPlayersDto getPlayersDto);

    GetPlayersResponse findPlayer(Long id);

    PutPlayerResponse updatePlayer(Long id, UpdatePlayerDto updatePlayerDto);

    void delete(Long id);
}
