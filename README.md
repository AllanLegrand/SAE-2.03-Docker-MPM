# Projet de création de schéma MPM

## Objectifs 

- Créer un docker permettant de lancer une page web qui permet d'a partir d'un tableau a remplir, de generer un png d'un schéma MPM.

## Qu'est ce qu'une MPM 

La MPM ou Méthode des Potentiels et antécédents Métra est une technique d'ordonnancement basée sur la théorie des graphes, visant à optimiser la planification des tâches d'un projet.	

Le recours à la méthode des potentiels Métra suppose qu'aient été identifiées préalablement les différentes tâches nécessaires à la réalisation du projet, leur durée et leurs relations d'antériorité.


| Tâches | Durée | Antériorité |
|--------------|:-----:|-----------:|
| A	| 2	| -	|
| B	| 4	| - |
| C	| 4	| A	|
| D	| 5	| A,B	|
| E | 6	| C,D |	

La méthode des potentiels Métra permet de représenter l'ensemble de ces tâches sur un graphe orienté, à partir duquel il sera possible d'identifier leurs dates au plus et au plus tard et de calculer leurs marges.	
	Un graphe orienté est un réseau composé d'une entrée et d'une sortie, ainsi que de points (appelés "sommets") reliés entre eux par des flèches (appelées "arcs").	

Les principales conventions d'un réseau MPM sont les suivantes :	
- chaque tâche est représentée par un sommet	
- les contraintes de succession sont symbolisées par les arcs	
- chaque tâche est renseignée sur sa durée ainsi que sur la date à laquelle elle peut commencer au plus tôt ("date au plus tôt") et au plus tard ("date au plus tard") pour respecter le délai optimal de réalisation du projet.	
- le graphe commence et termine sur 2 sommets, respectivement appelés "Début" et "Fin" symbolisant les début et fin des opérations (mais ne correspondant pas une tâche).	

docker build -t serveurMPM .
