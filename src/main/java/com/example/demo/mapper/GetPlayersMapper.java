package com.example.demo.mapper;

import com.example.demo.controller.dto.GetPlayersRequest;
import com.example.demo.controller.dto.GetPlayersResponse;
import com.example.demo.controller.dto.PostPlayerResponse;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.SelectPlayers;

import static com.example.demo.utils.CommonUtils.calculateLevel;
import static com.example.demo.utils.CommonUtils.convertLongToLocalDate;

public class GetPlayersMapper {

    public static SelectPlayers toSelectPlayersEntity(GetPlayersRequest getPlayersRequest) {
        SelectPlayers selectPlayers = new SelectPlayers();
        selectPlayers.setName(getPlayersRequest.getName());
        selectPlayers.setTitle(getPlayersRequest.getTitle());
        selectPlayers.setRace(getPlayersRequest.getRace());
        selectPlayers.setProfession(getPlayersRequest.getProfession());
        selectPlayers.setAfter(convertLongToLocalDate(getPlayersRequest.getAfter()));
        selectPlayers.setBefore(convertLongToLocalDate(getPlayersRequest.getBefore()));
        selectPlayers.setBanned(getPlayersRequest.getBanned());
        selectPlayers.setMinExperience(getPlayersRequest.getMinExperience());
        selectPlayers.setMaxExperience(getPlayersRequest.getMaxExperience());
        selectPlayers.setMinLevel(calculateLevel(getPlayersRequest.getMinLevel()));
        selectPlayers.setMaxLevel(calculateLevel(getPlayersRequest.getMaxLevel()));

        return selectPlayers;
    }

    public static PostPlayerResponse toPostPlayerResponse(Player newPlayer) {
        PostPlayerResponse postPlayerResponse = new PostPlayerResponse();
        postPlayerResponse.setId(newPlayer.getId());
        postPlayerResponse.setName(newPlayer.getName());
        postPlayerResponse.setTitle(newPlayer.getTitle());
        postPlayerResponse.setRace(newPlayer.getRace());
        postPlayerResponse.setProfession(newPlayer.getProfession());
        postPlayerResponse.setBirthday(newPlayer.getBirthday().toEpochDay());
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
        getPlayersResponse.setBirthday(player.getBirthday().toEpochDay());
        getPlayersResponse.setBanned(player.getBanned());
        getPlayersResponse.setExperience(player.getExperience());
        getPlayersResponse.setLevel(player.getLevel());
        getPlayersResponse.setUntilNextLevel(player.getUntilNextLevel());

        return getPlayersResponse;
    }
}
