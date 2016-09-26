package com.kleegroup.formation.services;

import io.vertigo.persona.security.UserSession;

import java.util.Locale;

import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;

/**
 * Session d'un utilisateur<br>
 * Un utilisateur poss�de une liste de profils correspondant � des r�les au sein d'une ou plusieurs entit�s.<br>
 * On consid�re que toute session utilisateur cr��e implique que l'utilisateur est authentifi�.
 * 
 */
public final class KleeFormationUserSession extends UserSession {

	/** 
	 * Serial Version.
	 */
	private static final long serialVersionUID = 2497388902473962429L;

	/**
	 * Utilisateur auquel est associ� la session.
	 */
	private Utilisateur utilisateur;

	private boolean deviseEuro = true;

	/**
	 * @return si la devis courante est l'euro
	 */
	public boolean isDeviseEuro() {
		return deviseEuro;
	}

	/**
	 * Change la devise courante.
	 */
	public void swapDevise() {
		deviseEuro = !deviseEuro;
	}

	/**
	 * R�cup�re la valeur de l'attribut utilisateur.
	 * 
	 * @return valeur de l'attribut utilisateur
	 */
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	/**
	 * Initialise l'attribut utilisateur.
	 * 
	 * @param utilisateur valeur d'initialisation de l'attribut utilisateur
	 */
	public void setUtilisateur(final Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/** {@inheritDoc} */
	@Override
	public Locale getLocale() {
		return deviseEuro ? Locale.FRANCE : Locale.ENGLISH;
	}
}
