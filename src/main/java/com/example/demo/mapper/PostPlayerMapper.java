package com.example.demo.mapper;

import com.example.demo.controller.dto.PostPlayerRequest;
import com.example.demo.repository.entity.Player;

import static com.example.demo.utils.CommonUtils.convertLongToLocalDate;

public class PostPlayerMapper {

    public static Player toPlayer(PostPlayerRequest postPlayerRequest) {
        Player player = new Player();
        player.setName(postPlayerRequest.getName());
        player.setTitle(postPlayerRequest.getTitle());
        player.setRace(postPlayerRequest.getRace());
        player.setProfession(postPlayerRequest.getProfession());
        player.setBirthday(convertLongToLocalDate(postPlayerRequest.getBirthday()));
        player.setBanned(postPlayerRequest.getBanned());
        player.setExperience(postPlayerRequest.getExperience());

        return player;
    }
}
