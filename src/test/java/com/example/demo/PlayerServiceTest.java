package com.example.demo;

import com.example.demo.controller.dto.GetPlayersResponse;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.entity.Player;
import com.example.demo.service.PlayerService;
import com.example.demo.utils.CommonUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class PlayerServiceTest {

    private static final Long birthDay = 631_929_600_000L;

    @Autowired
    PlayerService playerService;

    @Autowired
    PlayerRepository playerRepository;

    @Test
    public void findPlayerTest() {
        Player player = new Player();
        player.setName("Name" + UUID.randomUUID().toString().substring(0, 5));
        player.setTitle("Title" + UUID.randomUUID().toString().substring(0, 5));
        player.setRace(Race.ELF);
        player.setProfession(Profession.CLERIC);
        player.setBirthday(CommonUtils.convertLongToLocalDate(birthDay));
        player.setBanned(false);
        player.setLevel(1);
        player.setExperience(150);
        player.setUntilNextLevel(50);
        Long id = playerRepository.savePlayer(player).getId();

        GetPlayersResponse playersResponse = playerService.findPlayer(id);
        Assertions.assertThat(playersResponse).isNotNull();
        Assertions.assertThat(playersResponse.getId()).isEqualTo(id);
        Assertions.assertThat(playersResponse.getName()).isEqualTo(player.getName());
        Assertions.assertThat(playersResponse.getTitle()).isEqualTo(player.getTitle());
        Assertions.assertThat(playersResponse.getRace()).isEqualTo(Race.ELF);
        Assertions.assertThat(playersResponse.getProfession()).isEqualTo(Profession.CLERIC);
        Assertions.assertThat(playersResponse.getBirthday()).isEqualTo(birthDay);
        Assertions.assertThat(playersResponse.getBanned()).isEqualTo(false);
        Assertions.assertThat(playersResponse.getLevel()).isEqualTo(1);
        Assertions.assertThat(playersResponse.getExperience()).isEqualTo(150);
        Assertions.assertThat(playersResponse.getUntilNextLevel()).isEqualTo(50);
    }
}
