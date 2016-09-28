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
	<s:div layout="table" cols="4">
		<s:if test="%{!modeReadOnly}">
			<s:textfield name="utilisateurLogin.login" label="default"/>
			<s:if test="%{modeEdit}">
				<s:textfield name="utilisateurLogin.password" label="Ancien mot de passe"/>
			</s:if>
			<s:textfield name="utilisateurLogin.newPassword" label="default"/>
			<s:textfield name="utilisateurLogin.newPasswordCheck" label="default"/>
		</s:if>
		<tr><td colspan="4">&nbsp;</td></tr>
		<s:textfield name="utilisateur.nom" label="default"/>
		<s:textfield name="utilisateur.prenom" label="default"/>
		<s:textfield name="utilisateur.mail" label="default"/>
		<s:if test="%{!modeReadOnly}">
		<tr>
		<s:checkbox name="roleAdmin" label="Administrateur"/>
		<tr><td></td></tr>
		<s:checkbox name="roleFormatteur" label="Formateur"/>
		<s:checkbox name="roleResponssable" label="Responsable"/>
		</tr>
		</s:if>
		<s:if test="%{modeReadOnly}">
		<tr>
		<s:checkbox name="utilisateur.admin" label="Administrateur"/>
		<tr><td></td></tr>
		<s:checkbox name="utilisateur.formateur" label="Formateur"/>
		<s:checkbox name="utilisateur.responsable" label="Responsable"/>
		</tr>
		</s:if>
	</s:div>
	
<h3>Liste des formations</h3>
<display:table  name="inscriptions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
	<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
	<display:column title="Nom" sortable="true">
		<s:url action="MesDetail" includeParams="get" var="MesDetailURL">
			<s:param name="sesId">${item.sesId}</s:param>
		</s:url> 
		<a href="${MesDetailURL}">${item.formationName}</a>				
	</display:column>
	<display:column  property="dateDebut" title="date de début" sortable="true"/>
	<display:column  property="dateFin" title="date de fin" sortable="true"/>
	<display:column  property="presence" title="Présence" sortable="true"/>
	<display:column title="Satisfaction" sortable="true">
		<s:url action="Satisfaction" includeParams="get" var="SatisfactionURL">
			<s:param name="sesId">${item.sesId}</s:param>
		</s:url> 
		<a href="${SatisfactionURL}">${item.satisfaction}</a>				
	</display:column>
</display:table>
	
	
		
	<div class="button-bar">
		<s:if test="%{modeReadOnly}">
			<s:hidden name="utiId" value="%{utilisateur.utiId}" />
			<s:submit action="deleteUtilisateurDetail" value="" cssClass="supprimer" onclick='return confirmAction(this, "Etes vous sur de vouloir supprimer ce compte utilisateur ?")' />
		</s:if>
		<s:elseif test="%{modeEdit}" >
			<s:a action="UtilisateurDetail" cssClass="annuler">Annuler
			<s:param name="utiId" value="utilisateur.utiId"/></s:a>
		</s:elseif>
		<div class="right">
			<s:if test="%{modeReadOnly}">
				<s:submit action="editUtilisateurDetail" value="MODIFIER" cssClass="modifier" />
			</s:if>
			<s:else>
				<s:submit action="saveUtilisateurDetail" value="ENREGISTRER" cssClass="enregistrer" />
			</s:else>
		</div> 
		
	</div>
</s:form>

<s:include value="/jsp/include/footer.jsp"/>