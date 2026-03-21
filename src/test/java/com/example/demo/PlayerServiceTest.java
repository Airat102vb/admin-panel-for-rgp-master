package com.example.demo;

import com.example.demo.controller.dto.CreatePlayerRequest;
import com.example.demo.controller.dto.GetPlayersResponse;
import com.example.demo.controller.dto.PostPlayerResponse;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import com.example.demo.mapper.ControllerServiceMapper;
import com.example.demo.repository.PlayerRepository;
import com.example.demo.repository.entity.Player;
import com.example.demo.service.PlayerService;
import com.example.demo.service.PlayerServiceImpl;
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
    PlayerRepository playerRepository;

    @Autowired
    private PlayerServiceImpl playerServiceImpl;

    @Test
    public void createPlayerTest() {
        Player player = getPlayer();
        Mockito.when(playerRepository.insert(Mockito.any(Player.class))).thenReturn(player);

        CreatePlayerRequest createPlayerRequest = new CreatePlayerRequest();
        createPlayerRequest.setBirthday(birthDay);
        PostPlayerResponse postPlayerResponse = playerServiceImpl
                .createPlayer(ControllerServiceMapper.mapToCreatePlayerDto(createPlayerRequest));

        Assertions.assertThat(postPlayerResponse).isNotNull();
        Assertions.assertThat(postPlayerResponse.getId()).isEqualTo(player.getId());
        Assertions.assertThat(postPlayerResponse.getName()).isEqualTo(player.getName());
        Assertions.assertThat(postPlayerResponse.getTitle()).isEqualTo(player.getTitle());
        Assertions.assertThat(postPlayerResponse.getRace()).isEqualTo(player.getRace());
        Assertions.assertThat(postPlayerResponse.getProfession()).isEqualTo(player.getProfession());
        Assertions.assertThat(postPlayerResponse.getBirthday()).isEqualTo(CommonUtils.convertLocalDateToMillis(player.getBirthday()));
        Assertions.assertThat(postPlayerResponse.getBanned()).isEqualTo(player.getBanned());
        Assertions.assertThat(postPlayerResponse.getLevel()).isEqualTo(player.getLevel());
        Assertions.assertThat(postPlayerResponse.getExperience()).isEqualTo(player.getExperience());
        Assertions.assertThat(postPlayerResponse.getUntilNextLevel()).isEqualTo(player.getUntilNextLevel());
    }

    @Test
    public void findPlayerTest() {
        Player player = getPlayer();
        Mockito.when(playerRepository.findById(eq(1L))).thenReturn(Optional.of(player));

        GetPlayersResponse playersResponse = playerService.findPlayer(1L);

        Assertions.assertThat(playersResponse).isNotNull();
        Assertions.assertThat(playersResponse.getId()).isEqualTo(player.getId());
        Assertions.assertThat(playersResponse.getName()).isEqualTo(player.getName());
        Assertions.assertThat(playersResponse.getTitle()).isEqualTo(player.getTitle());
        Assertions.assertThat(playersResponse.getRace()).isEqualTo(player.getRace());
        Assertions.assertThat(playersResponse.getProfession()).isEqualTo(player.getProfession());
        Assertions.assertThat(playersResponse.getBirthday()).isEqualTo(CommonUtils.convertLocalDateToMillis(player.getBirthday()));
        Assertions.assertThat(playersResponse.getBanned()).isEqualTo(player.getBanned());
        Assertions.assertThat(playersResponse.getLevel()).isEqualTo(player.getLevel());
        Assertions.assertThat(playersResponse.getExperience()).isEqualTo(player.getExperience());
        Assertions.assertThat(playersResponse.getUntilNextLevel()).isEqualTo(player.getUntilNextLevel());
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
