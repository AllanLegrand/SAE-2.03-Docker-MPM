# Projet de création de schéma MPM	

## Prerequis

- GIT
- Docker

### Récupérer les fichier
Pour que notre application fonctionne, il est important de récupérer les fichiers nécessaire à son fonctionnement.
Mettez vous dans un répértoire de votre choix, puis, tappez la commande suivante :

``git clone git@github.com:AllanLegrand/docker-sae203.git``

Si vous regardez votre répértoire, vous verrez qu'un dossier **docker-sea203** s'est crée, déplacer vous dedans avec la commande

``cd docker-sae203``

Dedans, vous trouverez le fichier README.md, le fichier DockerFile, et un dossier app.

### Créer et lancer l'image 
Toujours dans le dossier docker-sae203, exécuter la commande suivante (en remplacant <nom_image> par le nom que vous voulez) :

``docker build -t <nom_image> .``

Cela vous prendra surement un petit moment (le temps que tout s'installe). 
Une fois terminer, tappez la commande suivante :

``docker run --name <nom_image> -d -p <port-hôte>:80 <nom_image>``

*Note : Il se peut que le port soit déja occupé par votre pc pour autre chose. On vous insite à utiliser le port 8080 pour éviter tous problèmes*

Une fois lancé, démarrer un navigateur de votre choix et tapper dans la barre la ligne suivante :

``localhost:<port_hôte>``

http://localhost/

*Info : Il est possible de changer les datas afin de générer des groupes propres à vos demandes. N'hésitez pas à regarder notre site pour plus d'informations sur comment faire.*

Une fois les planning et autre crées, vous pouvez supprimer l'image (afin de libérer votre port) avec la commande :

``docker stop <nom_image>``

et

``docker rm <nom_image>``
```shell
docker build -t serveurMPM .
```
