package com.example.demo.controller;

import com.example.demo.dto.PlayerFilterDto;
import com.example.demo.dto.PutPlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.service.PlayerService;
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

    // TODO spring validation обновление img_4
    // TODO img_3 img_5 - 404 400
    
    // TODO untilNextTime (readme.md)

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    // TODO на каждый слой, свой тип/сущность/дто (реквест/респонс)
    @GetMapping
    public List<Player> getPlayers(@ModelAttribute PlayerFilterDto playerFilterDto) {
        return playerService.findPlayers(playerFilterDto);
    }

    @GetMapping("/count")
    public Integer getPlayersCount(@ModelAttribute PlayerFilterDto playerFilterDto) {
        return playerService.countPlayers(playerFilterDto);
    }

    @PostMapping
    public Player createPlayer(@Valid @RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("/{id}")
    public Player getPlayer(@PathVariable("id") @Positive(message = "id должно быть положительным числом") Long id) {
        return playerService.findPlayer(id);
    }

    @PostMapping("/{id}")
    public Player putPlayer(@PathVariable Long id, @RequestBody PutPlayerDto playerUpdates) {
        return playerService.updatePlayer(id, playerUpdates);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
    }
}
