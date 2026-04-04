package com.example.demo;

import com.example.demo.controller.dto.CreatePlayerRequest;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import com.example.demo.mapper.ControllerServiceMapper;
import com.example.demo.repository.PlayerRepositoryJpa;
import com.example.demo.repository.entity.Player;
import com.example.demo.service.PlayerService;
import com.example.demo.service.PlayerServiceImpl;
import com.example.demo.service.dto.PlayerDto;
import com.example.demo.utils.CommonUtils;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;

@SpringBootTest
@ActiveProfiles("test")
public class PlayerServiceTest {

    private static final Long birthDay = 631_929_600_000L;

    @Autowired
    PlayerService playerService;

    @MockitoBean
    PlayerRepositoryJpa playerRepositoryJpa;

    @Autowired
    private PlayerServiceImpl playerServiceImpl;

    @Test
    public void createPlayerTest() {
        Player player = getPlayer();
        Mockito.when(playerRepositoryJpa.save(Mockito.any(Player.class))).thenReturn(player);

        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setBirthday(birthDay);
        PlayerDto playerDto = playerServiceImpl
                .createPlayer(ControllerServiceMapper.mapToCreatePlayerDto(createPlayerRequest));

        Assertions.assertThat(playerDto).isNotNull();
        Assertions.assertThat(playerDto.getId()).isEqualTo(player.getId());
        Assertions.assertThat(playerDto.getName()).isEqualTo(player.getName());
        Assertions.assertThat(playerDto.getTitle()).isEqualTo(player.getTitle());
        Assertions.assertThat(playerDto.getRace()).isEqualTo(player.getRace());
        Assertions.assertThat(playerDto.getProfession()).isEqualTo(player.getProfession());
        Assertions.assertThat(playerDto.getBirthday()).isEqualTo(CommonUtils.convertLocalDateToMillis(player.getBirthday()));
        Assertions.assertThat(playerDto.getBanned()).isEqualTo(player.getBanned());
        Assertions.assertThat(playerDto.getLevel()).isEqualTo(player.getLevel());
        Assertions.assertThat(playerDto.getExperience()).isEqualTo(player.getExperience());
        Assertions.assertThat(playerDto.getUntilNextLevel()).isEqualTo(player.getUntilNextLevel());
    }

    @Test
    public void findPlayerTest() {
        Player player = getPlayer();
        Mockito.when(playerRepositoryJpa.findById(eq(1L))).thenReturn(Optional.of(player));

        PlayerDto playerDto = playerService.findPlayer(1L);

        Assertions.assertThat(playerDto).isNotNull();
        Assertions.assertThat(playerDto.getId()).isEqualTo(player.getId());
        Assertions.assertThat(playerDto.getName()).isEqualTo(player.getName());
        Assertions.assertThat(playerDto.getTitle()).isEqualTo(player.getTitle());
        Assertions.assertThat(playerDto.getRace()).isEqualTo(player.getRace());
        Assertions.assertThat(playerDto.getProfession()).isEqualTo(player.getProfession());
        Assertions.assertThat(playerDto.getBirthday()).isEqualTo(CommonUtils.convertLocalDateToMillis(player.getBirthday()));
        Assertions.assertThat(playerDto.getBanned()).isEqualTo(player.getBanned());
        Assertions.assertThat(playerDto.getLevel()).isEqualTo(player.getLevel());
        Assertions.assertThat(playerDto.getExperience()).isEqualTo(player.getExperience());
        Assertions.assertThat(playerDto.getUntilNextLevel()).isEqualTo(player.getUntilNextLevel());
    }

    private Player getPlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Name" + UUID.randomUUID().toString().substring(0, 5));
        player.setTitle("Title" + UUID.randomUUID().toString().substring(0, 5));
        player.setRace(Race.ELF);
        player.setProfession(Profession.CLERIC);
        player.setBirthday(CommonUtils.convertLongToLocalDate(birthDay));
        player.setBanned(false);
        player.setLevel(1);
        player.setExperience(150);
        player.setUntilNextLevel(50);
        return player;
    }
}
