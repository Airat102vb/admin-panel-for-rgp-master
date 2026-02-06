package com.example.demo.repository;

import com.example.demo.eception.PlayerException;
import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.SelectPlayers;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Stream;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository {

    private final AtomicLong idGenerator = new AtomicLong(1);
    private final Map<Long, Player> playersDb = new ConcurrentHashMap<>();

    @Override
    public Player savePlayer(Player player) {
        long id = idGenerator.getAndIncrement();
        player.setId(id);
        playersDb.put(id, player);
        return player;
    }

    @Override
    public List<Player> selectPlayers(SelectPlayers selectPlayers) {

        Comparator<Player> comparator = switch (selectPlayers.getOrder().getFieldName()) {
            case "name" -> Comparator.comparing(Player::getName);
            case "experience" -> Comparator.comparing(Player::getExperience);
            case "birthday" -> Comparator.comparing(Player::getBirthday);
            case "level" -> Comparator.comparing(Player::getLevel);
            default -> Comparator.comparing(Player::getId);
        };

        comparator = comparator.thenComparing(Player::getId);

        return filter(playersDb.values(), selectPlayers)
                .sorted(comparator)
                .skip((long) selectPlayers.getPageNumber() * selectPlayers.getPageSize())
                .limit(selectPlayers.getPageSize())
                .toList();
    }

    @Override
    public Integer countPlayers(SelectPlayers selectPlayers) {
        return filter(playersDb.values(), selectPlayers).toList().size();
    }

    @Override
    public Player selectPlayer(Long id) {
        return playersDb.get(id);
    }

    @Override
    public Player updatePlayer(Long id, Player playerUpdates) {
        Player player = playersDb.get(id);

        if (Objects.isNull(player)) {
            throw new PlayerException.NotFound();
        }

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
            player.setBirthday(playerUpdates.getBirthday());
        }
        if (Objects.nonNull(playerUpdates.getBanned())) {
            player.setBanned(playerUpdates.getBanned());
        }
        if (Objects.nonNull(playerUpdates.getExperience())) {
            player.setExperience(playerUpdates.getExperience());
        }
        if (Objects.nonNull(playerUpdates.getLevel())) {
            player.setLevel(playerUpdates.getLevel());
        }
        if (Objects.nonNull(playerUpdates.getUntilNextLevel())) {
            player.setUntilNextLevel(playerUpdates.getUntilNextLevel());
        }

        return player;
    }

    @Override
    public void deletePlayer(Long id) {
        playersDb.remove(id);
    }

    private Stream<Player> filter(Collection<Player> players, SelectPlayers selectPlayers) {
        return players
                .stream()
                .filter(player -> Objects.isNull(selectPlayers.getName()) || player.getName().contains(selectPlayers.getName()))
                .filter(player -> Objects.isNull(selectPlayers.getTitle()) || player.getTitle().contains(selectPlayers.getTitle()))
                .filter(player -> Objects.isNull(selectPlayers.getRace()) || player.getRace() == selectPlayers.getRace())
                .filter(player -> Objects.isNull(selectPlayers.getProfession()) || player.getProfession() == selectPlayers.getProfession())
                .filter(player -> Objects.isNull(selectPlayers.getAfter()) || player.getBirthday().isAfter(selectPlayers.getAfter()))
                .filter(player -> Objects.isNull(selectPlayers.getBefore()) || player.getBirthday().isBefore(selectPlayers.getBefore()))
                .filter(player -> Objects.isNull(selectPlayers.getBanned()) || player.getBanned() == selectPlayers.getBanned())
                .filter(player -> Objects.isNull(selectPlayers.getMinExperience()) || player.getExperience() <= selectPlayers.getMinExperience())
                .filter(player -> Objects.isNull(selectPlayers.getMaxExperience()) || player.getExperience() >= selectPlayers.getMaxExperience())
                .filter(player -> Objects.isNull(selectPlayers.getMinLevel()) || player.getLevel() >= selectPlayers.getMinLevel())
                .filter(player -> Objects.isNull(selectPlayers.getMaxLevel()) || player.getLevel() <= selectPlayers.getMaxLevel());
    }
}
