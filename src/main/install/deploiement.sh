#!/bin/bash
#
# tomcat        Script de mise a jour de l'application KleeFromation
#

echo -ne "Mise a jour de l'application KleeFormation pour Tomcat \n"
echo -ne "/!\ Assurez-vous de bien avoir arrete le Tomcat avant de poursuivre\n"



read -p "Numero de version a deployer ? " -a VERSION

WAR_HOME=/home/tomcat/war
TOMCAT_HOME=/home/tomcat/apache-tomcat
TOMCAT_USER=tomcat

BASENAME_WAR=KleeFormation

export TOMCAT_HOME WAR_HOME TOMCAT_USER

if [ ! -e $WAR_HOME/$BASENAME_WAR-v$VERSION.war ]
then
        echo "Le fichier $WAR_HOME/$BASENAME_WAR-v$VERSION.war n'existe pas !"
        exit
fi

sudo service tomcat stop

echo -ne "Copie de la nouvelle version de l'application sur le serveur applicatif\n"
cp $WAR_HOME/$BASENAME_WAR-v$VERSION.war $TOMCAT_HOME/webapps/formation.war

echo -ne "Suppression des fichiers du work \n"
rm -Rf $TOMCAT_HOME/work/Catalina/localhost/*

echo -ne "Supression de la webapp \n"
rm -Rf $TOMCAT_HOME/work/formation/*

sleep 10

echo -ne "Redemarrage du serveur \n"
sudo service tomcat start

tail -f $TOMCAT_HOME/logs/catalina.out

exit 0

