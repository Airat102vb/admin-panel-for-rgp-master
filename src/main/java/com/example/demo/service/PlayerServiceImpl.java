package com.example.demo.service;

import com.example.demo.controller.dto.GetPlayersResponse;
import com.example.demo.controller.dto.PostPlayerResponse;
import com.example.demo.controller.dto.PutPlayerResponse;
import com.example.demo.eception.PlayerNotFoundException;
import com.example.demo.mapper.ControllerServiceMapper;
import com.example.demo.mapper.ServiceRepositoryMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.entity.Player;
import com.example.demo.service.dto.CreatePlayerDto;
import com.example.demo.service.dto.GetPlayersDto;
import com.example.demo.service.dto.PlayerDto;
import com.example.demo.service.dto.UpdatePlayerDto;
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
    public PostPlayerResponse createPlayer(CreatePlayerDto createPlayerDto) {
        int level = calculateLevel(createPlayerDto.getExperience());
        int untilNextLevel = calculateUntilNextLevel(level, createPlayerDto.getExperience());

        Player newPlayer = ServiceRepositoryMapper.mapToPlayer(createPlayerDto);
        newPlayer.setLevel(level);
        newPlayer.setUntilNextLevel(untilNextLevel);

        PlayerDto playerDto = ServiceRepositoryMapper.mapToPlayerDto(playerRepository.insert(newPlayer));
        return ControllerServiceMapper.mapToPostPlayerResponse(playerDto);
    }

    @Override
    public List<GetPlayersResponse> findPlayers(GetPlayersDto getPlayersDto) {
        return playerRepository
                .findAll(ServiceRepositoryMapper.mapToSelectPlayersEntity(getPlayersDto))
                .stream()
                .map(ServiceRepositoryMapper::mapToPlayerDto)
                .map(ControllerServiceMapper::mapToGetPlayersResponse)
                .toList();
    }

    @Override
    public Long countPlayers(GetPlayersDto getPlayersDto) {
        return playerRepository.count(ServiceRepositoryMapper.mapToSelectPlayersEntity(getPlayersDto));
    }

    @Override
    public GetPlayersResponse findPlayer(Long id) {
        Optional<Player> player = playerRepository.findById(id);

        if (Objects.isNull(player)) {
            throw new PlayerNotFoundException();
        }

        return ControllerServiceMapper
                .mapToGetPlayersResponse(ServiceRepositoryMapper.mapToPlayerDto(player.get()));
    }

    @Override
    public PutPlayerResponse updatePlayer(Long id, UpdatePlayerDto updatePlayerDto) {
        Player player = playerRepository.findById(id).orElseThrow(PlayerNotFoundException::new);
        ServiceRepositoryMapper.mapToPlayer(updatePlayerDto, player);
        PlayerDto playerDto = ServiceRepositoryMapper.mapToPlayerDto((playerRepository.update(player)));
        return ControllerServiceMapper.mapToPutPlayerResponse(playerDto);
    }

    @Override
    public void delete(Long id) {
        playerRepository.deleteById(id);
    }
}
