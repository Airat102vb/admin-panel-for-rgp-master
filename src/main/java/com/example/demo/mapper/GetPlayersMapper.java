package com.example.demo.mapper;

import com.example.demo.controller.dto.GetPlayersRequest;
import com.example.demo.controller.dto.GetPlayersResponse;
import com.example.demo.controller.dto.PostPlayerResponse;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.SelectPlayers;

import java.util.Objects;

import static com.example.demo.utils.CommonUtils.*;

public class GetPlayersMapper {

    public static SelectPlayers toSelectPlayersEntity(GetPlayersRequest getPlayersRequest) {
        SelectPlayers selectPlayers = new SelectPlayers();

        if (Objects.nonNull(getPlayersRequest.getName())) {
            selectPlayers.setName(getPlayersRequest.getName());
        }
        if (Objects.nonNull(getPlayersRequest.getTitle())) {
            selectPlayers.setTitle(getPlayersRequest.getTitle());
        }
        if (Objects.nonNull(getPlayersRequest.getRace())) {
            selectPlayers.setRace(getPlayersRequest.getRace());
        }
        if (Objects.nonNull(getPlayersRequest.getProfession())) {
            selectPlayers.setProfession(getPlayersRequest.getProfession());
        }
        if (Objects.nonNull(getPlayersRequest.getAfter())) {
            selectPlayers.setAfter(convertLongToLocalDate(getPlayersRequest.getAfter()));
        }
        if (Objects.nonNull(getPlayersRequest.getBefore())) {
            selectPlayers.setBefore(convertLongToLocalDate(getPlayersRequest.getBefore()));
        }
        if (Objects.nonNull(getPlayersRequest.getBanned())) {
            selectPlayers.setBanned(getPlayersRequest.getBanned());
        }
        if (Objects.nonNull(getPlayersRequest.getMinExperience())) {
            selectPlayers.setMinExperience(getPlayersRequest.getMinExperience());
        }
        if (Objects.nonNull(getPlayersRequest.getMaxExperience())) {
            selectPlayers.setMaxExperience(getPlayersRequest.getMaxExperience());
        }
        if (Objects.nonNull(getPlayersRequest.getMinLevel())) {
            selectPlayers.setMinLevel(calculateLevel(getPlayersRequest.getMinLevel()));
        }
        if (Objects.nonNull(getPlayersRequest.getMaxLevel())) {
            selectPlayers.setMaxLevel(calculateLevel(getPlayersRequest.getMaxLevel()));
        }
        if (Objects.nonNull(getPlayersRequest.getOrder())) {
            selectPlayers.setOrder(getPlayersRequest.getOrder());
        }
        if (Objects.nonNull(getPlayersRequest.getPageNumber())) {
            selectPlayers.setPageNumber(getPlayersRequest.getPageNumber());
        }
        if (Objects.nonNull(getPlayersRequest.getPageSize())) {
            selectPlayers.setPageSize(getPlayersRequest.getPageSize());
        }

        return selectPlayers;
    }

    public static PostPlayerResponse toPostPlayerResponse(Player newPlayer) {
        PostPlayerResponse postPlayerResponse = new PostPlayerResponse();
        postPlayerResponse.setId(newPlayer.getId());
        postPlayerResponse.setName(newPlayer.getName());
        postPlayerResponse.setTitle(newPlayer.getTitle());
        postPlayerResponse.setRace(newPlayer.getRace());
        postPlayerResponse.setProfession(newPlayer.getProfession());
        postPlayerResponse.setBirthday(convertLocalDateToMillis(newPlayer.getBirthday()));
        postPlayerResponse.setBanned(newPlayer.getBanned());
        postPlayerResponse.setExperience(newPlayer.getExperience());
        postPlayerResponse.setLevel(newPlayer.getLevel());
        postPlayerResponse.setUntilNextLevel(newPlayer.getUntilNextLevel());

        return postPlayerResponse;
    }

    public static GetPlayersResponse toGetPlayerResponse(Player player) {
        GetPlayersResponse getPlayersResponse = new GetPlayersResponse();
        getPlayersResponse.setId(player.getId());
        getPlayersResponse.setName(player.getName());
        getPlayersResponse.setTitle(player.getTitle());
        getPlayersResponse.setRace(player.getRace());
        getPlayersResponse.setProfession(player.getProfession());
        getPlayersResponse.setBirthday(convertLocalDateToMillis(player.getBirthday()));
        getPlayersResponse.setBanned(player.getBanned());
        getPlayersResponse.setExperience(player.getExperience());
        getPlayersResponse.setLevel(player.getLevel());
        getPlayersResponse.setUntilNextLevel(player.getUntilNextLevel());

        return getPlayersResponse;
    }

    public static Player toPlayerEntity(GetPlayersRequest getPlayersRequest) {
        Player player = new Player();

        if (Objects.nonNull(getPlayersRequest.getName())) {
            player.setName(getPlayersRequest.getName());
        }
        if (Objects.nonNull(getPlayersRequest.getTitle())) {
            player.setTitle(getPlayersRequest.getTitle());
        }
        if (Objects.nonNull(getPlayersRequest.getRace())) {
            player.setRace(getPlayersRequest.getRace());
        }
        if (Objects.nonNull(getPlayersRequest.getProfession())) {
            player.setProfession(getPlayersRequest.getProfession());
        }
//        if (Objects.nonNull(getPlayersRequest.getAfter())) {
//            player.setAfter(convertLongToLocalDate(getPlayersRequest.getAfter()));
//        }
//        if (Objects.nonNull(getPlayersRequest.getBefore())) {
//            player.setBefore(convertLongToLocalDate(getPlayersRequest.getBefore()));
//        }
        if (Objects.nonNull(getPlayersRequest.getBanned())) {
            player.setBanned(getPlayersRequest.getBanned());
        }
//        if (Objects.nonNull(getPlayersRequest.getMinExperience())) {
//            player.setMinExperience(getPlayersRequest.getMinExperience());
//        }
//        if (Objects.nonNull(getPlayersRequest.getMaxExperience())) {
//            player.setMaxExperience(getPlayersRequest.getMaxExperience());
//        }
//        if (Objects.nonNull(getPlayersRequest.getMinLevel())) {
//            player.setMinLevel(calculateLevel(getPlayersRequest.getMinLevel()));
//        }
//        if (Objects.nonNull(getPlayersRequest.getMaxLevel())) {
//            player.setMaxLevel(calculateLevel(getPlayersRequest.getMaxLevel()));
//        }

        return player;
    }
}
