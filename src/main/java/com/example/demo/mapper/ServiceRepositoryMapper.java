package com.example.demo.mapper;

import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.PlayerDataAverages;
import com.example.demo.repository.entity.SelectPlayers;
import com.example.demo.service.dto.*;

import java.util.Objects;

import static com.example.demo.utils.CommonUtils.*;

public class ServiceRepositoryMapper {

    public static Player mapToPlayer(CreatePlayerDto createPlayerDto) {
        Player player = new Player();
        player.setName(createPlayerDto.getName());
        player.setTitle(createPlayerDto.getTitle());
        player.setRace(createPlayerDto.getRace());
        player.setProfession(createPlayerDto.getProfession());
        player.setBirthday(convertLongToLocalDate(createPlayerDto.getBirthday()));
        player.setBanned(createPlayerDto.getBanned());
        player.setExperience(createPlayerDto.getExperience());

        return player;
    }

    public static SelectPlayers mapToSelectPlayersEntity(GetPlayersDto getPlayersDto) {
        SelectPlayers selectPlayers = new SelectPlayers();

        if (Objects.nonNull(getPlayersDto.getName())) {
            selectPlayers.setName(getPlayersDto.getName());
        }
        if (Objects.nonNull(getPlayersDto.getTitle())) {
            selectPlayers.setTitle(getPlayersDto.getTitle());
        }
        if (Objects.nonNull(getPlayersDto.getRace())) {
            selectPlayers.setRace(getPlayersDto.getRace());
        }
        if (Objects.nonNull(getPlayersDto.getProfession())) {
            selectPlayers.setProfession(getPlayersDto.getProfession());
        }
        if (Objects.nonNull(getPlayersDto.getAfter())) {
            selectPlayers.setAfter(convertLongToLocalDate(getPlayersDto.getAfter()));
        }
        if (Objects.nonNull(getPlayersDto.getBefore())) {
            selectPlayers.setBefore(convertLongToLocalDate(getPlayersDto.getBefore()));
        }
        if (Objects.nonNull(getPlayersDto.getBanned())) {
            selectPlayers.setBanned(getPlayersDto.getBanned());
        }
        if (Objects.nonNull(getPlayersDto.getMinExperience())) {
            selectPlayers.setMinExperience(getPlayersDto.getMinExperience());
        }
        if (Objects.nonNull(getPlayersDto.getMaxExperience())) {
            selectPlayers.setMaxExperience(getPlayersDto.getMaxExperience());
        }
        if (Objects.nonNull(getPlayersDto.getMinLevel())) {
            selectPlayers.setMinLevel(calculateLevel(getPlayersDto.getMinLevel()));
        }
        if (Objects.nonNull(getPlayersDto.getMaxLevel())) {
            selectPlayers.setMaxLevel(calculateLevel(getPlayersDto.getMaxLevel()));
        }
        if (Objects.nonNull(getPlayersDto.getOrder())) {
            selectPlayers.setOrder(getPlayersDto.getOrder());
        }
        if (Objects.nonNull(getPlayersDto.getPageNumber())) {
            selectPlayers.setPageNumber(getPlayersDto.getPageNumber());
        }
        if (Objects.nonNull(getPlayersDto.getPageSize())) {
            selectPlayers.setPageSize(getPlayersDto.getPageSize());
        }

        return selectPlayers;
    }

    public static PlayerDto mapToPlayerDto(Player player) {
        PlayerDto playerDto = new PlayerDto();

        playerDto.setId(player.getId());
        playerDto.setName(player.getName());
        playerDto.setTitle(player.getTitle());
        playerDto.setRace(player.getRace());
        playerDto.setProfession(player.getProfession());
        playerDto.setBirthday(convertLocalDateToMillis(player.getBirthday()));
        playerDto.setBanned(player.getBanned());
        playerDto.setExperience(player.getExperience());
        playerDto.setLevel(player.getLevel());
        playerDto.setUntilNextLevel(player.getUntilNextLevel());

        return playerDto;
    }

    public static AverageValuesDto mapToAverageValuesDto(PlayerDataAverages playerDataAverages) {
        AverageValuesDto averageValuesDto = new AverageValuesDto();
        averageValuesDto.setExperienceAverage(playerDataAverages.getExperienceAverage());
        averageValuesDto.setLevelAverage(playerDataAverages.getLevelAverage());
        return averageValuesDto;
    }
}
