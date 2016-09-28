<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>

<s:form>
	<!--   mode création / modification ----------------------------------------------------------------------------------- -->
	<s:div layout="table" cols="3">
		<s:textfield name="formation.intitule" label="Intitulé" />
		<tr><td colspan="2">&nbsp;</td></tr>
		<s:select name="formation.nivCode" label="default" list="niveaux"/>	
		<s:textarea name="formation.commentaire" label="Descriptif"/>
	</s:div>
		
	<div class="button-bar">
		<s:if test="%{modeReadOnly}">
			 <s:hidden name="forId" value="%{formation.forId}" /> 
			<s:submit action="deleteFormationDetail" value="SUPPRIMER" cssClass="enregistrer" onclick='return confirmAction(this, "Etes vous sur de vouloir supprimer cette formation ?")' />
			<div class="right">
			<s:submit action="editFormationDetail" value="MODIFIER" cssClass="enregistrer" />
			</div>
		</s:if>
		<s:elseif test="%{modeEdit}" >
			<s:a action="FormationDetail" cssClass="annuler">Annuler
			<s:param name="forId" value="formation.forId"/></s:a>
		</s:elseif>
		<div class="right">
			<s:submit action="saveFormationDetail" value="ENREGISTRER" cssClass="enregistrer" />
		</div> 	
	</div>
</s:form>
<h3>Liste des sessions de la formation</h3>
	<display:table  name="sessions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune session.</display:setProperty>
		<display:column title="Nom" sortable="true">
			<s:url action="SessionDetail" includeParams="get" var="SessionDetailURL">
				<s:param name="sesId">${item.sesId}</s:param>
			</s:url>
			<a href="${SessionDetailURL}">${item.formationName}</a>			
		</display:column>
		<display:column  property="dateDebut" title="Début" sortable="true"/>
		<display:column  property="dateFin" title="Fin" sortable="true" />
		<display:column  property="duree" title="Durée(jours)" sortable="true" />
		<display:column  property="horaires" title="Horaires" sortable="true" />
		<display:column  property="niveau" title="Niveau" sortable="true" />
		<display:column  property="commentaire" title="Descriptif" sortable="true" />
		<display:column  property="status" title="Etat" sortable="true" /> 
	</display:table>
	
<s:include value="/jsp/include/footer.jsp"/>