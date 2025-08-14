import React, { useEffect, useState } from "react";

function Projects() {
  const [projects, setProjects] = useState([]);

  useEffect(() => {
    fetch("https://api.github.com/repos/maatheusdacruz/Portfolio_MatheusdaCruz/contents/")
      .then((res) => res.json())
      .then(async (data) => {
        const folders = data.filter(item => item.type === "dir");
        // Para cada pasta, busca o package.json e o último commit
        const details = await Promise.all(folders.map(async (folder) => {
        const infoRes = await fetch(`https://api.github.com/repos/maatheusdacruz/Portfolio_MatheusdaCruz/contents/${folder.name}/info.json`);
        let language = "";
        let image = "";
        let description = "";
        if (infoRes.ok) {
        const infoData = await infoRes.json();
        const infoJson = JSON.parse(atob(infoData.content));
        language = infoJson.language || "";
        image = infoJson.image || "";
        description = infoJson.description || "";
        }
          return {
            name: folder.name,
            html_url: folder.html_url,
            description,
            image,
            language,
          };
        }));
        setProjects(details);
      });
  }, []);

  return (
    <div>
      <h2>Projetos dentro do repositório</h2>
      <ul>
        {projects.map((proj) => (
          <li key={proj.name} style={{marginBottom: "2rem"}}>
            <strong>{proj.name}</strong>
            <p>{proj.description}</p>
            <p>Linguagem: {proj.language}</p>
            <img src={proj.image} alt={`${proj.name} thumbnail`} style={{width: "100px", height: "100px"}} />
            <a href={proj.html_url} target="_blank" rel="noopener noreferrer">
              Ver no GitHub
            </a>
          </li>
        ))}
      </ul>
    </div>
  );
}

export default Projects;