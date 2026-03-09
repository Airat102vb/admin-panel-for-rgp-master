package com.example.demo.service;

import com.example.demo.controller.dto.*;

import java.util.List;

public interface PlayerService {

    PostPlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest);

    List<GetPlayersResponse> findPlayers(GetPlayersRequest getPlayersRequest);

    Long countPlayers(GetPlayersRequest getPlayersRequest);

    GetPlayersResponse findPlayer(Long id);

    PutPlayerResponse updatePlayer(Long id, UpdatePlayerRequest playerUpdates);

    void delete(Long id);
}
