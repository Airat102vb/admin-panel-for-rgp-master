package com.example.demo.service;

import com.example.demo.controller.dto.GetPlayersResponse;
import com.example.demo.controller.dto.PostPlayerResponse;
import com.example.demo.controller.dto.PutPlayerResponse;
import com.example.demo.eception.PlayerNotFoundException;
import com.example.demo.mapper.ControllerServiceMapper;
import com.example.demo.mapper.ServiceRepositoryMapper;
import com.example.demo.repository.PlayerRepositoryJpa;
import com.example.demo.repository.entity.Player;
import com.example.demo.service.dto.CreatePlayerDto;
import com.example.demo.service.dto.GetPlayersDto;
import com.example.demo.service.dto.PlayerDto;
import com.example.demo.service.dto.UpdatePlayerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.example.demo.utils.CommonUtils.calculateLevel;
import static com.example.demo.utils.CommonUtils.calculateUntilNextLevel;

@Service
public class PlayerServiceImpl implements PlayerService {

    private PlayerRepositoryJpa playerRepositoryJpa;

    @Autowired
    public PlayerServiceImpl(PlayerRepositoryJpa playerRepositoryJpa) {
        this.playerRepositoryJpa = playerRepositoryJpa;
    }

    @Override
    public PostPlayerResponse createPlayer(CreatePlayerDto createPlayerDto) { // return PlayerDto
        int level = calculateLevel(createPlayerDto.getExperience());
        int untilNextLevel = calculateUntilNextLevel(level, createPlayerDto.getExperience());

        Player newPlayer = ServiceRepositoryMapper.mapToPlayer(createPlayerDto);
        newPlayer.setLevel(level);
        newPlayer.setUntilNextLevel(untilNextLevel);
        Player savedPlayer = playerRepositoryJpa.save(newPlayer);
        PlayerDto playerDto = ServiceRepositoryMapper.mapToPlayerDto(savedPlayer);
        return ControllerServiceMapper.mapToPostPlayerResponse(playerDto);
    }

    @Override
    public List<GetPlayersResponse> findPlayers(GetPlayersDto getPlayersDto) { //TODO return List<PlayerDto>
        Specification<Player> searchSpec = UserSpecification.of(getPlayersDto);

        Pageable pageable = PageRequest.of(
                getPlayersDto.getPageNumber(),
                getPlayersDto.getPageSize(),
                Sort.by(getPlayersDto.getOrder().getFieldName()).ascending()
        );

        Page<Player> players = playerRepositoryJpa.findAll(searchSpec, pageable);

        return players
                .stream()
                .map(ServiceRepositoryMapper::mapToPlayerDto)
                .map(ControllerServiceMapper::mapToGetPlayersResponse)
                .toList();
    }

    @Override
    public Long countPlayers(GetPlayersDto getPlayersDto) {
        Specification<Player> searchSpec = UserSpecification.of(getPlayersDto);
        return playerRepositoryJpa.count(searchSpec);
    }

    @Override
    public GetPlayersResponse findPlayer(Long id) { // return PlayerDto
        Optional<Player> player = playerRepositoryJpa.findById(id);

        if (player.isEmpty()) {
            throw new PlayerNotFoundException();
        }

        return ControllerServiceMapper
                .mapToGetPlayersResponse(ServiceRepositoryMapper.mapToPlayerDto(player.get()));
    }

    @Override
    public PutPlayerResponse updatePlayer(Long id, UpdatePlayerDto updatePlayerDto) { // return PlayerDto
        Player player = playerRepositoryJpa.findById(id).orElseThrow(PlayerNotFoundException::new);
        ServiceRepositoryMapper.mapToPlayer(updatePlayerDto, player); //TODO это не маппер
        PlayerDto playerDto = ServiceRepositoryMapper.mapToPlayerDto((playerRepositoryJpa.save(player))); //вынести ave  отдельнуюпеременную
        return ControllerServiceMapper.mapToPutPlayerResponse(playerDto);
    }

    @Override
    public void delete(Long id) {
        playerRepositoryJpa.deleteById(id);
    }
}
