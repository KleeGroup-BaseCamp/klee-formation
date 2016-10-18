-- ==========================================================================
--   Copyright � Klee 2016. All Rights Reserved.
--   Nom du script		:	init_data.sql
--   Date de création	:	04/08/2009
--   Action				:	Script d'initialisation des données
-- ==========================================================================

SET AUTOCOMMIT OFF;

/*==============================================================*/
/* Table: ROLE                                                  */
/*==============================================================*/
/*
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_REF_PAY_CONS', 'Consultation Pays');
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_REF_PAY_CREA', 'Creation Pays');
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_REF_PAY_MODIF', 'Modification Pays');

INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_REF_PRF_CONS', 'Consultation Profils');
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_REF_PRF_CREA', 'Creation Profils');
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_REF_PRF_MODIF', 'Modification Profils');
*/
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_ADMIN', 'Administrateur');
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_FORMATTEUR', 'Formateur');
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_RESPONSSABLE', 'Responsable');
INSERT INTO ROLE ( ROL_CODE, LIBELLE ) VALUES ( 'R_ANONYMOUS', 'Anonyme');

/*==============================================================*/
/* Table: UTILISATEUR                                                  */
/*==============================================================*/

INSERT INTO UTILISATEUR (
	UTI_ID, NOM, PRENOM,
	MAIL)
VALUES (
	1, 'Administrateur', '--', 'npiedeloup@kleegroup.com');

INSERT INTO LOGIN (LOG_ID, LOGIN, PASSWORD, UTI_ID)
VALUES (1,'admin', 'zfy8fVkqGGCerNUF4pNXkM92Qr0trt4ij-IX290SyiX9TxlKsbQ=', 1);


INSERT INTO UTILISATEUR (
	UTI_ID, NOM, PRENOM,
	MAIL)
VALUES (
	2, 'Moutte', 'Camille', 'moutte.camille@kleegroup.com');

INSERT INTO LOGIN (LOG_ID, LOGIN, PASSWORD, UTI_ID)
VALUES (2,'camille', 'zfy8fVkqGGCerNUF4pNXkM92Qr0trt4ij-IX290SyiX9TxlKsbQ=', 2);

INSERT INTO UTILISATEUR (
	UTI_ID, NOM, PRENOM,
	MAIL)
VALUES (
	3, 'utilisateur', 'utilisateur', 'utilisateure@kleegroup.com');

INSERT INTO LOGIN (LOG_ID, LOGIN, PASSWORD, UTI_ID)
VALUES (3,'utilisateur', 'zfy8fVkqGGCerNUF4pNXkM92Qr0trt4ij-IX290SyiX9TxlKsbQ=', 3);



INSERT INTO UTILISATEUR (
	UTI_ID, NOM, PRENOM,
	MAIL)
VALUES (
	4, 'admin', 'admin', 'admin@kleegroup.com');

INSERT INTO LOGIN (LOG_ID, LOGIN, PASSWORD, UTI_ID)
VALUES (4,'Admin', 'zfy8fVkqGGCerNUF4pNXkM92Qr0trt4ij-IX290SyiX9TxlKsbQ=', 4);

INSERT INTO UTILISATEUR (
	UTI_ID, NOM, PRENOM,
	MAIL)
VALUES (
	5, 'formateur', 'formateur', 'formateur@kleegroup.com');

INSERT INTO LOGIN (LOG_ID, LOGIN, PASSWORD, UTI_ID)
VALUES (5,'formateur', 'zfy8fVkqGGCerNUF4pNXkM92Qr0trt4ij-IX290SyiX9TxlKsbQ=', 5);


INSERT INTO UTILISATEUR (
	UTI_ID, NOM, PRENOM,
	MAIL)
VALUES (
	6, 'responsable', 'responsable', 'responsable@kleegroup.com');

INSERT INTO LOGIN (LOG_ID, LOGIN, PASSWORD, UTI_ID)
VALUES (6,'responsable', 'zfy8fVkqGGCerNUF4pNXkM92Qr0trt4ij-IX290SyiX9TxlKsbQ=', 6);


/*==============================================================*/
/* Table: UTI_ROL                                                  */
/*==============================================================*/
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (1, 'R_ADMIN');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (1, 'R_FORMATTEUR');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (1, 'R_RESPONSSABLE');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (1, 'R_ANONYMOUS');

INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (2, 'R_RESPONSSABLE');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (2, 'R_FORMATTEUR');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (2, 'R_ANONYMOUS');

INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (3, 'R_ANONYMOUS');

INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (4, 'R_ANONYMOUS');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (4, 'R_ADMIN');

INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (5, 'R_FORMATTEUR');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (5, 'R_ANONYMOUS');

INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (6, 'R_RESPONSSABLE');
INSERT INTO UTI_ROL (UTI_ID, ROL_CODE) VALUES (6, 'R_ANONYMOUS');
/*==============================================================*/
/* Table: NIVEAUX                                                  */
/*==============================================================*/
INSERT INTO NIVEAU (NIV_CODE, LIBELLE) VALUES ('DEBUT', 'Débutant');
INSERT INTO NIVEAU (NIV_CODE, LIBELLE) VALUES ('INTER','Intermédiaire');
INSERT INTO NIVEAU (NIV_CODE, LIBELLE) VALUES ('EXPER','Expert');

/*==============================================================*/
/* Table: ETAT                                                */
/*==============================================================*/
INSERT INTO ETAT (ETA_CODE, LIBELLE) VALUES ('Publier', 'Publiée');
INSERT INTO ETAT (ETA_CODE, LIBELLE) VALUES ('Brouillon', 'Brouillon');
INSERT INTO ETAT (ETA_CODE, LIBELLE) VALUES ('Realiser', 'Réalisée');
INSERT INTO ETAT (ETA_CODE, LIBELLE) VALUES ('Annuler', 'Annulée');

/*==============================================================*/
/* Table: ETAT_SESSION_UTILISATEUR                              */
/*==============================================================*/
INSERT INTO ETAT_SESSION_UTILISATEUR (ESU_CODE, LIBELLE) VALUES ('Ouverte', 'Ouverte');
INSERT INTO ETAT_SESSION_UTILISATEUR (ESU_CODE, LIBELLE) VALUES ('Complete', 'Complete');
INSERT INTO ETAT_SESSION_UTILISATEUR (ESU_CODE, LIBELLE) VALUES ('Annulée', 'Annulée');



COMMIT;
