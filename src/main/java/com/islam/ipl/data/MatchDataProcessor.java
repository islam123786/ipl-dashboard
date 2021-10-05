package com.islam.ipl.data;

import com.islam.ipl.model.Match;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {

    private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

    @Override
    public Match process(final MatchInput matchInput) throws Exception {
        final Match match = new Match();
        match.setId(Long.parseLong(matchInput.getId()));
        match.setCity(matchInput.getCity());
        match.setDate(LocalDate.parse(matchInput.getDate()));
        match.setPlayerOfMatch(matchInput.getPlayer_of_match());
        match.setVenue(match.getVenue());

        String firstInningBattingTeam;
        String secondInningBattingTeam;
        if(matchInput.getToss_decision().equalsIgnoreCase("bat")){
            if(matchInput.getToss_winner().equalsIgnoreCase(matchInput.getTeam1())){
                firstInningBattingTeam = matchInput.getTeam1();
                secondInningBattingTeam = matchInput.getTeam2();
            }else {
                secondInningBattingTeam = matchInput.getTeam1();
                firstInningBattingTeam = matchInput.getTeam2();
            }
        } else {
            if(matchInput.getToss_winner().equalsIgnoreCase(matchInput.getTeam1())){
                secondInningBattingTeam = matchInput.getTeam1();
                firstInningBattingTeam = matchInput.getTeam2();
            }else {
                firstInningBattingTeam = matchInput.getTeam1();
                secondInningBattingTeam = matchInput.getTeam2();
            }
        }

        match.setTeam1(firstInningBattingTeam);
        match.setTeam2(secondInningBattingTeam);
        match.setTossWinner(matchInput.getToss_winner());
        match.setTossDecision(matchInput.getToss_decision());
        match.setMatchWinner(matchInput.getWinner());
        match.setResult(matchInput.getResult());
        match.setResultMargin(matchInput.getResult_margin());
        match.setUmpire1(matchInput.getUmpire1());
        match.setUmpire2(matchInput.getUmpire2());


        log.info("Converting (" + matchInput + ") into (" + match + ")");
        return match;
    }

}
