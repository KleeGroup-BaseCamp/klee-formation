<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>	

<h3>Liste de mes formations à venir</h3>
<display:table  name="inscriptions2" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
	<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:column title="Nom" sortable="true">
			<s:url action="MesDetail" includeParams="get" var="MesDetailURL">
				<s:param name="sesId">${item.sesId}</s:param>
			</s:url> 
			<a href="${MesDetailURL}">${item.sessionName}</a>				
		</display:column>
	<display:column  property="datedebut" title="Début" sortable="true"/>
	<display:column  property="datefin" title="Fin" sortable="true"/>
	<display:column  property="dureejour" title="Durée(jours)" sortable="true"/>
	<display:column  property="horaire" title="Horaires" sortable="true"/>
	<display:column  property="niveau" title="Niveau" sortable="true" />
	<display:column  property="commentaire" title="Descriptif" sortable="true" />	
</display:table>

<h4>Liste de mes formations </h4>
<display:table  name="inscriptions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:column title="Nom" sortable="true">
			<s:url action="MesDetail" includeParams="get" var="MesDetailURL">
				<s:param name="sesId">${item.sesId}</s:param>
			</s:url> 
			<a href="${MesDetailURL}">${item.sessionName}</a>			
		</display:column>
		<display:column  property="datedebut" title="Début" sortable="true"/>
		<display:column  property="datefin" title="Fin" sortable="true"/>
		<display:column title="Satisfaction" sortable="true">
			<s:url action="Satisfaction" includeParams="get" var="SatisfactionURL">
				<s:param name="sesId">${item.sesId}</s:param>
			</s:url> 
			<a href="${SatisfactionURL}">${item.satisfaction}</a>				
		</display:column>
</display:table>
	
<s:if test="%{isformateur()}">	
<h3>Liste de mes formations  à préparer</h3>
	<display:table  name="sessions2" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:column title="Nom" sortable="true">
			<s:url action="FormateurDetail" includeParams="get" var="FormateurDetailURL">
				<s:param name="sesId">${item.sesId}</s:param>
			</s:url> 
			<a href="${FormateurDetailURL}">${item.formationName}</a>			
		</display:column>
		<display:column  property="dateDebut" title="Début" sortable="true"/>
		<display:column  property="dateFin" title="Fin" sortable="true"/>
		<display:column  property="duree" title="Durée(jours)" sortable="true"/>
		<display:column  property="horaire" title="Horaires" sortable="true"/>
		<display:column  property="niveau" title="Niveau" sortable="true" />
		<display:column  property="commentaire" title="Descriptif" sortable="true" />		
	</display:table>
	
	<h4>Liste de mes formations préparer</h4>
	
	<display:table  name="sessions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:column title="Nom" sortable="true">
			<s:url action="FormateurDetail" includeParams="get" var="FormateurDetailURL">
				<s:param name="sesId">${item.sesId}</s:param>
			</s:url> 
			<a href="${FormateurDetailURL}">${item.formationName}</a>			
		</display:column>
		<display:column  property="dateDebut" title="Début" sortable="true"/>
		<display:column  property="dateFin" title="Fin" sortable="true"/>
		<display:column  property="satisfaction" title="Staisfaction générale" sortable="true"/> 
	</display:table>
</s:if>


<%@include file="/jsp/include/footer.jsp"%>

	
	