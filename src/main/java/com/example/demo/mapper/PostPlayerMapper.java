package com.example.demo.mapper;

import com.example.demo.controller.dto.CreatePlayerRequest;
import com.example.demo.repository.entity.Player;

import static com.example.demo.utils.CommonUtils.convertLongToLocalDate;

public class PostPlayerMapper {

    public static Player toPlayer(CreatePlayerRequest createPlayerRequest) {
        Player player = new Player();
        player.setName(createPlayerRequest.getName());
        player.setTitle(createPlayerRequest.getTitle());
        player.setRace(createPlayerRequest.getRace());
        player.setProfession(createPlayerRequest.getProfession());
        player.setBirthday(convertLongToLocalDate(createPlayerRequest.getBirthday()));
        player.setBanned(createPlayerRequest.getBanned());
        player.setExperience(createPlayerRequest.getExperience());

        return player;
    }
}
