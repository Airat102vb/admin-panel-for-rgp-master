package com.example.demo.repository;

import com.example.demo.repository.entity.Player;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Map;

@Repository
public class SimplePlayerJdbcInsert extends SimpleJdbcInsert {
    private final JdbcClient jdbcClient;

    public SimplePlayerJdbcInsert(DataSource dataSource, JdbcClient jdbcClient) {
        super(dataSource);
        withTableName("player");
        usingGeneratedKeyColumns("id");
        this.jdbcClient = jdbcClient;
    }

    public Player insert(Player player) {
        Map<String, Object> parameters =  Map.of(
                "name", player.getName(),
                "title", player.getTitle(),
                "race", player.getRace().name(),
                "profession", player.getProfession().name(),
                "birthday", player.getBirthday(),
                "banned", player.getBanned(),
                "experience", player.getExperience(),
                "level", player.getLevel(),
                "until_next_level", player.getUntilNextLevel()
        );

        long id = executeAndReturnKey(parameters).longValue();

        return jdbcClient.sql("""
                        SELECT id, name, title, race, profession, birthday, banned, experience, level, until_next_level 
                        FROM player 
                        WHERE id = ?
                        """)
                .param(id)
                .query(Player.class)
                .single();
    }
}
