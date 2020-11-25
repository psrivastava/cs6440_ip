import logo from './logo.svg';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import HomePage from './components/HomePage';
import { CareCenterPage } from './components/CareCenterPage';
import { LoginPage } from './components/LoginPage';
import { GroupPage } from './components/GroupPage';
import { ReportPage } from './components/ReportPage';
import { DashboardPage } from './components/DashboardPage';
import PrivateRoute from './app/PrivateRoute';

function App() {
  return (
    <div className="App">
      <Router>
        <Switch>
          <PrivateRoute path="/" exact>
            <HomePage />
          </PrivateRoute>
          <PrivateRoute path="/home" exact>
            <HomePage />
          </PrivateRoute>
          <PrivateRoute path="/group" exact>
            <GroupPage />
          </PrivateRoute>
          <PrivateRoute path="/center" exact>
            <CareCenterPage />
          </PrivateRoute>
          <PrivateRoute path="/report" exact>
            <ReportPage />
          </PrivateRoute>
          <PrivateRoute path="/dash" exact>
            <DashboardPage />
          </PrivateRoute>
          {/*
          <Route exact path="/" component={HomePage} />
          <Route path="/home" component={HomePage} />
          <Route path="/group" component={GroupPage} />
          <Route path="/center" component={CareCenterPage} />
          <Route path="/report" component={ReportPage} />
          <Route path="/dash" component={DashboardPage} />
          */}
          <Route path="/logout" component={LoginPage} />
          <Route path="/login" component={LoginPage} />
        </Switch>
      </Router>
    </div>
  );
}

export default App;
