package com.kleegroup.formation.services.administration.utilisateur;

import java.util.List;

import com.kleegroup.formation.domain.administration.utilisateur.Role;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurLogin;

import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.lang.Component;

/**
 * Décrit les services associés à la gestion des utilisateurs.
 *
 */
public interface UtilisateurServices extends Component {

	/**
	 * Connecte un utilisateur authentifi� avec un identifiant et un mot de passe.
	 *
	 * @param utilisateurLogin informations de login
	 * @param createIfNotExist create the user if the user is unknow in database
	 * @return utilisateur connect�
	 */
	Utilisateur connecterUtilisateur(String utilisateurLogin, boolean createIfNotExist);

	/**
	 * Charge un utilisateur par son id technique.
	 *
	 * @return utilisateur connect�
	 */
	Utilisateur loadUtilisateurWithRoles(Long utiId);

	/**
	 * Charge un role par son code.
	 *
	 * @param rolCode code du role
	 * @return Role
	 */
	Role loadRole(String rolCode);

	/**
	 * Charge la liste des roles
	 *
	 * @return liste de roles
	 */
	public DtList<Role> getAllRoles();

	/**
	 * Retourne la liste des utilisateurs r�pondant aux crit�res.
	 *
	 * @param criteres crit�res de recherche des utilisateurs
	 * @return liste d'utilisateurs r�pondant aux crit�res donn�s en entr�e
	 */
	DtList<Utilisateur> getUtilisateurListByCritere(UtilisateurCritere criteres);

	/**
	 * Retourne la liste de tous utilisateurs.
	 *
	 * @return liste des utilisateurs
	 */

	DtList<Utilisateur> listUtilisateur();

	/**
	 * Sauvegarde en base un utilisateur.
	 *
	 * @param utilisateur utilisateur � persister en base
	 */
	void saveUtilisateur(Utilisateur utilisateur, List<URI> dtcURIRole);

	/**
	 * Sauvegarde en base un utilisateur, et ses informations de login.
	 *
	 * @param utilisateur utilisateur � persister en base
	 * @param dtcRole Liste des droits
	 */
	void saveUtilisateur(final UtilisateurLogin login, final Utilisateur utilisateur, List<URI> dtcURIRole);

	/**
	 * Supprime un utilisateur en base de donn�es.
	 *
	 * @param utiId Id utilisateur � supprimer en base
	 */
	void deleteUtilisateur(Long utiId);

	/**
	 * Renvoie l'utilisateur connecter sur l'application de gestion des formations
	 *
	 */

	Utilisateur getCurrentUtilisateur();

	/**
	 * synchronise la liste des utilisateur avec celle de la liste des utilisateur de klee
	 *
	 */
	void importLdapUtilisateur();

}
