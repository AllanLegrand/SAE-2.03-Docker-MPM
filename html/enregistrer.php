<?php
if ($_SERVER["REQUEST_METHOD"] === "POST") 
{
  $donneCSV = $_POST['csvData'];

  $fichier = fopen('tableau.csv', 'w');

  if ($fichier) 
  {
    fwrite($fichier, $donneCSV);
    fclose($fichier);
    echo "Le fichier CSV a été enregistré avec succès.";
  } 
  else 
  {
    echo "Erreur lors de l'ouverture du fichier.";
  }
}

exec("chmod 777 MPM.java");
exec("javac MPM.java", $resultat, $status);
if ($status === 0) 
{
  exec("chmod 777 MPM.class");
  exec("java MPM test");

  echo "Fichier Java compilé. Redirection en cours...";
} 
else 
{
  echo "Erreur lors de la compilation du fichier Java.$compileOutput $compileStatus ";
}
?>