import React from "react";
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Routes, Route } from "react-router-dom";

import App from './App';
import './index.css';
import FleerOverview from "./pages/FleerOverview";
import Monitoring from "./pages/Monitoring";
import Teleoperation from "./pages/Teleoperation";
import TimeCapsule from "./pages/TimeCapsule";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route path='FleerOverview' element={<FleerOverview/>} />
          <Route path='Monitoring' element={<Monitoring/>} />
          <Route path='Teleoperation' element={<Teleoperation/>} />
          <Route path='TimeCapsule' element={<TimeCapsule/>} />
          <Route path="*" element={<p>Page not found</p>} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>

);

