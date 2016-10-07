<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>

<h5>Rechercher une Formation</h5>
<s:form>
	<s:textfield name="formationCritere.intitule" label="Nom de la formation"/>
	<s:submit action="rechercherFormationList" value="RECHERCHER" cssClass="rechercher" />	
</s:form>
<h4>Liste des Formations</h4>
<display:table  name="formations" class="tableau" uid="item" export="true" sort="list" requestURI="#" pagesize="20">
	<display:setProperty name="basic.msg.empty_list">Aucune formation.</display:setProperty>
	<display:setProperty name="export.csv.filename">formations.csv</display:setProperty> 
	<display:column title="Intitulé" sortable="true">
		<s:url action="FormationDetail" includeParams="get" var="formationDetailURL">
			<s:param name="forId">${item.forId}</s:param>
		</s:url>
		<a href="${formationDetailURL}">${item.intitule}</a>				
	</display:column>
	<display:column  property="commentaire" title="Descriptif" sortable="true"/>
	<display:column title="${util.label('formations.nivCode')}" sortable="true" sortProperty="nivCode">
		<s:select list="niveaux" name="%{util.contextKey(#attr.item)}.nivCode" theme="simple_read" />
	</display:column>
</display:table>
	
<div class="button-bar">
	<div class="right">
		<s:a action="FormationDetail" cssClass="creer">NOUVELLE FORMATION</s:a>
	</div>
</div>
<%@include file="/jsp/include/footer.jsp"%>