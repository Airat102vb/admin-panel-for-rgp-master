package com.example.demo.controller;

import com.example.demo.controller.dto.*;
import com.example.demo.eception.PlayerException;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
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
        return playerService.findPlayers(getPlayersRequest);
    }

    @GetMapping("/count")
    public Integer getPlayersCount(@ModelAttribute GetPlayersRequest getPlayersRequest) {
        return playerService.countPlayers(getPlayersRequest);
    }

    @PostMapping
    public PostPlayerResponse createPlayer(@Valid @RequestBody PostPlayerRequest postPlayerRequest) {
        return playerService.createPlayer(postPlayerRequest);
    }

    @GetMapping("/{id}")
    public GetPlayersResponse getPlayer(@PathVariable("id") @Positive(message = "id должно быть положительным числом") Long id) {
        return playerService.findPlayer(id);
    }

    @PostMapping("/{id}")
    public PutPlayerResponse updatePlayer(@PathVariable Long id, @RequestBody PutPlayerRequest putPlayerRequest) {
        return playerService.updatePlayer(id, putPlayerRequest);
    }

    @DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        playerService.delete(id);
    }

    @ExceptionHandler(PlayerException.NotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void playerNotFoundHandler() {
    }

    @ExceptionHandler({PlayerException.BadRequest.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void badRequestHandler() {
    }
}
