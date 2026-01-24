package com.example.demo.service;

import com.example.demo.dto.PlayerFilterDto;
import com.example.demo.dto.PutPlayerDto;
import com.example.demo.entity.Player;
import com.example.demo.entity.Profession;
import com.example.demo.entity.Race;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PlayerServiceImpl implements PlayerService {

    // TODO перенсти в репозиторий
    private AtomicLong idGenerator = new AtomicLong(1);
    private final Map<Long, Player> playersDb = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        for (int i = 0; i < 5000; i++) {
            Player player = new Player();
            player.setName("Name" + i);
            player.setTitle("Title" + i);
            player.setRace(Race.ELF);
            player.setProfession(Profession.DRUID);
            player.setBirthday(253465656L + i);
            player.setExperience(i);

            savePlayer(player);
        }
    }

    @Override
    public Player savePlayer(Player player) {
        long id = idGenerator.getAndIncrement();
        player.setId(id);
        playersDb.put(id, player);
        return player;
    }

    @Override
    public List<Player> findPlayers(PlayerFilterDto playerFilterDto) {

        // TODO Comparator по названию
        // TODO сортировка в репозиторий, филтрация

        // TODO стримы, разбение дороже для джава

        // TODO филтровать раньше сравнение
        // TODO убрать дублирование с каунт

        Collection<Player> players = playersDb
                .values()
                .stream()
                .sorted(switch (playerFilterDto.getOrder().getFieldName()) {
                    case "name" -> Comparator.comparing(Player::getName);
                    case "experience" -> Comparator.comparing(Player::getExperience);
                    case "birthday" -> Comparator.comparing(Player::getBirthday);
                    case "level" -> Comparator.comparing(Player::getLevel);
                    default -> Comparator.comparing(Player::getId);
                })
                .toList();

        // TODO добавить все поля в фильтр
        List<Player> filteredResult = players
                .stream()
                .filter(player -> Objects.isNull(playerFilterDto.getName()) || player.getName().contains(playerFilterDto.getName()))
                .filter(player -> Objects.isNull(playerFilterDto.getTitle()) || player.getTitle().contains(playerFilterDto.getTitle()))
                .toList();

        List<Player> pagedResult = filteredResult
                .stream()
                .skip(playerFilterDto.getPageNumber() == 0 ? 0 : (long) playerFilterDto.getPageNumber() * playerFilterDto.getPageSize())
                .limit((long) playerFilterDto.getPageSize())
                .toList();

        return pagedResult;
    }

    @Override
    public Integer countPlayers(PlayerFilterDto playerFilterDto) {
        Collection<Player> players = playersDb
                .values()
                .stream()
                .sorted(Comparator.comparing(order -> playerFilterDto.getOrder()))
                .toList();

        List<Player> filteredResult = players
                .stream()
                .filter(player -> Objects.isNull(playerFilterDto.getName()) || player.getName().contains(playerFilterDto.getName()))
                .filter(player -> Objects.isNull(playerFilterDto.getTitle()) || player.getTitle().contains(playerFilterDto.getTitle()))
                .toList();

        return filteredResult.size();
    }

    @Override
    public Player findPlayer(Long id) {
        return playersDb.get(id);
    }

    @Override
    public Player updatePlayer(Long id, PutPlayerDto playerUpdates) {
        Player player = playersDb.get(id);

        player.setName(playerUpdates.getName());
        player.setTitle(playerUpdates.getTitle());
        player.setBirthday(playerUpdates.getBirthday());
        player.setRace(playerUpdates.getRace());
        player.setProfession(playerUpdates.getProfession());
        player.setBanned(playerUpdates.getBanned());
        player.setExperience(playerUpdates.getExperience());

        return player;
    }

    @Override
    public void delete(Long id) {
        playersDb.remove(id);
    }
}
