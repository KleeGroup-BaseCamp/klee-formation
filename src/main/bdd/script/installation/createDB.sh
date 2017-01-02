echo Creation de la base de donnees specifique

. ./klee-formation.properties

# Create Users DB
echo creation de l utilisateur base de donnees $NOM_USER_DB : 
createuser -e -l -d -S -R -P -E -p $PORT_DB $NOM_USER_DB

# Create BD
echo creation de la base de donnees $NOM_DB : 
createdb -e -p $PORT_DB -O $NOM_USER_DB $NOM_DB

# Create lang plpgsql
echo "creation du langage plpgsql. Peut echouer si le langage existe deja"
createlang -e plpgsql $NOM_DB

# Create schema
echo creation du schema $NOM_USER_DB
psql -h localhost -e $NOM_DB $NOM_USER_DB -p $PORT_DB -c " create schema \"$NOM_USER_DB\" authorization \"$NOM_USER_DB\";"

