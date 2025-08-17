
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { useEffect, useState } from 'react';
import { Menu } from './components/menu';
import { Footer } from './components/footer';
import Home from './pages/Home';
import About from './pages/About';
import Projects from './pages/Projects';

// URL do backend hospedado (troque pela sua URL real do Render)
const BACKEND_URL = "https://portfolio-matheusdacruz.onrender.com/";

// Função utilitária para consumir qualquer rota do backend
function fetchBackend(route) {
  return fetch(`${BACKEND_URL}${route}`).then(res => res.json());
}


function App() {
  const [mensagem, setMensagem] = useState('');

  useEffect(() => {
    // Exemplo: consumindo a rota raiz do backend
    fetchBackend('/')
      .then((data) => setMensagem(data.message))
      .catch(() => setMensagem('Erro ao conectar com o backend.'));
  }, []);

  return (
    <>
      <Menu/>
      <div style={{ padding: '1rem', color: 'green', fontWeight: 'bold' }}>
        Backend diz: {mensagem}
      </div>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/projects" element={<Projects />} />
          <Route path="/about" element={<About />} />
        </Routes>
      </BrowserRouter>
      <Footer/>
    </>
  );
}

export default App;

