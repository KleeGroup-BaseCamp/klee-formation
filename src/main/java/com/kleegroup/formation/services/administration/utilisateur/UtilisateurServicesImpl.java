package com.kleegroup.formation.services.administration.utilisateur;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.kleegroup.formation.dao.administration.utilisateur.LoginDAO;
import com.kleegroup.formation.dao.administration.utilisateur.RoleDAO;
import com.kleegroup.formation.dao.administration.utilisateur.UtilisateurDAO;
import com.kleegroup.formation.domain.DtDefinitions;
import com.kleegroup.formation.domain.DtDefinitions.LoginFields;
import com.kleegroup.formation.domain.administration.utilisateur.Login;
import com.kleegroup.formation.domain.administration.utilisateur.Role;
import com.kleegroup.formation.domain.administration.utilisateur.Utilisateur;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurCritere;
import com.kleegroup.formation.domain.administration.utilisateur.UtilisateurLogin;
import com.kleegroup.formation.services.KleeFormationUserSession;
import com.kleegroup.formation.services.util.PasswordHelper;

import io.vertigo.app.Home;
import io.vertigo.dynamo.domain.model.DtList;
import io.vertigo.dynamo.domain.model.DtListURI;
import io.vertigo.dynamo.domain.model.DtListURIForCriteria;
import io.vertigo.dynamo.domain.model.URI;
import io.vertigo.dynamo.domain.util.DtObjectUtil;
import io.vertigo.dynamo.store.StoreManager;
import io.vertigo.dynamo.store.criteria.Criteria;
import io.vertigo.dynamo.store.criteria.FilterCriteria;
import io.vertigo.dynamo.store.criteria.FilterCriteriaBuilder;
import io.vertigo.dynamo.transaction.Transactional;
import io.vertigo.lang.Assertion;
import io.vertigo.lang.MessageText;
import io.vertigo.lang.VUserException;
import io.vertigo.persona.security.VSecurityManager;
//import io.vertigo.util.DateUtil;

/**
 * Impl�mentation des services associ�s � la gestion des utilisateurs.
 *
 */
@Transactional
public class UtilisateurServicesImpl implements UtilisateurServices {
	private static final String MSG_LOGIN_INVALID = "Les informations de connexion que vous avez fournies ne correspondent pas � cet utilisateur.";

	@Inject
	private LoginDAO loginDAO;
	@Inject
	private RoleDAO roleDAO;
	@Inject
	private UtilisateurDAO utilisateurDAO;
	@Inject
	private VSecurityManager securityManager;
	@Inject
	private StoreManager storeManager;

	private final PasswordHelper passwordHelper = new PasswordHelper();

	/** {@inheritDoc} */
	@Override
	public Utilisateur connecterUtilisateur(final UtilisateurLogin utilisateurLogin) {
		// Authentification de l'utilisateur auprès de la base de données
		final Utilisateur utilisateur = loadUtilisateurByLogin(utilisateurLogin);
		// Charge la collection car la transaction est au niveau de la couche service
		utilisateur.getRoleList();
		return utilisateur;
	}

	/** {@inheritDoc} */
	@Override
	public Utilisateur loadUtilisateurWithRoles(final Long utiId) {
		final Utilisateur utilisateur = utilisateurDAO.get(utiId);
		//On charge les roles en mode transactionnel
		utilisateur.getRoleList();
		return utilisateur;
	}

	/** {@inheritDoc} */
	@Override
	public Role loadRole(final String rolCode) {
		return roleDAO.get(rolCode);
	}

	/** {@inheritDoc} */
	@Override
	public DtList<Role> getAllRoles() {
		final DtListURI collectionURI = new DtListURIForCriteria<>(DtObjectUtil.findDtDefinition(Role.class), null, null);
		return storeManager.getDataStore().findAll(collectionURI);
	}

	/** {@inheritDoc} */
	@Override
	public DtList<Utilisateur> getUtilisateurListByCritere(final UtilisateurCritere criteres) {
		return utilisateurDAO.listUtilisateurByCritere(criteres);

	}

	/** {@inheritDoc} */
	@Override
	public void saveUtilisateur(final Utilisateur utilisateur, final List<URI> dtcURIRole) {
		Assertion.checkNotNull(utilisateur);
		// ---------------------------------------------------------------------
		/*	if (utilisateur.getUtiId() == null) {
				utilisateur.setDateCreation(DateUtil.newDateTime());
			} else {
				utilisateur.setDateDerniereModif(DateUtil.newDateTime());
			}
		*/
		//utilisateur.setAuteurDerniereModif(getCurrentUtilisateur().getPrenomNom());
		utilisateurDAO.save(utilisateur);
		if (!dtcURIRole.isEmpty()) {
			utilisateurDAO.updateNN(utilisateur.getRoleDtListURI(), dtcURIRole);
		}
	}

	@Override
	public Utilisateur getCurrentUtilisateur() {
		return securityManager.<KleeFormationUserSession> getCurrentUserSession().get().getUtilisateur();
	}

	/** {@inheritDoc} */
	@Override
	public void saveUtilisateur(final UtilisateurLogin utilisateurLogin, final Utilisateur utilisateur, final List<URI> dtcURIRole) {
		Assertion.checkNotNull(utilisateurLogin);
		// ---------------------------------------------------------------------
		final boolean isCreation = utilisateur.getUtiId() == null;
		saveUtilisateur(utilisateur, dtcURIRole);
		if (!isCreation) {
			final Utilisateur loggedUser = loadUtilisateurByLogin(utilisateurLogin);
			if (!loggedUser.getUtiId().equals(utilisateur.getUtiId())) {
				throw new VUserException(new MessageText(MSG_LOGIN_INVALID, null));
			}
			loginDAO.delete(utilisateur.getLoginList().get(0).getLogId());
		}
		final Login login = new Login();
		login.setUtiId(utilisateur.getUtiId());
		login.setLogin(utilisateurLogin.getLogin());
		final String encodedPassword = passwordHelper.encodePassword(passwordHelper.generateNewSalt(), utilisateurLogin.getPassword());
		login.setPassword(encodedPassword);
		loginDAO.create(login);
	}

	/** {@inheritDoc} */
	@Override
	public void deleteUtilisateur(final Long utiId) {
		//throw new UnsupportedOperationException("operation not supported yet !!!");
		final FilterCriteria<Login> filter = new FilterCriteriaBuilder<Login>().withFilter(LoginFields.UTI_ID, utiId).build();
		final DtList<Login> logins = loginDAO.getList(filter, 1);
		loginDAO.delete(logins.get(0).getLogId());
		final Utilisateur utilisateur = utilisateurDAO.get(utiId);
		/*int i = 0;
		while (i < utilisateur.getRoleList().size()) {
			System.out.println(Integer.toString(utilisateur.getRoleList().size()));
			System.out.println(Integer.toString(i));
			utilisateur.getRoleList().remove(i);
			i = i + 1;
		
		}*/

		final List<URI> roles = new ArrayList<>();
		utilisateurDAO.updateNN(utilisateur.getRoleDtListURI(), roles);
		//utilisateurDAO.updateNN(utilisateur.getRoleDtListURI(), new List<URI>());

		//	roleDAO.delete(utiId.toString());

		utilisateurDAO.delete(utiId);
	}

	/**
	 * Authentification de l'utilisateur en base de données.<br>
	 * Récupération de l'utilisateur dans la base de données de l'application si l'utilisateur est authentifié.
	 *
	 * @param utilisateurLogin login et mot de passe de l'utilisateur cherchant à se connecter
	 * @return l'utilisateur correspondant aux login et mot de passe saisis.
	 */
	private Utilisateur loadUtilisateurByLogin(final UtilisateurLogin utilisateurLogin) {
		Assertion.checkNotNull(utilisateurLogin);
		//-----
		final Criteria<Login> critere = new FilterCriteriaBuilder<Login>()
				.withFilter(DtDefinitions.LoginFields.LOGIN, utilisateurLogin.getLogin())
				.build();
		final DtList<Login> logins = loginDAO.getList(critere, 1);
		//On effectue le même traitement si le login est incorrect pour éviter l'analyse par le temps
		final String password = logins.isEmpty() ? "UnknownPassword" : logins.get(0).getPassword();
		final String salt = passwordHelper.extractSalt(password);
		final String encodePassword = passwordHelper.encodePassword(salt, utilisateurLogin.getPassword());

		if (logins.isEmpty() || !password.equals(encodePassword)) {
			throw new VUserException(new MessageText("Login ou mot de passe incorrect", null));
		}

		final KleeFormationUserSession session = securityManager.<KleeFormationUserSession> getCurrentUserSession().get();
		final Utilisateur utilisateur = utilisateurDAO.get(logins.get(0).getUtiId());
		session.setUtilisateur(utilisateur);
		session.authenticate();

		for (final Role role : utilisateur.getRoleList()) {
			session.addRole(Home.getApp().getDefinitionSpace().resolve(role.getRolCode(), io.vertigo.persona.security.metamodel.Role.class));
		}

		return utilisateur;
	}
}
