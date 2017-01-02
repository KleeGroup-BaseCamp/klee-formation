#!/bin/bash
#title           :initDB.sh
#description     :Ce script permet d'initialiser la base de données.
#author          :xdurand
#date            :23/05/2016
#version         :0.2
#usage           :. ./initDB.sh
#notes           :Ce script doit être lancé depuis la VM persistence.
#bash_version    :4.3.30(1)-release
#==============================================================================

set -o pipefail

echo "Creation des tables/ index et donnees"

. ./klee-formation.properties

LOG_FILE=initDB.log

echo "$(date) Debut initDB." >> $LOG_FILE

#Initialisation de la base 
psql -h localhost $NOM_DB $NOM_USER_DB -p $PORT_DB -v ON_ERROR_STOP=1 -f ../../../sqlgen/crebas.sql 2>&1 | tee --append $LOG_FILE 
RET_CODE=$?
echo "$(date) Fin initDB. Code retour:$RET_CODE" >> $LOG_FILE
exit $RET_CODE
