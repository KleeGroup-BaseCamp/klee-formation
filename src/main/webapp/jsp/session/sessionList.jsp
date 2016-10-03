<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>

<s:form>
<h4>Rechercher une session</h4>
	<s:div layout="table" cols="2">
		<s:textfield name="critere.intitule" label="Nom de la session"/>
	</s:div>
			<div class="button-bar">
		<div class="right">
			<s:submit action="rechercherSessionList" value="RECHERCHER" cssClass="rechercher" />
		</div>
	</div>
	 <div class="button-bar">
		<div class="right">
		</div>
	</div>
</s:form>

<h3>Liste des sessions</h3>
<display:table  name="sessions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20" defaultsort="3">
		<display:setProperty name="basic.msg.empty_list">Aucune session.</display:setProperty>
		<display:setProperty name="export.csv.filename">sessions.csv</display:setProperty>
		<display:column title="Numéro" sortable="true" >
		<s:url action="SessionDetail" includeParams="get" var="SessionDetailURL">
			<s:param name="sesId">${item.sesId}</s:param>
		</s:url>
		<a href="${SessionDetailURL}">${item.sesId}</a>			
			
		</display:column>
		<display:column  property="formationName" title="Nom" sortable="true"/>
		<display:column  property="dateDebut" title="Début" sortable="true" defaultorder="descending"/>
		<display:column  property="dateFin" title="Fin" sortable="true" />
		<display:column  property="niveau" title="${util.label('sessions.niveau')}" sortable="true"  />
		<display:column  property="commentaire" title= "Descriptif" sortable="true" />
		<display:column  property="status" title="Etat" sortable="true" />
	</display:table>

<s:form>
			 		<div class="button-bar">
						<div class="right">
							<s:a action="SessionDetail" cssClass="creer">NOUVELLE SESSION
						</s:a>
						</div>
					</div>
				</s:form>
	
<%@include file="/jsp/include/footer.jsp"%>