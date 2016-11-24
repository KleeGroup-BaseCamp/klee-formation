<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>
<s:form>
	<s:div layout="table" cols="6">
		<s:textfield name="utilisateurCritere.nom" label="default"/>
		<s:textfield name="utilisateurCritere.prenom" label="Prénom"/>
		<%--<s:textfield name="utilisateurCritere.role" label="default"/> --%>
		<s:select name="utilisateurCritere.role" label="default" list="roles"/>	
	</s:div>
	<div class="button-bar">
		<div class="right">
			<s:submit action="rechercherUtilisateurList" value="RECHERCHER" cssClass="rechercher" />
		</div>
	</div>
</s:form>
<h3>Liste des utilisateurs</h3>

<display:table  name="utilisateurs" class="tableau" uid="item" export="true" sort="list" requestURI="#" pagesize="20">
	<display:setProperty name="basic.msg.empty_list">Aucun utilisateur.</display:setProperty>
	<display:setProperty name="export.csv.filename">utilisateurs.csv</display:setProperty>
	<display:column title="${util.label('utilisateurs.nom')}" sortable="true">
		<s:url action="UtilisateurDetail" includeParams="get" var="utilisateurDetailURL">
			<s:param name="utiId">${item.utiId}</s:param>
		</s:url>
		<a href="${utilisateurDetailURL}" target=_blank>${item.nom}</a>
	</display:column>
	<display:column  property="prenom" title="${util.label('utilisateurs.prenom')}"/>		
	<display:column property="mail" title="${util.label('utilisateurs.mail')}" sortable="true"/>
	<display:column title="Responsable" sortable="true" >
		<s:checkbox name="%{util.contextKey(#attr.item)}.responsable" theme="simple_read" />
	</display:column>
	<display:column title="Administrateur" sortable="true" >
		<s:checkbox name="%{util.contextKey(#attr.item)}.admin" theme="simple_read" />
	</display:column>
	<display:column title="Formateur" sortable="true" >
		<s:checkbox name="%{util.contextKey(#attr.item)}.formateur" theme="simple_read" />
	</display:column>
	
	<display:column property="utiId" title="${util.label('utilisateurs.utiId')}" sortable="true"/>
</display:table>

<s:form>
<div class="button-bar">
	<div class="right">
		<s:if test="%{isAdministrateur()}">
		<s:submit action="loadLdapUtilisateurList" value="IMPORTER UTILISATEURS" cssClass="supprimer" />
	
		<s:a action="UtilisateurDetail" cssClass="creer">NOUVEAU</s:a>
		</s:if>
	</div>
</div>
</s:form>
<s:include value="/jsp/include/footer.jsp" />

	
	