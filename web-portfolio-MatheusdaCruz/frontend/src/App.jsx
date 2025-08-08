import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Menu } from './components/menu';
import { Footer } from './components/footer';
import Home from './pages/Home';
import About from './pages/About';

function App() {
  const [mensagem, setMensagem] = useState('');

  useEffect(() => {
    fetch('/api/oi')
      .then((res) => res.json())
      .then((data) => setMensagem(data.message));
  }, []);

  return (
    <>
    <Menu/>
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/about" element={<About />} />
      </Routes>
    </BrowserRouter>
    <Footer/>
    </>
    
  );
}

export default App;

