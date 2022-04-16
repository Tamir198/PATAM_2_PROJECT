import './App.css';
import { NavLink } from 'react-router-dom';
import { Outlet } from 'react-router-dom';

function App() {
  return (
    <div>
      <nav>
        <NavLink to="/page1">First page</NavLink>
        <NavLink to="/page2">Second page</NavLink>
        <NavLink to="/page3">Third page</NavLink>
      </nav>
      <Outlet />
    </div>
  );
}

export default App;
