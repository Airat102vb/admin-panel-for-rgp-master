package com.example.demo.repository;

import com.example.demo.repository.entity.Player;
import com.example.demo.repository.entity.SelectPlayers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static com.example.demo.repository.PlayerRepositorySql.sqlSelectAllPlayers;

@Repository
public class PlayerRepositoryImpl implements PlayerRepository { //интерфейс

    private final JdbcClient jdbcClient;

    @Autowired
    public PlayerRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public Player insert(Player player) {
        return jdbcClient.sql("""
                        INSERT INTO player (name, title, race, profession, birthday, banned, experience, level, until_next_level) 
                        VALUES (:name, :title, :race, :profession, :birthday, :banned, :experience, :level, :untilNextLevel)
                        RETURNING *
                        """)
                .param("name", player.getName())
                .param("title", player.getTitle())
                .param("race", player.getRace().name())
                .param("profession", player.getProfession().name())
                .param("birthday", player.getBirthday())
                .param("banned", player.getBanned())
                .param("experience", player.getExperience())
                .param("level", player.getLevel())
                .param("untilNextLevel", player.getUntilNextLevel())
                .query(Player.class)
                .single();// TODO simpleJdbcInsert + select
    }

    public Optional<Player> findById(Long id) {
        return jdbcClient.sql("SELECT * FROM player WHERE id = ?")
                .param(id)
                .query(Player.class)
                .optional();
    }

    public Player update(Player player) {
        return jdbcClient.sql("""
                        UPDATE player
                            SET
                                name = COALESCE(:name, name),
                                title = COALESCE(:title, title),
                                race = COALESCE(:race, race),
                                profession = COALESCE(:profession, profession),
                                birthday = COALESCE(:birthday, birthday),
                                banned = COALESCE(:banned, banned),
                                experience = COALESCE(:experience, experience),
                                level = COALESCE(:level, level),
                                until_next_level = COALESCE(:untilNextLevel, until_next_level)
                                WHERE id = :id
                        RETURNING *
                        """)
                .param("id", player.getId())
                .param("name", player.getName())
                .param("title", player.getTitle())
                .param("race", player.getRace().name())
                .param("profession", player.getProfession().name())
                .param("birthday", player.getBirthday())
                .param("banned", player.getBanned())
                .param("experience", player.getExperience())
                .param("level", player.getLevel())
                .param("untilNextLevel", player.getUntilNextLevel())
                .query(Player.class)
                .single();
    }

    public void deleteById(Long id) {
        jdbcClient.sql("DELETE FROM player WHERE id = ?")
                .param(id)
                .update();
    }

    public Long count(SelectPlayers selectPlayers) {
        return (long) jdbcClient.sql(getQueryFilters(selectPlayers))
                .query(Player.class)
                .list()
                .size();
    }

    public List<Player> findAll(SelectPlayers selectPlayers) {
        String query = getQueryFilters(selectPlayers);

        return jdbcClient.sql(query + String.format(" ORDER BY %s ASC LIMIT %s OFFSET %s",
                        selectPlayers.getOrder().getFieldName(),
                        selectPlayers.getPageSize(),
                        selectPlayers.getPageNumber() * selectPlayers.getPageSize()))
                .query(Player.class)
                .list();
    }

    private static String getQueryFilters(SelectPlayers selectPlayers) {
        List<String> filters = new ArrayList<>();

        if (Objects.nonNull(selectPlayers.getName())) {
            filters.add("name ILIKE '%%%s%%'".formatted(selectPlayers.getName()));
        }
        if (Objects.nonNull(selectPlayers.getRace())) {
            filters.add("race = %s ".formatted(selectPlayers.getRace()));
        }
        if (Objects.nonNull(selectPlayers.getProfession())) {
            filters.add("profession = %s ".formatted(selectPlayers.getProfession()));
        }
        if (Objects.nonNull(selectPlayers.getBanned())) {
            filters.add("banned = %s ".formatted(selectPlayers.getBanned()));
        }
        if (Objects.nonNull(selectPlayers.getTitle())) {
            filters.add("title ILIKE '%%%s%%' ".formatted(selectPlayers.getTitle()));
        }
        if (Objects.nonNull(selectPlayers.getAfter())) {
            filters.add("birthday >= %s ".formatted(selectPlayers.getAfter()));
        }
        if (Objects.nonNull(selectPlayers.getBefore())) {
            filters.add("birthday <= %s ".formatted(selectPlayers.getBefore()));
        }
        if (Objects.nonNull(selectPlayers.getMinLevel())) {
            filters.add("level >= %s ".formatted(selectPlayers.getMinLevel()));
        }
        if (Objects.nonNull(selectPlayers.getMaxLevel())) {
            filters.add("level <= %s ".formatted(selectPlayers.getMaxLevel()));
        }
        if (Objects.nonNull(selectPlayers.getMinExperience())) {
            filters.add("experience >= %s ".formatted(selectPlayers.getMinExperience()));
        }
        if (Objects.nonNull(selectPlayers.getMaxExperience())) {
            filters.add("experience <= %s ".formatted(selectPlayers.getMaxExperience()));
        }

        return filters.isEmpty() ?
                sqlSelectAllPlayers :
                sqlSelectAllPlayers + " WHERE " + String.join(" AND ", filters);
    }
}
