## Projet de création de schéma MPM

# Objectifs 

- Créer un docker permettant de lancer une page web qui permet d'a partir d'un tableau a remplir, de generer un png d'un schéma MPM.

# Qu'est ce qu'une MPM 

La MPM ou Méthode des Potentiels et antécédents Métra est une technique d'ordonnancement basée sur la théorie des graphes, visant à optimiser la planification des tâches d'un projet.	


| Tâches | Durée | Antériorité |
|--------------|:-----:|-----------:|
| A	| 2	| -	|
| B	| 4	| - |
| C	| 4	| A	|
| D	| 5	| A,B	|
| E | 6	| C,D |	

docker build -t serveurMPM .
