# Projet de création de schéma MPM	

## Prérequis

- GIT
- Docker
- Plus d'1 Go de stockage

### Récupérer les fichier
Afin de faire fonctionner notre application, il vous faut récupérer les fichiers nécessaire à son fonctionnement.
Mettez vous dans un répértoire de votre choix, puis, tappez la commande suivante :

``git clone git@github.com:AllanLegrand/SAE-2.03-Docker-MPM.git``

Normalement, dans votre répértoire, un dossier **SAE-2.03-Docker-MPM** s'est crée, déplacer vous dedans avec la commande

``cd SAE-2.03-Docker-MPM``

Dedans, vous trouverez le fichier README.md, le fichier DockerFile, et un dossier html.

### Créer et lancer l'image 
Restez dans le dossier SAE-2.03-Docker-MPM et exécuter la commande suivante :

``docker build -t mpm .``

Cela peut prendre entre 2 à 5 minutes à s'installer. 
Une fois terminer, tappez la commande suivante :

``docker run --name conteneur_mpm -d -p 8080:80 mpm``

Une fois lancé, démarrer un navigateur de votre choix et tapper dans la barre la ligne suivante :

``localhost:8080``

http://localhost:8080

Une fois le graphe MPM généré, vous pouvez arrêter l'image afin de libérer votre port avec la commande :

``docker stop mpm``

Pour plus d'information sur l'utilisation, c'est ici : 
https://allanlegrand.github.io/SAE-2.03-Docker-MPM/
