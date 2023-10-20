---
layout: post
title: Build a Project Hack
courses: { csa: {week: 1} }
---


<html>

<head></head>
<link rel="stylesheet" type="text/css" href="images/style.css">
<body>
  <div class="size"></div>
  <div class="color-box">
    <input type="color" id="color-picker" style="border: none; outline: none;">
  </div>
  <button class="button reset-btn" onclick="window.location.reload()"><span>Reset</span></button>
</body>

</html>

<script> // iterates and makes the grid
  const size = document.querySelector('.size');
  const width = 20; // these numbers change the grid size
  const height = 20;
 
  for (let i = 0; i < height; i++) {
    const row = document.createElement('div');
    row.classList.add('row');
    size.appendChild(row);

    for (let j = 0; j < width; j++) {
      const cell = document.createElement('div');
      cell.classList.add('cell');
      row.appendChild(cell);
    }
  }

  const cells = document.querySelectorAll('.cell');
  const colorPicker = document.getElementById('color-picker');
  const colorBox = document.querySelector('.color-box');
  const resetBtn = document.getElementById('reset-button');
  
  cells.forEach(cell => {
    cell.addEventListener('click', () => {
      const selectedColor = colorPicker.value;
      cell.style.backgroundColor = selectedColor;
    });
  });
  
  colorPicker.addEventListener('input', () => {
    const selectedColor = colorPicker.value;
    colorBox.style.backgroundColor = selectedColor;
  });
  
  // this section changes all of the cells back to white
  resetBtn.addEventListener('click', () => {
    cells.forEach(cell => {
      cell.style.backgroundColor = 'white';
    });
  });
  
</script>