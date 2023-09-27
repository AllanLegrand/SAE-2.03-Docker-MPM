function ajouterLigne() 
{
  var grille = document.getElementById("grille");
  var row = grille.insertRow(grille.rows.length);

  for (var i = 0; i < grille.rows[0].cells.length; i++) 
  {
    var cell = row.insertCell(i);
    cell.innerHTML = "";
    cell.contentEditable = true;
  }
}

function telecharger() 
{
  var grille = document.getElementById("grille");
  var DonneeCSV = "";

  for (var i = 0; i < grille.rows.length; i++) 
  {
    var rowData = [];

    for (var j = 0; j < grille.rows[i].cells.length; j++) 
    {
      rowData.push(grille.rows[i].cells[j].innerText);
    }

    DonneeCSV += rowData.join(",") + "\n";
  }

  var formData = new FormData();
  formData.append('csvData', DonneeCSV);

  var request = new XMLHttpRequest();
  request.open('POST', 'enregistrer.php', true);
  request.onreadystatechange = function () 
  {
    if (request.readyState === 4 && request.status === 200) 
    {
      console.log(request.responseText);
    }
  };
  
  request.send(formData);
  
  //Pause de 5 secondes le temps que le fichier soit créer
  setTimeout(function() 
  {
    var lien = document.createElement('a');
    lien.href = 'MPM.svg';
    lien.download = 'MPM.svg'; 
    
    lien.style.display = 'none';
    document.body.appendChild(lien);
  
    lien.click();
  
    document.body.removeChild(lien);
  }, 5000);
  
}
