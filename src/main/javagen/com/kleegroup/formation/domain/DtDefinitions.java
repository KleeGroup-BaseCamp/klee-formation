package com.kleegroup.formation.domain;

import java.util.Arrays;
import java.util.Iterator;
import io.vertigo.dynamo.domain.metamodel.DtFieldName;

/**
 * Attention cette classe est générée automatiquement !
 */
public final class DtDefinitions implements Iterable<Class<?>> {
	
	/**
	 * Enumération des DtDefinitions.
	 */
	public enum Definitions {
			/** Objet de données CritereSession. */
			CritereSession(com.kleegroup.formation.domain.session.CritereSession.class),
			/** Objet de données Etat. */
			Etat(com.kleegroup.formation.domain.formation.Etat.class),
			/** Objet de données EtatSessionUtilisateur. */
			EtatSessionUtilisateur(com.kleegroup.formation.domain.formation.EtatSessionUtilisateur.class),
			/** Objet de données Formation. */
			Formation(com.kleegroup.formation.domain.formation.Formation.class),
			/** Objet de données FormationCritere. */
			FormationCritere(com.kleegroup.formation.domain.formation.FormationCritere.class),
			/** Objet de données Horaires. */
			Horaires(com.kleegroup.formation.domain.formation.Horaires.class),
			/** Objet de données Inscription. */
			Inscription(com.kleegroup.formation.domain.formation.Inscription.class),
			/** Objet de données InscriptionView. */
			InscriptionView(com.kleegroup.formation.domain.inscription.InscriptionView.class),
			/** Objet de données Login. */
			Login(com.kleegroup.formation.domain.administration.utilisateur.Login.class),
			/** Objet de données Niveau. */
			Niveau(com.kleegroup.formation.domain.formation.Niveau.class),
			/** Objet de données Role. */
			Role(com.kleegroup.formation.domain.administration.utilisateur.Role.class),
			/** Objet de données RoleInput. */
			RoleInput(com.kleegroup.formation.domain.administration.utilisateur.RoleInput.class),
			/** Objet de données SessionFormation. */
			SessionFormation(com.kleegroup.formation.domain.formation.SessionFormation.class),
			/** Objet de données SessionView. */
			SessionView(com.kleegroup.formation.domain.session.SessionView.class),
			/** Objet de données Utilisateur. */
			Utilisateur(com.kleegroup.formation.domain.administration.utilisateur.Utilisateur.class),
			/** Objet de données UtilisateurCritere. */
			UtilisateurCritere(com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere.class),
			/** Objet de données UtilisateurLogin. */
			UtilisateurLogin(com.kleegroup.formation.domain.administration.utilisateur.UtilisateurLogin.class),
		;
		
		private final Class<?> clazz;
		private Definitions(final Class<?> clazz) {
			this.clazz = clazz;
		}
		
		/** 
		  * Classe associée.
		  * @return Class d'implémentation de l'objet 
		  */
		public Class<?> getDtClass() {
			return clazz;
		}
    }

	/**
	 * Enumération des champs de CritereSession.
	 */
	public enum CritereSessionFields implements DtFieldName {
		/** Propriété 'Libellé court'. */
		INTITULE,
	}

	/**
	 * Enumération des champs de Etat.
	 */
	public enum EtatFields implements DtFieldName {
		/** Propriété 'Code'. */
		ETA_CODE,
		/** Propriété 'Libelle'. */
		LIBELLE,
	}

	/**
	 * Enumération des champs de EtatSessionUtilisateur.
	 */
	public enum EtatSessionUtilisateurFields implements DtFieldName {
		/** Propriété 'esuCode'. */
		ESU_CODE,
		/** Propriété 'Libellé'. */
		LIBELLE,
	}

	/**
	 * Enumération des champs de Formation.
	 */
	public enum FormationFields implements DtFieldName {
		/** Propriété 'Id'. */
		FOR_ID,
		/** Propriété 'intitule'. */
		INTITULE,
		/** Propriété 'commentaire'. */
		COMMENTAIRE,
		/** Propriété 'Niveau'. */
		NIV_CODE,
	}

	/**
	 * Enumération des champs de FormationCritere.
	 */
	public enum FormationCritereFields implements DtFieldName {
		/** Propriété 'Libellé court'. */
		INTITULE,
		/** Propriété 'Code'. */
		NIV_CODE,
	}

	/**
	 * Enumération des champs de Horaires.
	 */
	public enum HorairesFields implements DtFieldName {
		/** Propriété 'DatId'. */
		DAT_ID,
		/** Propriété 'debut'. */
		DEBUT,
		/** Propriété 'fin'. */
		FIN,
		/** Propriété 'jour'. */
		JOUR,
		/** Propriété 'debutAprem'. */
		DEBUT_APREM,
		/** Propriété 'finAprem'. */
		FIN_APREM,
		/** Propriété 'Session formation'. */
		SES_ID,
	}

	/**
	 * Enumération des champs de Inscription.
	 */
	public enum InscriptionFields implements DtFieldName {
		/** Propriété 'INS_ID'. */
		INS_ID,
		/** Propriété 'PRESENCE'. */
		PRESENCE,
		/** Propriété 'SATISFACTION'. */
		SATISFACTION,
		/** Propriété 'Durée'. */
		DUREE,
		/** Propriété 'Theme'. */
		THEME,
		/** Propriété 'Contenu'. */
		CONTENU,
		/** Propriété 'Progression'. */
		PROGRESSION,
		/** Propriété 'Attentes'. */
		ATTENTES,
		/** Propriété 'Bénéfices'. */
		BENEFICES,
		/** Propriété 'Approfondir'. */
		APPROFONDIR,
		/** Propriété 'Contact'. */
		CONTACT,
		/** Propriété 'Explication'. */
		EXPLICATION,
		/** Propriété 'etat'. */
		ETAT,
		/** Propriété 'Session formation'. */
		SES_ID,
		/** Propriété 'Utilisateur'. */
		UTI_ID,
	}

	/**
	 * Enumération des champs de InscriptionView.
	 */
	public enum InscriptionViewFields implements DtFieldName {
		/** Propriété 'SesId'. */
		SES_ID,
		/** Propriété 'Libellé court'. */
		FORMATION_NAME,
		/** Propriété 'Début'. */
		DATE_DEBUT,
		/** Propriété 'Fin'. */
		DATE_FIN,
		/** Propriété 'Horaires'. */
		HORAIRES,
		/** Propriété 'Durée'. */
		DUREE,
		/** Propriété 'Niveau'. */
		NIVEAU,
		/** Propriété 'Descriptif'. */
		COMMENTAIRE,
		/** Propriété 'Satisfaction'. */
		SATISFACTION,
		/** Propriété 'Présence'. */
		PRESENCE,
		/** Propriété 'Nom'. */
		NOM,
		/** Propriété 'Prénom'. */
		PRENOM,
	}

	/**
	 * Enumération des champs de Login.
	 */
	public enum LoginFields implements DtFieldName {
		/** Propriété 'LOG ID'. */
		LOG_ID,
		/** Propriété 'Login'. */
		LOGIN,
		/** Propriété 'Mot de passe'. */
		PASSWORD,
		/** Propriété 'Utilisateur'. */
		UTI_ID,
	}

	/**
	 * Enumération des champs de Niveau.
	 */
	public enum NiveauFields implements DtFieldName {
		/** Propriété 'Niv_Code'. */
		NIV_CODE,
		/** Propriété 'Libellé'. */
		LIBELLE,
	}

	/**
	 * Enumération des champs de Role.
	 */
	public enum RoleFields implements DtFieldName {
		/** Propriété 'ROL_CODE'. */
		ROL_CODE,
		/** Propriété 'Libellé'. */
		LIBELLE,
	}

	/**
	 * Enumération des champs de RoleInput.
	 */
	public enum RoleInputFields implements DtFieldName {
		/** Propriété 'Role'. */
		ROL_CODE,
	}

	/**
	 * Enumération des champs de SessionFormation.
	 */
	public enum SessionFormationFields implements DtFieldName {
		/** Propriété 'Id'. */
		SES_ID,
		/** Propriété 'Horaire'. */
		HORAIRE,
		/** Propriété 'NbPersonne'. */
		NB_PERSONNE,
		/** Propriété 'Status'. */
		STATUS,
		/** Propriété 'Is_ouvert'. */
		IS_OUVERT,
		/** Propriété 'Satisfaction'. */
		SATISFACTION,
		/** Propriété 'i'. */
		I,
		/** Propriété 'Duree'. */
		DUREE,
		/** Propriété 'DateFin'. */
		DATE_FIN,
		/** Propriété 'DateDebut'. */
		DATE_DEBUT,
		/** Propriété 'Formation'. */
		FOR_ID,
		/** Propriété 'Etat'. */
		ETA_CODE,
		/** Propriété 'Utilisateur'. */
		UTI_ID,
		/** Propriété 'Etat session utilisateur'. */
		ESU_CODE,
	}

	/**
	 * Enumération des champs de SessionView.
	 */
	public enum SessionViewFields implements DtFieldName {
		/** Propriété 'SesId'. */
		SES_ID,
		/** Propriété 'Libellé court'. */
		FORMATION_NAME,
		/** Propriété 'Début'. */
		DATE_DEBUT,
		/** Propriété 'Fin'. */
		DATE_FIN,
		/** Propriété 'Horaires'. */
		HORAIRES,
		/** Propriété 'Durée'. */
		DUREE,
		/** Propriété 'Niveau'. */
		NIVEAU,
		/** Propriété 'Descriptif'. */
		COMMENTAIRE,
		/** Propriété 'Etat'. */
		STATUS,
		/** Propriété 'Etat'. */
		STATUS_UTILISATEUR,
		/** Propriété 'Satisfaction'. */
		SATISFACTION,
	}

	/**
	 * Enumération des champs de Utilisateur.
	 */
	public enum UtilisateurFields implements DtFieldName {
		/** Propriété 'UTI_ID'. */
		UTI_ID,
		/** Propriété 'Nom'. */
		NOM,
		/** Propriété 'Prénom'. */
		PRENOM,
		/** Propriété 'Courriel'. */
		MAIL,
		/** Propriété 'admin'. */
		ADMIN,
		/** Propriété 'responsable'. */
		RESPONSABLE,
		/** Propriété 'formateur'. */
		FORMATEUR,
		/** Propriété 'Prénom Nom'. */
		PRENOM_NOM,
		/** Propriété 'Nom complet'. */
		NOM_COMPLET,
	}

	/**
	 * Enumération des champs de UtilisateurCritere.
	 */
	public enum UtilisateurCritereFields implements DtFieldName {
		/** Propriété 'Nom'. */
		NOM,
		/** Propriété 'Nom'. */
		PRENOM,
		/** Propriété 'Indentifiant'. */
		LOGIN,
		/** Propriété 'Rôle'. */
		ROLE,
		/** Propriété 'Seulement les utilisateurs actifs'. */
		IS_ACTIF,
	}

	/**
	 * Enumération des champs de UtilisateurLogin.
	 */
	public enum UtilisateurLoginFields implements DtFieldName {
		/** Propriété 'Nom'. */
		NOM,
		/** Propriété 'Identifiant utilisateur'. */
		LOGIN,
		/** Propriété 'Mot de passe'. */
		PASSWORD,
		/** Propriété 'Nouveau mot de passe'. */
		NEW_PASSWORD,
		/** Propriété 'Resaisir le mot de passe'. */
		NEW_PASSWORD_CHECK,
	}

	    
    /** {@inheritDoc} */
    @Override
    public Iterator<Class<?>> iterator() {
        return new Iterator<Class<?>>() {
            private Iterator<Definitions> it = Arrays.asList(Definitions.values()).iterator();

            /** {@inheritDoc} */
            @Override
            public boolean hasNext() {
				return it.hasNext();
            }

            /** {@inheritDoc} */
            @Override
            public Class<?> next() {
            	return it.next().getDtClass();
            }

            /** {@inheritDoc} */
            @Override
            public void remove() {
            	//unsupported
            }
        };
    }                      
}
