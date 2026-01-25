package com.example.demo.mapper;

import com.example.demo.controller.dto.PutPlayerRequest;
import com.example.demo.controller.dto.PutPlayerResponse;
import com.example.demo.repository.entity.Player;

import java.util.Objects;

import static com.example.demo.utils.CommonUtils.convertLongToLocalDate;

public class PutPlayerMapper {

    public static PutPlayerResponse toPutPlayerResponse(Player player) {
        PutPlayerResponse putPlayerResponse = new PutPlayerResponse();
        putPlayerResponse.setId(player.getId());
        putPlayerResponse.setName(player.getName());
        putPlayerResponse.setTitle(player.getTitle());
        putPlayerResponse.setRace(player.getRace());
        putPlayerResponse.setProfession(player.getProfession());
        putPlayerResponse.setBirthday(player.getBirthday().toEpochDay());
        putPlayerResponse.setBanned(player.getBanned());
        putPlayerResponse.setExperience(player.getExperience());
        putPlayerResponse.setLevel(player.getLevel());
        putPlayerResponse.setUntilNextLevel(player.getUntilNextLevel());

        return putPlayerResponse;
    }

    public static Player toPlayer(PutPlayerRequest playerUpdates) {
        Player player = new Player();

        if (Objects.nonNull(playerUpdates.getName())) {
            player.setName(playerUpdates.getName());
        }

        if (Objects.nonNull(playerUpdates.getTitle())) {
            player.setTitle(playerUpdates.getTitle());
        }

        if (Objects.nonNull(playerUpdates.getRace())) {
            player.setRace(playerUpdates.getRace());
        }

        if (Objects.nonNull(playerUpdates.getProfession())) {
            player.setProfession(playerUpdates.getProfession());
        }

        if (Objects.nonNull(playerUpdates.getBirthday())) {
            player.setBirthday(convertLongToLocalDate(playerUpdates.getBirthday()));
        }

        if (Objects.nonNull(playerUpdates.getBanned())) {
            player.setBanned(playerUpdates.getBanned());
        }

        if (Objects.nonNull(playerUpdates.getExperience())) {
            player.setExperience(playerUpdates.getExperience());
        }

        return player;
    }
}
