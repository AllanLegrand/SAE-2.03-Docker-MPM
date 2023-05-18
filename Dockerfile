#!/bin/bash
FROM debian:latest

ENV DEBIAN_FRONTEND=noninteractive

# Installer des services et des packages
RUN  apt-get update && \
    apt-get -y install  \
    apache2
    
RUN apt install -y openjdk-17-jdk

RUN chmod -R 777 /var/www/

RUN apt install -y php libapache2-mod-php php-mysql
RUN apt install -y graphviz

COPY ./html /var/www/html

# Exposer le port 80
EXPOSE 80

# Lancer le service apache au démarrage du conteneur
CMD ["/usr/sbin/apache2ctl","-DFOREGROUND"]
    
