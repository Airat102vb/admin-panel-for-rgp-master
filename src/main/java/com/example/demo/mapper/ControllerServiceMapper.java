package com.example.demo.mapper;

import com.example.demo.controller.dto.*;
import com.example.demo.service.dto.*;

public class ControllerServiceMapper {

    public static UpdatePlayerDto mapToUpdatePlayerDto(UpdatePlayerRequest updatePlayerRequest) {
        UpdatePlayerDto updatePlayerDto = new UpdatePlayerDto();

        updatePlayerDto.setName(updatePlayerRequest.getName());
        updatePlayerDto.setTitle(updatePlayerRequest.getTitle());
        updatePlayerDto.setRace(updatePlayerRequest.getRace());
        updatePlayerDto.setProfession(updatePlayerRequest.getProfession());
        updatePlayerDto.setBirthday(updatePlayerRequest.getBirthday());
        updatePlayerDto.setBanned(updatePlayerRequest.getBanned());
        updatePlayerDto.setExperience(updatePlayerRequest.getExperience());

        return updatePlayerDto;
    }

    public static GetPlayersResponse mapToGetPlayersResponse(PlayerDto playerDto) {
        GetPlayersResponse getPlayersResponse = new GetPlayersResponse();
        getPlayersResponse.setId(playerDto.getId());
        getPlayersResponse.setName(playerDto.getName());
        getPlayersResponse.setTitle(playerDto.getTitle());
        getPlayersResponse.setRace(playerDto.getRace());
        getPlayersResponse.setProfession(playerDto.getProfession());
        getPlayersResponse.setBirthday(playerDto.getBirthday());
        getPlayersResponse.setBanned(playerDto.getBanned());
        getPlayersResponse.setExperience(playerDto.getExperience());
        getPlayersResponse.setLevel(playerDto.getLevel());
        getPlayersResponse.setUntilNextLevel(playerDto.getUntilNextLevel());

        return getPlayersResponse;
    }

    public static CreatePlayerDto mapToCreatePlayerDto(CreatePlayerRequest updatePlayerRequest) {
        CreatePlayerDto createPlayerDto = new CreatePlayerDto();

        createPlayerDto.setName(updatePlayerRequest.getName());
        createPlayerDto.setTitle(updatePlayerRequest.getTitle());
        createPlayerDto.setRace(updatePlayerRequest.getRace());
        createPlayerDto.setProfession(updatePlayerRequest.getProfession());
        createPlayerDto.setBirthday(updatePlayerRequest.getBirthday());
        createPlayerDto.setBanned(updatePlayerRequest.getBanned());
        createPlayerDto.setExperience(updatePlayerRequest.getExperience());

        return createPlayerDto;
    }

    public static GetPlayersDto mapToGetPlayersDto(GetPlayersRequest getPlayersRequest) {
        GetPlayersDto getPlayersDto = new GetPlayersDto();

        getPlayersDto.setName(getPlayersRequest.getName());
        getPlayersDto.setTitle(getPlayersRequest.getTitle());
        getPlayersDto.setRace(getPlayersRequest.getRace());
        getPlayersDto.setProfession(getPlayersRequest.getProfession());
        getPlayersDto.setAfter(getPlayersRequest.getAfter());
        getPlayersDto.setBefore(getPlayersRequest.getBefore());
        getPlayersDto.setBanned(getPlayersRequest.getBanned());
        getPlayersDto.setMinExperience(getPlayersRequest.getMinExperience());
        getPlayersDto.setMaxExperience(getPlayersRequest.getMaxExperience());
        getPlayersDto.setMinLevel(getPlayersRequest.getMinLevel());
        getPlayersDto.setMaxLevel(getPlayersRequest.getMaxLevel());
        getPlayersDto.setOrder(getPlayersRequest.getOrder());
        getPlayersDto.setPageNumber(getPlayersRequest.getPageNumber());
        getPlayersDto.setPageSize(getPlayersRequest.getPageSize());

        return getPlayersDto;
    }

    public static PutPlayerResponse mapToPutPlayerResponse(PlayerDto playerDto) {
        PutPlayerResponse putPlayerResponse = new PutPlayerResponse();
        putPlayerResponse.setId(playerDto.getId());
        putPlayerResponse.setName(playerDto.getName());
        putPlayerResponse.setTitle(playerDto.getTitle());
        putPlayerResponse.setRace(playerDto.getRace());
        putPlayerResponse.setProfession(playerDto.getProfession());
        putPlayerResponse.setBirthday(playerDto.getBirthday());
        putPlayerResponse.setBanned(playerDto.getBanned());
        putPlayerResponse.setExperience(playerDto.getExperience());
        putPlayerResponse.setLevel(playerDto.getLevel());
        putPlayerResponse.setUntilNextLevel(playerDto.getUntilNextLevel());

        return putPlayerResponse;
    }

    public static PostPlayerResponse mapToPostPlayerResponse(PlayerDto playerDto) {
        PostPlayerResponse postPlayerResponse = new PostPlayerResponse();
        postPlayerResponse.setId(playerDto.getId());
        postPlayerResponse.setName(playerDto.getName());
        postPlayerResponse.setTitle(playerDto.getTitle());
        postPlayerResponse.setRace(playerDto.getRace());
        postPlayerResponse.setProfession(playerDto.getProfession());
        postPlayerResponse.setBirthday(playerDto.getBirthday());
        postPlayerResponse.setBanned(playerDto.getBanned());
        postPlayerResponse.setExperience(playerDto.getExperience());
        postPlayerResponse.setLevel(playerDto.getLevel());
        postPlayerResponse.setUntilNextLevel(playerDto.getUntilNextLevel());

        return postPlayerResponse;
    }

    public static GetAveragesResponse mapToGetAveragesResponse(AverageValuesDto averageValuesDto) {
        GetAveragesResponse getAveragesResponse = new GetAveragesResponse();
        getAveragesResponse.setExperienceAverage(averageValuesDto.getExperienceAverage());
        getAveragesResponse.setLevelAverage(averageValuesDto.getLevelAverage());
        return getAveragesResponse;
    }
}
