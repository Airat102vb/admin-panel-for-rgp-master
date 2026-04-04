package com.example.demo.controller;

import com.example.demo.controller.dto.*;
import com.example.demo.mapper.ControllerServiceMapper;
import com.example.demo.service.PlayerService;
import com.example.demo.service.dto.PlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.util.List;

@RestController
@RequestMapping("/rest/players")
@Validated
public class PlayerController {

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping
    public List<GetPlayersResponse> getPlayers(@ModelAttribute GetPlayersRequest getPlayersRequest) {
        List<PlayerDto> playerDtos = playerService.findPlayers(ControllerServiceMapper.mapToGetPlayersDto(getPlayersRequest));
        return playerDtos
                .stream()
                .map(ControllerServiceMapper::mapToGetPlayersResponse)
                .toList();
    }

    @GetMapping("/count")
    public Long getPlayersCount(@ModelAttribute GetPlayersRequest getPlayersRequest) {
        return playerService.countPlayers(ControllerServiceMapper.mapToGetPlayersDto(getPlayersRequest));
    }

    @PostMapping("/")
    public PostPlayerResponse createPlayer(@Valid @RequestBody CreatePlayerRequest createPlayerRequest) {
        PlayerDto playerDto = playerService.createPlayer(ControllerServiceMapper.mapToCreatePlayerDto(createPlayerRequest));
        return ControllerServiceMapper.mapToPostPlayerResponse(playerDto);
    }

    @GetMapping("/{id}")
    public GetPlayersResponse getPlayer(@PathVariable("id") @Positive(message = "id должно быть положительным числом") Long id) {
        PlayerDto playerDto = playerService.findPlayer(id);
        return ControllerServiceMapper.mapToGetPlayersResponse(playerDto);
    }

    @PostMapping("/{id}")
    public PutPlayerResponse updatePlayer(@PathVariable Long id, @RequestBody UpdatePlayerRequest updatePlayerRequest) {
        PlayerDto playerDto = playerService.updatePlayer(id, ControllerServiceMapper.mapToUpdatePlayerDto(updatePlayerRequest));
        return ControllerServiceMapper.mapToPutPlayerResponse(playerDto);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
    }
}
