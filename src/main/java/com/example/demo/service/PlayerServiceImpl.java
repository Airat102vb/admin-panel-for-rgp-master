package com.example.demo.service;

import com.example.demo.controller.dto.*;
import com.example.demo.eception.PlayerException;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import com.example.demo.mapper.GetPlayersMapper;
import com.example.demo.mapper.PostPlayerMapper;
import com.example.demo.mapper.PutPlayerMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @PostConstruct
    public void init() {
        for (int i = 0; i < 500; i++) {
            PostPlayerRequest player = new PostPlayerRequest();
            player.setName("Name" + i);
            player.setTitle("Title" + i);
            player.setRace(Race.ELF);
            player.setProfession(Profession.DRUID);
            player.setBirthday(253465656L + i);
            player.setExperience(i);

            createPlayer(player);
        }
    }

    @Override
    public PostPlayerResponse createPlayer(PostPlayerRequest postPlayerRequest) {
        Player newPlayer = playerRepository.savePlayer(PostPlayerMapper.toPlayer(postPlayerRequest));
        return GetPlayersMapper.toPostPlayerResponse(newPlayer);
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
        return PutPlayerMapper
                .toPutPlayerResponse(playerRepository.updatePlayer(id, PutPlayerMapper.toPlayer(playerUpdates)));
    }

    @Override
    public void delete(Long id) {
        playerRepository.deletePlayer(id);
    }
}
