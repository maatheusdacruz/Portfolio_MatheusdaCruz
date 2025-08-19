import React, { useEffect, useState } from "react";
import Item from "../components/projectItem" 

function Projects() {
  return (<section class="bg-white dark:bg-gray-900 antialiased">
    <div class="max-w-screen-xl px-4 py-8 mx-auto lg:px-6 sm:py-16 lg:py-24">
      <Item>a</Item>
      <Item>b</Item>
      <Item>c</Item>
    </div>
  </section>);
}

export default Projects;