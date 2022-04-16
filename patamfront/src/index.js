import React from "react";
import ReactDOM from 'react-dom/client'
import { BrowserRouter, Routes, Route } from "react-router-dom";

import App from './App';
import './index.css';
import Page1 from "./pages/Page1";
import Page2 from "./pages/Page2";
import Page3 from "./pages/Page3";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<App />}>
          <Route path='page1' element={<Page1/>} />
          <Route path='page2' element={<Page2/>} />
          <Route path='page3' element={<Page3/>} />
          <Route path="*" element={<p>Page not found</p>} />
        </Route>
      </Routes>
    </BrowserRouter>
  </React.StrictMode>

);

