package com.example.demo.service;

import com.example.demo.eception.PlayerNotFoundException;
import com.example.demo.mapper.ServiceRepositoryMapper;
import com.example.demo.repository.PlayerRepositoryJpa;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.PlayerDataAverages;
import com.example.demo.service.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.demo.utils.CommonUtils.*;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepositoryJpa playerRepositoryJpa;

    @Autowired
    public PlayerServiceImpl(PlayerRepositoryJpa playerRepositoryJpa) {
        this.playerRepositoryJpa = playerRepositoryJpa;
    }

    @Override
    public PlayerDto createPlayer(CreatePlayerDto createPlayerDto) {
        int level = calculateLevel(createPlayerDto.getExperience());
        int untilNextLevel = calculateUntilNextLevel(level, createPlayerDto.getExperience());

        Player newPlayer = ServiceRepositoryMapper.mapToPlayer(createPlayerDto);
        newPlayer.setLevel(level);
        newPlayer.setUntilNextLevel(untilNextLevel);
        Player savedPlayer = playerRepositoryJpa.save(newPlayer);
        return ServiceRepositoryMapper.mapToPlayerDto(savedPlayer);
    }

    @Override
    public List<PlayerDto> findPlayers(GetPlayersDto getPlayersDto) {
        Specification<Player> searchSpec = UserSpecification.of(getPlayersDto);

        Pageable pageable = PageRequest.of(
                getPlayersDto.getPageNumber(),
                getPlayersDto.getPageSize(),
                Sort.by(getPlayersDto.getOrder().getFieldName()).ascending()
        );

        Page<Player> players = playerRepositoryJpa.findAll(searchSpec, pageable);

        return players
                .stream()
                .map(ServiceRepositoryMapper::mapToPlayerDto)
                .toList();
    }

    @Override
    public Long countPlayers(GetPlayersDto getPlayersDto) {
        Specification<Player> searchSpec = UserSpecification.of(getPlayersDto);
        return playerRepositoryJpa.count(searchSpec);
    }

    @Override
    public PlayerDto findPlayer(Long id) {
        Optional<Player> player = playerRepositoryJpa.findById(id);

        if (player.isEmpty()) {
            throw new PlayerNotFoundException();
        }
        return ServiceRepositoryMapper.mapToPlayerDto(player.get());
    }

    @Override
    public PlayerDto updatePlayer(Long id, UpdatePlayerDto updatePlayerDto) {
        Player player = playerRepositoryJpa.findById(id).orElseThrow(PlayerNotFoundException::new);
        fillPlayer(updatePlayerDto, player);
        Player savedPlayer = playerRepositoryJpa.save(player);
        return ServiceRepositoryMapper.mapToPlayerDto((savedPlayer));
    }

    @Override
    public void delete(Long id) {
        playerRepositoryJpa.deleteById(id);
    }

    @Override
    public AverageValuesDto getAverageValues() {
        PlayerDataAverages playerDataAverages = playerRepositoryJpa.getPlayerDataAverages();
        AverageValuesDto averageValuesDto = ServiceRepositoryMapper.mapToAverageValuesDto(playerDataAverages);
        return averageValuesDto;
    }

    private static void fillPlayer(UpdatePlayerDto updatePlayerDto, Player player) {
        if (Objects.nonNull(updatePlayerDto.getName())) {
            player.setName(updatePlayerDto.getName());
        }

        if (Objects.nonNull(updatePlayerDto.getTitle())) {
            player.setTitle(updatePlayerDto.getTitle());
        }

        if (Objects.nonNull(updatePlayerDto.getRace())) {
            player.setRace(updatePlayerDto.getRace());
        }

        if (Objects.nonNull(updatePlayerDto.getProfession())) {
            player.setProfession(updatePlayerDto.getProfession());
        }

        if (Objects.nonNull(updatePlayerDto.getBirthday())) {
            player.setBirthday(convertLongToLocalDate(updatePlayerDto.getBirthday()));
        }

        if (Objects.nonNull(updatePlayerDto.getBanned())) {
            player.setBanned(updatePlayerDto.getBanned());
        }

        if (Objects.nonNull(updatePlayerDto.getExperience())) {
            player.setExperience(updatePlayerDto.getExperience());
        }

        if (Objects.nonNull(updatePlayerDto.getExperience())) {
            player.setLevel(calculateLevel(updatePlayerDto.getExperience()));
            player.setUntilNextLevel(calculateUntilNextLevel(player.getLevel(), updatePlayerDto.getExperience()));
        }
    }
}
