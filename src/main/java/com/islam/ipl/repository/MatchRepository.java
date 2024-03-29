package com.islam.ipl.repository;

import com.islam.ipl.model.Match;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


import java.time.LocalDate;
import java.util.List;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List<Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2, Pageable pageable);

    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) AND m.date between :dateStart and :dateEnd order by date desc")
    List<Match> getMatchesByTeamBetweenDates(@Param("teamName")String teamName, @Param("dateStart") LocalDate startDate, @Param("dateEnd") LocalDate endDate);

    default List<Match> findMatchesForTeamByYear(String teamName, int year){
        LocalDate startDate = LocalDate.of(year, 1,1);
        LocalDate endDate = LocalDate.of(year, 12,31);
        return getMatchesByTeamBetweenDates(teamName, startDate, endDate);
    }

//    List<Match> getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(String teamName1, LocalDate date1,  LocalDate date2, String teamName2, LocalDate date3,  LocalDate date4);
//
//    default List<Match> findMatchesForTeamByYear(String teamName, int year){
//        LocalDate startDate = LocalDate.of(year, 1,1);
//        LocalDate endDate = LocalDate.of(year, 12,31);
//        return getByTeam1AndDateBetweenOrTeam2AndDateBetweenOrderByDateDesc(teamName, startDate, endDate,teamName, startDate, endDate);
//    }

    default List<Match> findLatestMatchesbyTeam(String teamName, int count) {
        return getByTeam1OrTeam2OrderByDateDesc(teamName, teamName, PageRequest.of(0, count));
    }
}
