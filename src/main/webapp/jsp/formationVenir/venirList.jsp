<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>	

<s:form>
<h4>Rechercher une formation</h4>
	<s:div layout="table" cols="2">
		<s:textfield name="critere.intitule" label="nom de la formation"/>
	</s:div>
	<div class="button-bar">
		<div class="right">
			<s:submit action="rechercherVenirList" value="RECHERCHER" cssClass="rechercher" />
		</div>
	</div>
</s:form>
</br>
<h3>Liste des formations à venir</h3>
</br>
</br>
<display:table  name="sessions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
	<display:setProperty name="basic.msg.empty_list">Aucune session.</display:setProperty>
	<display:setProperty name="export.csv.filename">sessions.csv</display:setProperty>
	<display:column title="Nom" sortable="true">
		<s:url action="VenirDetail" includeParams="get" var="VenirDetailURL">
			<s:param name="sesId">${item.sesId}</s:param>
		</s:url>
		<a href="${VenirDetailURL}">${item.formationName}</a>				
	</display:column>
	<display:column  property="dateDebut" title="Début" sortable="true"/>
	<display:column  property="dateFin" title="Fin" sortable="true" />
	<display:column  property="duree" title="Durée(jours)" sortable="true" />
	<display:column  property="horaires" title="Horaires" sortable="true" />
	<display:column  property="niveau" title="Niveau" sortable="true" />
	<display:column  property="commentaire" title="Descriptif" sortable="true" />
	<display:column  property="statusUtilisateur" title="Etat" sortable="true" class="${item.statusUtilisateur == 'Ouvert' ? 'session-ouvert': 'session-ferme'}"/>
</display:table>

<%@include file="/jsp/include/footer.jsp"%>