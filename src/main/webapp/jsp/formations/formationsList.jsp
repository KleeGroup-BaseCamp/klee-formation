<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>

<h3>Rechercher un Utilisateur</h3>
<br/>
<br/>
<s:form>
	<s:div layout="table">
		<s:textfield name="utilisateurCritere.nom" label="default"/>
		<s:textfield name="utilisateurCritere.login" label="default"/>
		<s:textfield name="utilisateurCritere.role" label="default"/>
	</s:div>
	<div class="button-bar">
		<div class="right">
		
			<s:submit action="utilisateurRechercherFormationsList" value="RECHERCHER" cssClass="rechercher" />
		</div>
	</div>
	</s:form>
	
	<display:table  name="utilisateurs" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
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
		<display:column property="utiId" title="${util.label('utilisateurs.utiId')}" sortable="true"/>
	</display:table>
	
	
<%@include file="/jsp/include/footer.jsp"%>