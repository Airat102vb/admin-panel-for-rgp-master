package com.example.demo.mapper;

import com.example.demo.controller.dto.UpdatePlayerRequest;
import com.example.demo.controller.dto.PutPlayerResponse;
import com.example.demo.repository.entity.Player;

import java.util.Objects;

import static com.example.demo.utils.CommonUtils.*;

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

    public static void toPlayer(UpdatePlayerRequest playerUpdates, Player player) {
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

        player.setLevel(calculateLevel(playerUpdates.getExperience()));
        player.setUntilNextLevel(calculateUntilNextLevel(player.getLevel(), playerUpdates.getExperience()));
    }
}
