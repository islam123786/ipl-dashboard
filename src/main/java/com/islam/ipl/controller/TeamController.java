package com.islam.ipl.controller;

import com.islam.ipl.model.Match;
import com.islam.ipl.model.Team;
import com.islam.ipl.repository.MatchRepository;
import com.islam.ipl.repository.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.List;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private MatchRepository matchRepository;

//    No Reactive API
//    @GetMapping("/team/{teamName}")
//    public Team getTeam(@PathVariable String teamName) {
//        Team team = teamRepository.findByTeamName(teamName);
//        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));
//        return team;
//    }
//
//    @GetMapping("/team/{teamName}/matches")
//    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
//        return matchRepository.findMatchesForTeamByYear(teamName, year);
//    }

    @GetMapping("/team/{teamName}")
    public Mono<Team> getTeam(@PathVariable String teamName) {
        Team team = teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesbyTeam(teamName, 4));
        return Mono.just(team).log();
    }

    @GetMapping("/team/{teamName}/matches")
    public Flux<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year){
        List<Match> matches = matchRepository.findMatchesForTeamByYear(teamName, year);
        return Flux.fromIterable(matches).log();
    }
}
