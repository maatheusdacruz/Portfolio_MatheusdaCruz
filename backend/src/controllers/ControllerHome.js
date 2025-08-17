function Home(req, res) {
  res.json({ message: 'Hello from the backend!' });
}

module.exports = { Home };