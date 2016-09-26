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
	<!--   mode création / modification ----------------------------------------------------------------------------------- -->
	<s:div layout="table" cols="2">
	<s:textfield name="sessionTest.formationName" label="Nom"  disabled="disabled"/>
	<s:textfield name="sessionTest.niveau" label="Niveau"  disabled="disabled"/>
	<s:textfield name="sessionTest.commentaire" label="Commentaire"  disabled="disabled"/>
		<tr><td colspan="2">&nbsp;</td></tr>
	 <sj:datepicker name="sessionTest.dateDebut" label="Début" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" showOn="button"/>
	 <sj:datepicker name="sessionTest.dateFin" label="Fin" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" showOn="button"/>
	 <s:textfield name="sessionTest.duree" label="Durée(jours)"/>
	 <s:textfield name="sessionTest.horaire" label="Horaires"/>
	 <s:textfield name="sessionTest.formateur" label="Formateur"/>
	 <s:textfield name="sessionTest.nbPersonne" label="Nombre de personnes maximum"/>
	</s:div>	
</s:form>

	<h3>Liste des personnes inscritent</h3>
	 <display:table  name="inscriptions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:setProperty name="export.csv.filename">inscriptions.csv</display:setProperty>
		<%--<display:column  property="insId" title="Nom" sortable="true"/> --%>
		<display:column  property="nom" title="Nom" sortable="true"/>
		<display:column  property="prenom" title="Prénom" sortable="true"/>
	</display:table>

	<s:div cssClass="button-bar">
			<s:form >
				<s:submit action="deleteMesDetail" cssClass="enregistrer" value="Se désinscrire" />
				<s:param name="sesId" value="session.sesId"/>

			</s:form>	
	</s:div>
<%@include file="/jsp/include/footer.jsp"%>