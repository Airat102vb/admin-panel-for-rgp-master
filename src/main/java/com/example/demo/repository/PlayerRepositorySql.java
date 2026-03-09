package com.example.demo.repository;

public interface PlayerRepositorySql {
    String sqlSelectAllPlayers = """
            SELECT id, name, title, race, profession, birthday, banned, experience, level, until_next_level FROM player
            """;
}
