# Projet de création de schéma MPM

## Objectifs 

- Créer un docker permettant de lancer une page web qui permet d'a partir d'un tableau a remplir, de generer un png d'un schéma MPM.

## Qu'est ce qu'une MPM 

### PRÉSENTATION DE LA MPM	

La MPM ou Méthode des Potentiels et antécédents Métra est une technique d'ordonnancement basée sur la théorie des graphes, visant à optimiser la planification des tâches d'un projet.	


### MÉTHODOLOGIE DE CONSTRUCTION D'UN RÉSEAU MPM	

Le recours à la méthode des potentiels Métra suppose qu'aient été identifiées préalablement les différentes tâches nécessaires à la réalisation du projet, leur durée et leurs relations d'antériorité.


| Tâches | Durée | Antériorité |
|--------------|:-----:|-----------:|
| A	| 2	| -	|
| B	| 4	| - |
| C	| 4	| A	|
| D	| 5	| A,B	|
| E | 6	| C,D |	

### Conventions de base d'un graphe MPM	

La méthode des potentiels Métra permet de représenter l'ensemble de ces tâches sur un graphe orienté, à partir duquel il sera possible d'identifier leurs dates au plus et au plus tard et de calculer leurs marges.	
	Un graphe orienté est un réseau composé d'une entrée et d'une sortie, ainsi que de points (appelés "sommets") reliés entre eux par des flèches (appelées "arcs").	

Les principales conventions d'un réseau MPM sont les suivantes :	
- chaque tâche est représentée par un sommet	
- les contraintes de succession sont symbolisées par les arcs	
- chaque tâche est renseignée sur sa durée ainsi que sur la date à laquelle elle peut commencer au plus tôt ("date au plus tôt") et au plus tard ("date au plus tard") pour respecter le délai optimal de réalisation du projet.	
- le graphe commence et termine sur 2 sommets, respectivement appelés "Début" et "Fin" symbolisant les début et fin des opérations (mais ne correspondant pas une tâche).	

### Construction d'un graphe MPM	

Sur la base des conventions précédentes, la construction d'un graphe MPM ne pose pas de difficulté particulière, mais doit être réalisée avec méthode. La démarche la plus appropriée consiste à procéder par "niveau" :	
- déterminer les tâches sans antécédent (tâches de niveau 1) et les relier au sommet "Début"	
- identifier ensuite les tâches de niveau 2, c'est-à-dire celles dont les antécédents sont exclusivement du niveau 1 et les positionner sur le graphique en les reliant à leurs antécédents,	
- … continuer ainsi, jusqu'à ce que toutes les tâches aient pu être positionnées entre elles et relier celles n'ayant pas de descendant au sommet "Fin".	

### Lecture d'un graphe MPM	
Le graphe se lit de gauche à droite (du sommet "DÉBUT" à celui de "FIN").	
	Chaque sommet symbolise une tâche.	
	Les arcs entre les sommets traduisent uniquement les relations d'antériorité des tâches. D'un même sommet peuvent donc partir plusieurs flèches, lorsque la tâche correspondante est immédiatement antérieure à plusieurs tâches indépendantes.	

Chaque sommet est identifié par une cartouche où sont précisés : le "nom de la tâche", la "durée de cette tâche", les dates de "début au plus tôt" et de "début au plus tard" de cette tâche.	

La date au plus tôt d'un réseau MPM correspond à la date à laquelle une tâche peut commencer au plus tôt.	
Elle s'obtient très simplement en ajoutant à la date au plus tôt de la tâche précédente la durée de la tâche en question :	
Date au plus tôt tâche T = Date au plus tôt tâche S + Durée tâche S	

Lorsque plusieurs arcs arrivent à un même sommet (c'est-à-dire que plusieurs tâches sont immédiatement antérieures à la tâche considérée), il convient, d'effectuer ce calcul pour toutes les tâches précédant la tâche en question et de retenir comme "date au plus tôt" de cette dernière le maximum des valeurs ainsi trouvée (en effet, cette tâche ne pourra vraiment débuter que lorsque toutes les tâches qui lui sont immédiatement antérieures auront été terminées). La formule précédente devient donc :	
Date au plus tôt tâche T = Max. (Date plus tôt tâches S + Durée tâches S)	
Dans cette formule, "S" représente l'ensemble des tâches immédiatement antérieures à "T"	

La détermination des dates au plus tôt des différentes sommets se fait donc par calculs successifs, à partir du sommet "Début" (dont, par convention, la date au plus tôt est fixée à 0).	
La durée minimale du projet correspond donc à la date au plus tôt du sommet "Fin".	



La date au plus tard d'un réseau MPM correspond à la date à laquelle une tâche doit être exécutée au plus tard pour ne pas remettre en cause la durée optimale totale du projet.	
	Elle s'obtient en retirant de la date au plus tard de la tâche qui lui succède sa propre durée :	
	Date au plus tard tâche S = Date au plus tard tâche T - durée tâche S	

Lorsque plusieurs arcs partent d'un même sommet (i.e. que plusieurs tâches succèdent à une tâche donnée), il convient de faire ce calcul pour toutes les tâches succédant à la tâche en question et de retenir comme "date au plus tard" de de cette dernière le minimum des valeurs ainsi trouvées :	
Date au plus tard tâche S = Min. (date au plus tard tâches T - durée tâche S)	
Dans cette formule, "T" représente l'ensemble des tâches immédiatement postérieures à "S"	

La détermination des dates au plus tard des différentes tâches se fait donc à rebours du graphe, par calculs successifs, en partant du sommet "Fin" (pour lequel, par convention, on considère que la date au plus tard est égale à sa date au plus tôt).	

