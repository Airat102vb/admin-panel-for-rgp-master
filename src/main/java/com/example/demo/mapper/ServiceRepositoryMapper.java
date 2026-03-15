package com.example.demo.mapper;

import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.SelectPlayers;
import com.example.demo.service.dto.CreatePlayerDto;
import com.example.demo.service.dto.GetPlayersDto;
import com.example.demo.service.dto.PlayerDto;
import com.example.demo.service.dto.UpdatePlayerDto;

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

    public static void mapToPlayer(UpdatePlayerDto updatePlayerDto, Player player) {
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
}
