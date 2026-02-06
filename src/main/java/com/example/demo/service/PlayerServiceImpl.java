package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.eception.PlayerException;
import com.example.demo.mapper.GetPlayersMapper;
import com.example.demo.mapper.PostPlayerMapper;
import com.example.demo.mapper.PutPlayerMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.example.demo.utils.CommonUtils.calculateLevel;
import static com.example.demo.utils.CommonUtils.calculateUntilNextLevel;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PostPlayerResponse createPlayer(PostPlayerRequest postPlayerRequest) {
        int level = calculateLevel(postPlayerRequest.getExperience());
        int untilNextLevel = calculateUntilNextLevel(level, postPlayerRequest.getExperience());

        Player newPlayer = PostPlayerMapper.toPlayer(postPlayerRequest);
        newPlayer.setLevel(level);
        newPlayer.setUntilNextLevel(untilNextLevel);

        Player createdPlayer = playerRepository.savePlayer(newPlayer);
        return GetPlayersMapper.toPostPlayerResponse(createdPlayer);
    }

    @Override
    public List<GetPlayersResponse> findPlayers(GetPlayersRequest getPlayersRequest) {
        return playerRepository
                .selectPlayers(GetPlayersMapper.toSelectPlayersEntity(getPlayersRequest))
                .stream()
                .map(GetPlayersMapper::toGetPlayerResponse)
                .toList();
    }

    @Override
    public Integer countPlayers(GetPlayersRequest getPlayersRequest) {
        return playerRepository.countPlayers(GetPlayersMapper.toSelectPlayersEntity(getPlayersRequest));
    }

    @Override
    public GetPlayersResponse findPlayer(Long id) {
        Player player = playerRepository.selectPlayer(id);

        if (Objects.isNull(player)) {
            throw new PlayerException.NotFound();
        }

        return GetPlayersMapper.toGetPlayerResponse(player);
    }

    @Override
    public PutPlayerResponse updatePlayer(Long id, PutPlayerRequest playerUpdates) {
        int level = calculateLevel(playerUpdates.getExperience());
        int untilNextLevel = calculateUntilNextLevel(level, playerUpdates.getExperience());

        Player updatePlayer = PutPlayerMapper.toPlayer(playerUpdates);
        updatePlayer.setLevel(level);
        updatePlayer.setUntilNextLevel(untilNextLevel);

        return PutPlayerMapper.toPutPlayerResponse(playerRepository.updatePlayer(id, updatePlayer));
    }

    @Override
    public void delete(Long id) {
        playerRepository.deletePlayer(id);
    }
}
