<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>
	<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <!--  script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script -->
  <script>
  $( function() {
	  <!-- $( "#datepicker" ).datepicker({ dateFormat: 'dd/mm/yy' });
	  $( "#datepicker2" ).datepicker({ dateFormat: 'dd/mm/yy' }); -->
  } );
  </script>

</head>
<s:form>
	<div class="row">
		<div class="span1">
		</div>
		<s:div cssClass="span2 %{formation.nivCode == 'DEBUT' ? 'debutant': formation.nivCode == 'INTER' ?  'intermediaire' : 'expert'}">
			<s:select name="formation.nivCode" list="niveaux" label="" theme="xhtml_read" />
		</s:div>
		<div class="span6">
			<s:textfield name="formation.intitule" label="" cssClass="h3" theme="xhtml_read"/>
			</br>
			<s:textfield name="formation.commentaire" label="" theme="xhtml_read"/>
		</div>
	</div>
	<div class="row">
		<div class="span3">
		</div>
	</br>
	<s:select name="sessionTest.utiId" label="Formateur" list="utilisateurs" />
	</div>
	<div class="row">
		<div class="span3">
		</div>
		<div class="span2">
			<s:textfield name="sessionTest.dateDebut" label="Début" theme="xhtml_read" />
			<s:textfield name="sessionTest.duree" label="Durée (jours) " theme="xhtml_read"/>
			</div>
		<div class="span2">
	</div>
		<div class="span3">
			<s:textfield name="sessionTest.dateFin" label="Fin" theme="xhtml_read" />
			</br>
			<s:textfield name="sessionTest.horaire" label="Horaires" theme="xhtml_read"/>
		</div>	
	</div>
	<div class="row">
		<div class="span3">
		</div>
	<s:textfield name="sessionTest.nbPersonne" label="Nombre de personnes maximum" theme="xhtml_read"/>
</div>
</s:form>

<h5>Liste des personnes inscrites</h5>
<s:form >
	<display:table  name="inscriptions" class="tableau" uid="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:setProperty name="export.csv.filename">inscriptions.csv</display:setProperty>
	<display:column title="Nom" sortable="true">
		<s:select name="%{util.contextKey(#attr.item)}.utiId" list="utilisateurs" listKey="utiId" listValue="nom" theme="xhtml_read" />
	</display:column>
	<display:column title="Prénom" sortable="true">
		<s:select name="%{util.contextKey(#attr.item)}.utiId" list="utilisateurs" listKey="utiId" listValue="prenom" theme="xhtml_read" />
	</display:column>
	<display:column title="Présence" sortable="true">
		<s:checkbox name="%{util.contextKey(#attr.item)}.presence" theme="simple%{modeReadOnly ? '_read' : ''}"/>
	</display:column>
	<display:column title="Satisfaction" sortable="true">
		<s:url action="Satisfaction" includeParams="get" var="SatisfactionURL">
			<s:param name="sesId">${item.sesId}</s:param>
			<s:param name="utiId">${item.utiId}</s:param>
		</s:url> 
		<a href="${SatisfactionURL}">${item.satisfaction}</a>			
	</display:column>
	</display:table>

<s:if test="%{modeEdit}">
	<s:div cssClass="button-bar">
		<div class="right">
			<s:submit action="saveFormateurDetail" cssClass="enregistrer" value="ENREGISTRER" />
			<s:param name="sesId" value="sessionTest.sesId"/>
		</div>
	</s:div>
	</s:if>
</s:form>	

	
<%@include file="/jsp/include/footer.jsp"%>