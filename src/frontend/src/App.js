import './App.scss';
import { TeamPage } from './pages/TeamPage';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import { MatchPage } from './pages/MatchPage';
import { HomePage } from './pages/HomePage';

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
        <Route path="/team/:teamName/matches/:year">
          <MatchPage />
        </Route> 
        <Route path="/team/:teamName">
          <TeamPage />
        </Route>   
        <Route path="/">
          <HomePage/>
        </Route>       
        </Switch>               
      </Router>      
    </div>
  );
}

export default App;
