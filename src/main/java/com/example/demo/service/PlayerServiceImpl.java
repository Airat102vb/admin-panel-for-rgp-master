package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.eception.PlayerNotFoundException;
import com.example.demo.mapper.GetPlayersMapper;
import com.example.demo.mapper.PostPlayerMapper;
import com.example.demo.mapper.PutPlayerMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.demo.utils.CommonUtils.calculateLevel;
import static com.example.demo.utils.CommonUtils.calculateUntilNextLevel;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public PostPlayerResponse createPlayer(CreatePlayerRequest createPlayerRequest) {
        int level = calculateLevel(createPlayerRequest.getExperience());
        int untilNextLevel = calculateUntilNextLevel(level, createPlayerRequest.getExperience());

        Player newPlayer = PostPlayerMapper.toPlayer(createPlayerRequest);
        newPlayer.setLevel(level);
        newPlayer.setUntilNextLevel(untilNextLevel);

        Player createdPlayer = playerRepository.insert(newPlayer);
        return GetPlayersMapper.toPostPlayerResponse(createdPlayer);
    }

    @Override
    public List<GetPlayersResponse> findPlayers(GetPlayersRequest getPlayersRequest) {
        return playerRepository
                .findAll(GetPlayersMapper.toSelectPlayersEntity(getPlayersRequest))
                .stream()
                .map(GetPlayersMapper::toGetPlayerResponse)
                .toList();
    }

    @Override
    public Long countPlayers(GetPlayersRequest getPlayersRequest) {
        return playerRepository.count(GetPlayersMapper.toSelectPlayersEntity(getPlayersRequest));
    }

    @Override
    public GetPlayersResponse findPlayer(Long id) {
        Optional<Player> player = playerRepository.findById(id);

        if (Objects.isNull(player)) {
            throw new PlayerNotFoundException();
        }

        return GetPlayersMapper.toGetPlayerResponse(player.get());
    }

    @Override
    public PutPlayerResponse updatePlayer(Long id, UpdatePlayerRequest playerUpdates) {
        Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        PutPlayerMapper.toPlayer(playerUpdates, player);
        return PutPlayerMapper.toPutPlayerResponse(playerRepository.update(player));
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
