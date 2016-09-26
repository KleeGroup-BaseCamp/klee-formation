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
		<tr><td colspan="2">&nbsp;</td></tr>
		    <sj:datepicker name="sessionTest.datedebut" label="date de début" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" showOn="button"/>
			<sj:datepicker name="sessionTest.datefin" label="date de début" changeMonth="true" changeYear="true" displayFormat="dd/mm/yy" showOn="button"/>
			<s:textfield name="sessionTest.horairedebut" label="horaire de début "/>
			<s:textfield name="sessionTest.horairefin" label="horaire de fin"/>
			<s:textfield name="sessionTest.formateur" label="formateur"/>
			<s:textfield name="sessionTest.nbpersonne" label="nombre de personne maximum"/>
	</s:div>	
</s:form>


	<h3>Liste des personnes inscritent</h3>
	 <display:table  name="inscriptions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:setProperty name="export.csv.filename">inscriptions.csv</display:setProperty>
			<display:column  property="nom" title="Nom" sortable="true"/>
		<display:column  property="prenom" title="Prénom" sortable="true"/>
	</display:table>
	 
	 <div class="button-bar">
		<div class="right">			
			<s:a action="Utilisateur" cssClass="annuler">Inscription
			<s:param name="sesId" value="sessionTest.sesId"/></s:a>
		</div>
	</div>
<s:include value="/jsp/include/footer.jsp"/>