import './TeamPage.scss';
import { React, useEffect, useState } from 'react';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';
import { useParams } from 'react-router-dom';
import { PieChart } from 'react-minimal-pie-chart';
import { Link } from 'react-router-dom';

export const TeamPage = () => {

  const [team, setTeam] = useState({matches: []});
  const {teamName} = useParams();
  
  useEffect(() => {
    const fetchTeam =  async () => {
        const response = await fetch(`http://localhost:9090/team/${teamName}`);
        const data = await response.json();
        setTeam(data);
        console.log(data);
      };
      fetchTeam();
    },[teamName]
  );

  if(!team || !team.teamName) return <h1>Team not Found</h1>

  return (
    <div className="TeamPage">
      <div className="team-name-section"><h1 className="team-name">{team.teamName}</h1></div>
      <div className="win-loss-section">
        Win/Losses
        <PieChart  data={[
          { title: 'Losses', value: team.totalMatches-team.totalWins, color: '#a34d5d' },
            { title: 'Wins', value: team.totalWins, color: '#4da375' },          
          ]}
        />
      </div>      
      <div className="match-detail-section">
        <h3>Latest Matches</h3>
        <MatchDetailCard teamName={team.teamName} match={team.matches[0]} />
      </div>
      {team.matches.slice(1).map(match =><MatchSmallCard teamName={team.teamName} match={match} /> )}
      <div className="more-link"><Link to={`/team/${teamName}/matches/${process.env.REACT_APP_DATA_END_YEAR}`}>More {'>'}</Link></div>
    </div>
  );
}