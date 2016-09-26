-- ============================================================
--   SGBD      		  :  PostgreSql                     
-- ============================================================




-- ============================================================
--   Sequences                                      
-- ============================================================
create sequence SEQ_ETAT
	start with 1000 cache 20; 

create sequence SEQ_ETAT_SESSION_UTILISATEUR
	start with 1000 cache 20; 

create sequence SEQ_FORMATION
	start with 1000 cache 20; 

create sequence SEQ_HORAIRES
	start with 1000 cache 20; 

create sequence SEQ_INSCRIPTION
	start with 1000 cache 20; 

create sequence SEQ_LOGIN
	start with 1000 cache 20; 

create sequence SEQ_NIVEAU
	start with 1000 cache 20; 

create sequence SEQ_ROLE
	start with 1000 cache 20; 

create sequence SEQ_SESSION_FORMATION
	start with 1000 cache 20; 

create sequence SEQ_UTILISATEUR
	start with 1000 cache 20; 


-- ============================================================
--   Table : ETAT                                        
-- ============================================================
create table ETAT
(
    ETA_CODE    	 VARCHAR(30) 	not null,
    LIBELLE     	 VARCHAR(40) 	,
    SES_ID      	 BIGINT      	not null,
    constraint PK_ETAT primary key (ETA_CODE)
);

comment on column ETAT.ETA_CODE is
'Code';

comment on column ETAT.LIBELLE is
'Libelle';

comment on column ETAT.SES_ID is
'Session formation';

create index ETAT_SES_ID_FK on ETAT (SES_ID asc);
-- ============================================================
--   Table : ETAT_SESSION_UTILISATEUR                                        
-- ============================================================
create table ETAT_SESSION_UTILISATEUR
(
    ESU_CODE    	 VARCHAR(30) 	not null,
    LIBELLE     	 VARCHAR(100)	,
    SES_ID      	 BIGINT      	not null,
    constraint PK_ETAT_SESSION_UTILISATEUR primary key (ESU_CODE)
);

comment on column ETAT_SESSION_UTILISATEUR.ESU_CODE is
'esuCode';

comment on column ETAT_SESSION_UTILISATEUR.LIBELLE is
'Libellé';

comment on column ETAT_SESSION_UTILISATEUR.SES_ID is
'Session formation';

create index ETAT_SESSION_UTILISATEUR_SES_ID_FK on ETAT_SESSION_UTILISATEUR (SES_ID asc);
-- ============================================================
--   Table : FORMATION                                        
-- ============================================================
create table FORMATION
(
    FOR_ID      	 BIGINT      	not null,
    INTITULE    	 VARCHAR(40) 	,
    COMMENTAIRE 	 VARCHAR(250)	,
    NIV_FORMATION	 VARCHAR(40) 	,
    NIV_CODE    	 VARCHAR(30) 	,
    constraint PK_FORMATION primary key (FOR_ID)
);

comment on column FORMATION.FOR_ID is
'Id';

comment on column FORMATION.INTITULE is
'intitule';

comment on column FORMATION.COMMENTAIRE is
'commentaire';

comment on column FORMATION.NIV_FORMATION is
'niveau';

comment on column FORMATION.NIV_CODE is
'Niveau';

create index FORMATION_NIV_CODE_FK on FORMATION (NIV_CODE asc);
-- ============================================================
--   Table : HORAIRES                                        
-- ============================================================
create table HORAIRES
(
    DAT_ID      	 BIGINT      	not null,
    DEBUT       	 TIMESTAMP   	,
    FIN         	 TIMESTAMP   	,
    SES_ID      	 BIGINT      	,
    constraint PK_HORAIRES primary key (DAT_ID)
);

comment on column HORAIRES.DAT_ID is
'DatId';

comment on column HORAIRES.DEBUT is
'debut';

comment on column HORAIRES.FIN is
'fin';

comment on column HORAIRES.SES_ID is
'Session formation';

create index HORAIRES_SES_ID_FK on HORAIRES (SES_ID asc);
-- ============================================================
--   Table : INSCRIPTION                                        
-- ============================================================
create table INSCRIPTION
(
    INS_ID      	 BIGINT      	not null,
    PRESENCE    	 BOOLEAN     	,
    SATISFACTION	 NUMERIC(5,2)	,
    SESSION_NAME	 VARCHAR(100)	,
    NOM         	 VARCHAR(100)	,
    PRENOM      	 VARCHAR(100)	,
    MAIL        	 VARCHAR(100)	,
    NIVEAU      	 VARCHAR(40) 	,
    COMMENTAIRE 	 VARCHAR(250)	,
    DATEDEBUT   	 TIMESTAMP   	,
    DATEFIN     	 TIMESTAMP   	,
    HORAIRE     	 VARCHAR(250)	,
    DUREEJOUR   	 BIGINT      	,
    DUREE       	 NUMERIC(5,2)	,
    THEME       	 NUMERIC(5,2)	,
    CONTENU     	 NUMERIC(5,2)	,
    PROGRESSION 	 NUMERIC(5,2)	,
    ATTENTES    	 NUMERIC(5,2)	,
    BENEFICES   	 NUMERIC(5,2)	,
    APPROFONDIR 	 NUMERIC(5,2)	,
    CONTACT     	 NUMERIC(5,2)	,
    EXPLICATION 	 NUMERIC(5,2)	,
    ETAT        	 VARCHAR(100)	,
    SES_ID      	 BIGINT      	,
    UTI_ID      	 BIGINT      	,
    constraint PK_INSCRIPTION primary key (INS_ID)
);

comment on column INSCRIPTION.INS_ID is
'INS_ID';

comment on column INSCRIPTION.PRESENCE is
'PRESENCE';

comment on column INSCRIPTION.SATISFACTION is
'SATISFACTION';

comment on column INSCRIPTION.SESSION_NAME is
'sessionName';

comment on column INSCRIPTION.NOM is
'nom';

comment on column INSCRIPTION.PRENOM is
'prenom';

comment on column INSCRIPTION.MAIL is
'mail';

comment on column INSCRIPTION.NIVEAU is
'niveau';

comment on column INSCRIPTION.COMMENTAIRE is
'commentaire';

comment on column INSCRIPTION.DATEDEBUT is
'dateDebut';

comment on column INSCRIPTION.DATEFIN is
'dateFin';

comment on column INSCRIPTION.HORAIRE is
'horaire';

comment on column INSCRIPTION.DUREEJOUR is
'duréejour';

comment on column INSCRIPTION.DUREE is
'Durée';

comment on column INSCRIPTION.THEME is
'Theme';

comment on column INSCRIPTION.CONTENU is
'Contenu';

comment on column INSCRIPTION.PROGRESSION is
'Progression';

comment on column INSCRIPTION.ATTENTES is
'Attentes';

comment on column INSCRIPTION.BENEFICES is
'Bénéfices';

comment on column INSCRIPTION.APPROFONDIR is
'Approfondir';

comment on column INSCRIPTION.CONTACT is
'Contact';

comment on column INSCRIPTION.EXPLICATION is
'Explication';

comment on column INSCRIPTION.ETAT is
'etat';

comment on column INSCRIPTION.SES_ID is
'Session formation';

create index INSCRIPTION_SES_ID_FK on INSCRIPTION (SES_ID asc);
comment on column INSCRIPTION.UTI_ID is
'Utilisateur';

create index INSCRIPTION_UTI_ID_FK on INSCRIPTION (UTI_ID asc);
-- ============================================================
--   Table : LOGIN                                        
-- ============================================================
create table LOGIN
(
    LOG_ID      	 BIGINT      	not null,
    LOGIN       	 VARCHAR(30) 	not null,
    PASSWORD    	 VARCHAR(250)	not null,
    UTI_ID      	 BIGINT      	not null,
    constraint PK_LOGIN primary key (LOG_ID)
);

comment on column LOGIN.LOG_ID is
'LOG ID';

comment on column LOGIN.LOGIN is
'Login';

comment on column LOGIN.PASSWORD is
'Mot de passe';

comment on column LOGIN.UTI_ID is
'Utilisateur';

create index LOGIN_UTI_ID_FK on LOGIN (UTI_ID asc);
-- ============================================================
--   Table : NIVEAU                                        
-- ============================================================
create table NIVEAU
(
    NIV_CODE    	 VARCHAR(30) 	not null,
    LIBELLE     	 VARCHAR(40) 	,
    constraint PK_NIVEAU primary key (NIV_CODE)
);

comment on column NIVEAU.NIV_CODE is
'Niv_Code';

comment on column NIVEAU.LIBELLE is
'Libellé';

-- ============================================================
--   Table : ROLE                                        
-- ============================================================
create table ROLE
(
    ROL_CODE    	 VARCHAR(30) 	not null,
    LIBELLE     	 VARCHAR(100)	not null,
    constraint PK_ROLE primary key (ROL_CODE)
);

comment on column ROLE.ROL_CODE is
'ROL_CODE';

comment on column ROLE.LIBELLE is
'Libellé';

-- ============================================================
--   Table : SESSION_FORMATION                                        
-- ============================================================
create table SESSION_FORMATION
(
    SES_ID      	 BIGINT      	not null,
    HORAIRE     	 VARCHAR(250)	,
    NB_PERSONNE 	 BIGINT      	,
    FORMATION_NAME	 VARCHAR(100)	,
    STATUS      	 VARCHAR(100)	,
    COMMENTAIRE 	 VARCHAR(250)	,
    NIVEAU      	 VARCHAR(40) 	,
    IS_OUVERT   	 VARCHAR(100)	,
    FORMATEUR   	 VARCHAR(250)	,
    SATISFACTION	 NUMERIC(5,2)	,
    I           	 NUMERIC(5,2)	,
    DUREE       	 BIGINT      	,
    DATE_FIN    	 TIMESTAMP   	,
    DATE_DEBUT  	 TIMESTAMP   	,
    FOR_ID      	 BIGINT      	,
    UTI_ID      	 BIGINT      	,
    constraint PK_SESSION_FORMATION primary key (SES_ID)
);

comment on column SESSION_FORMATION.SES_ID is
'Id';

comment on column SESSION_FORMATION.HORAIRE is
'Horaire';

comment on column SESSION_FORMATION.NB_PERSONNE is
'NbPersonne';

comment on column SESSION_FORMATION.FORMATION_NAME is
'FormationName';

comment on column SESSION_FORMATION.STATUS is
'Status';

comment on column SESSION_FORMATION.COMMENTAIRE is
'Commentaire';

comment on column SESSION_FORMATION.NIVEAU is
'Niveau';

comment on column SESSION_FORMATION.IS_OUVERT is
'Is_ouvert';

comment on column SESSION_FORMATION.FORMATEUR is
'Formateur';

comment on column SESSION_FORMATION.SATISFACTION is
'Satisfaction';

comment on column SESSION_FORMATION.I is
'i';

comment on column SESSION_FORMATION.DUREE is
'Duree';

comment on column SESSION_FORMATION.DATE_FIN is
'DateFin';

comment on column SESSION_FORMATION.DATE_DEBUT is
'DateDebut';

comment on column SESSION_FORMATION.FOR_ID is
'Formation';

create index SESSION_FORMATION_FOR_ID_FK on SESSION_FORMATION (FOR_ID asc);
comment on column SESSION_FORMATION.UTI_ID is
'Utilisateur';

create index SESSION_FORMATION_UTI_ID_FK on SESSION_FORMATION (UTI_ID asc);
-- ============================================================
--   Table : UTILISATEUR                                        
-- ============================================================
create table UTILISATEUR
(
    UTI_ID      	 BIGINT      	not null,
    NOM         	 VARCHAR(100)	,
    PRENOM      	 VARCHAR(100)	,
    MAIL        	 VARCHAR(255)	not null,
    ADMIN       	 BOOLEAN     	,
    RESPONSABLE 	 BOOLEAN     	,
    FORMATEUR   	 BOOLEAN     	,
    constraint PK_UTILISATEUR primary key (UTI_ID)
);

comment on column UTILISATEUR.UTI_ID is
'UTI_ID';

comment on column UTILISATEUR.NOM is
'Nom';

comment on column UTILISATEUR.PRENOM is
'Prénom';

comment on column UTILISATEUR.MAIL is
'Courriel';

comment on column UTILISATEUR.ADMIN is
'admin';

comment on column UTILISATEUR.RESPONSABLE is
'responsable';

comment on column UTILISATEUR.FORMATEUR is
'formateur';


create table UTI_ROL
(
	UTI_ID      	 BIGINT      	 not null,
	ROL_CODE    	 VARCHAR(30) 	 not null,
	constraint PK_UTI_ROL primary key (UTI_ID, ROL_CODE),
	constraint FK_UTI_ROL_UTILISATEUR 
		foreign key(UTI_ID)
		references UTILISATEUR (UTI_ID),
	constraint FK_UTI_ROL_ROLE 
		foreign key(ROL_CODE)
		references ROLE (ROL_CODE)
);

create index UTI_ROL_UTILISATEUR_FK on UTI_ROL (UTI_ID asc);

create index UTI_ROL_ROLE_FK on UTI_ROL (ROL_CODE asc);


alter table INSCRIPTION
	add constraint FK_ASSOCIATION3 foreign key (UTI_ID)
	references UTILISATEUR (UTI_ID);

alter table ETAT
	add constraint FK_ASSOCIATION5 foreign key (SES_ID)
	references SESSION_FORMATION (SES_ID);

alter table FORMATION
	add constraint FK_ASSOCIATION6 foreign key (NIV_CODE)
	references NIVEAU (NIV_CODE);

alter table HORAIRES
	add constraint FK_ASSOCIATION7 foreign key (SES_ID)
	references SESSION_FORMATION (SES_ID);

alter table ETAT_SESSION_UTILISATEUR
	add constraint FK_ESUSES foreign key (SES_ID)
	references SESSION_FORMATION (SES_ID);

alter table SESSION_FORMATION
	add constraint FK_FOR_SES foreign key (FOR_ID)
	references FORMATION (FOR_ID);

alter table INSCRIPTION
	add constraint FK_LCO_CMD foreign key (SES_ID)
	references SESSION_FORMATION (SES_ID);

alter table LOGIN
	add constraint FK_UTI_LOG foreign key (UTI_ID)
	references UTILISATEUR (UTI_ID);

alter table SESSION_FORMATION
	add constraint FK_UTI_SES foreign key (UTI_ID)
	references UTILISATEUR (UTI_ID);

