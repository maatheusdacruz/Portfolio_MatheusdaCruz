
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Menu } from './components/menu';
import { Footer } from './components/footer';
import Home from './pages/Home';
import About from './pages/About';
import Projects from './pages/Projects';




function App() {

  return (
    <>
      <Menu/>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="projects" element={<Projects />} />
          <Route path="about" element={<About />} />
        </Routes>
      </BrowserRouter>
      <Footer/>
    </>
  );
}

export default App;

