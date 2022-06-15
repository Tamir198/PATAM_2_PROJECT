import './App.css';
import { NavLink } from 'react-router-dom';
import { Outlet } from 'react-router-dom';

function App() {
  return (
    <div>
      <nav>
        <NavLink to="/FleerOverview">FleerOverview</NavLink>
        <NavLink to="/Monitoring">Monitoring</NavLink>
        <NavLink to="/Teleoperation">Teleoperation</NavLink>
        <NavLink to="/TimeCapsule">TimeCapsule</NavLink>
      </nav>
      <Outlet />
    </div>
  );
}

export default App;
