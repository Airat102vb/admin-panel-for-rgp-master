package com.example.demo.configuration;

import com.example.demo.controller.dto.PostPlayerRequest;
import com.example.demo.filter.Profession;
import com.example.demo.filter.Race;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class InitialDataConfig {

    @Bean
    @Profile("!test")
    public Object generateData(@Autowired PlayerService playerService) {
        for (int i = 0; i < 500; i++) {
            PostPlayerRequest player = new PostPlayerRequest();
            player.setName("Name" + i);
            player.setTitle("Title" + i);
            player.setRace(Race.ELF);
            player.setProfession(Profession.DRUID);
            player.setBirthday(631_929_600_000L + 86_400_000L * i);
            player.setExperience(i);

            playerService.createPlayer(player);
        }
        return new Object();
    }
}
