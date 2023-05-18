# Projet de création de schéma MPM	

## Prérequis

- GIT
- Docker

### Récupérer les fichier
Afin de faire fonctionner notre application, il vous faut récupérer les fichiers nécessaire à son fonctionnement.
Mettez vous dans un répértoire de votre choix, puis, tappez la commande suivante :

``git clone git@github.com:AllanLegrand/SAE-2.03-Docker-MPM.git``

Normalement, dans votre répértoire, un dossier **SAE-2.03-Docker-MPM** s'est crée, déplacer vous dedans avec la commande

``cd SAE-2.03-Docker-MPM``

Dedans, vous trouverez le fichier README.md, le fichier DockerFile, et un dossier html.

### Créer et lancer l'image 
Restez dans le dossier SAE-2.03-Docker-MPM et exécuter la commande suivante :

``docker build -t <nom_image> .``

Cela peut prendre entre 2 à 5 minutes à s'installer. 
Une fois terminer, tappez la commande suivante :

``docker run --name <nom_du_conteneur> -d -p <port-hôte>:80 <nom_image>``

Une fois lancé, démarrer un navigateur de votre choix et tapper dans la barre la ligne suivante :

``localhost:<port_hôte>``

http://localhost/

Une fois le graphe MPM généré, vous pouvez arrêter l'image afin de libérer votre port avec la commande :

``docker stop <nom_image>``

*Note : Bien évidement, <nom_image>, <nom_du_conteneur> et <port-hôte> sont à remplacer par le nom / port que vous voulez.*

Pour plus d'information sur l'utilisation, c'est ici : 
https://allanlegrand.github.io/SAE-2.03-Docker-MPM/
