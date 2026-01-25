package com.example.demo;

import com.example.demo.controller.PlayerController;
import com.example.demo.controller.dto.*;
import com.example.demo.eception.PlayerException;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import com.example.demo.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PlayerService playerService;

    @Test
    public void getPlayersTest() throws Exception {
        Mockito.when(this.playerService.findPlayers(Mockito.any(GetPlayersRequest.class))).thenReturn(getPlayers());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].title").value("Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].race").value("ELF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].profession").value("CLERIC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].birthday").value(631929600000L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].banned").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].experience").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].level").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].untilNextLevel").value(50))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].title").value("Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].race").value("ELF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].profession").value("CLERIC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].birthday").value(631929600000L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].banned").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].experience").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].level").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.[1].untilNextLevel").value(50));
    }

    @Test
    public void getCountPlayersTest() throws Exception {
        Mockito.when(this.playerService.countPlayers(Mockito.any(GetPlayersRequest.class))).thenReturn(1);

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players/count"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").value(1));
    }

    @Test
    public void createPlayerTest() throws Exception {
        Mockito.when(this.playerService.createPlayer(Mockito.any(PostPlayerRequest.class))).thenReturn(createPostPlayerResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players")
                        .header("Content-type", "application/json;charset=UTF-8")
                        .content("{\"name\":\"asd 67\",\"title\":\"zxc\",\"race\":\"HUMAN\",\"profession\":\"PALADIN\"," +
                                "\"birthday\":1767657600000,\"banned\":false,\"experience\":\"5\"}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.race").value("ELF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profession").value("CLERIC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value(631929600000L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.banned").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.experience").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.untilNextLevel").value(50));
    }

    @Test
    public void getPlayerTest() throws Exception {
        Mockito.when(this.playerService.findPlayer(1L)).thenReturn(createPlayer());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players/1"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.race").value("ELF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profession").value("CLERIC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value(631929600000L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.banned").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.experience").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.untilNextLevel").value(50));
    }

    @Test
    public void updatePlayerTest() throws Exception {
        Mockito.when(this.playerService.updatePlayer(eq(1L), Mockito.any(PutPlayerRequest.class))).thenReturn(createPutPlayerResponse());

        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players/1")
                        .header("Content-type", "application/json;charset=UTF-8")
                        .content("{\"name\":\"asdddddd\",\"title\":\"edited\",\"race\":\"HUMAN\",\"profession\":\"PALADIN\"," +
                                "\"birthday\":1767657600000,\"banned\":false,\"experience\":\"347\"}"))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Name"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Title"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.race").value("ELF"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.profession").value("CLERIC"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.birthday").value(631929600000L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.banned").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.experience").value(150))
                .andExpect(MockMvcResultMatchers.jsonPath("$.level").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.untilNextLevel").value(50));
    }

    @Test
    public void deletePlayerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/rest/players/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getPlayerNegativeTest() throws Exception {
        Mockito.when(this.playerService.findPlayer(eq(1L))).thenThrow(new PlayerException.NotFound());

        mockMvc.perform(MockMvcRequestBuilders.get("/rest/players/1"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    public void createPlayerNegativeTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/rest/players")
                        .header("Content-type", "application/json;charset=UTF-8")
                        .content("{\"name\":\"\""))
                .andDo(print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    private PostPlayerResponse createPostPlayerResponse() {
        PostPlayerResponse postPlayerResponse = new PostPlayerResponse();
        postPlayerResponse.setId(1L);
        postPlayerResponse.setName("Name");
        postPlayerResponse.setTitle("Title");
        postPlayerResponse.setRace(Race.ELF);
        postPlayerResponse.setProfession(Profession.CLERIC);
        postPlayerResponse.setBirthday(631_929_600_000L);
        postPlayerResponse.setBanned(false);
        postPlayerResponse.setLevel(1);
        postPlayerResponse.setExperience(150);
        postPlayerResponse.setUntilNextLevel(50);
        return postPlayerResponse;
    }

    private PutPlayerResponse createPutPlayerResponse() {
        PutPlayerResponse putPlayerResponse = new PutPlayerResponse();
        putPlayerResponse.setId(1L);
        putPlayerResponse.setName("Name");
        putPlayerResponse.setTitle("Title");
        putPlayerResponse.setRace(Race.ELF);
        putPlayerResponse.setProfession(Profession.CLERIC);
        putPlayerResponse.setBirthday(631_929_600_000L);
        putPlayerResponse.setBanned(false);
        putPlayerResponse.setLevel(1);
        putPlayerResponse.setExperience(150);
        putPlayerResponse.setUntilNextLevel(50);
        return putPlayerResponse;
    }

    private GetPlayersResponse createPlayer() {
        GetPlayersResponse playersResponse = new GetPlayersResponse();
        playersResponse.setId(1L);
        playersResponse.setName("Name");
        playersResponse.setTitle("Title");
        playersResponse.setRace(Race.ELF);
        playersResponse.setProfession(Profession.CLERIC);
        playersResponse.setBirthday(631_929_600_000L);
        playersResponse.setBanned(false);
        playersResponse.setLevel(1);
        playersResponse.setExperience(150);
        playersResponse.setUntilNextLevel(50);

        return playersResponse;
    }

    private List<GetPlayersResponse> getPlayers() {
        return List.of(createPlayer(), createPlayer());
    }
}
