package com.example.demo.service;

import com.example.demo.controller.dto.*;

import java.util.List;

public interface PlayerService {

    PostPlayerResponse createPlayer(PostPlayerRequest postPlayerRequest);

    List<GetPlayersResponse> findPlayers(GetPlayersRequest getPlayersRequest);

    Integer countPlayers(GetPlayersRequest getPlayersRequest);

    GetPlayersResponse findPlayer(Long id);

    PutPlayerResponse updatePlayer(Long id, PutPlayerRequest playerUpdates);

    void delete(Long id);
}
