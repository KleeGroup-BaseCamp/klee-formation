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
	<s:div layout="table" cols="4">
		<s:textfield name="formation.intitule" label="Nom" theme="xhtml_read" />
		<s:select name="formation.nivCode" list="niveaux" label="Niveau" theme="xhtml_read"/>
		<s:textfield name="formation.commentaire" label="Descriptif" theme="xhtml_read"/>
	<tr><td colspan="2">&nbsp;</td></tr>
	</s:div>
	<s:div layout="table" cols="4">
		<s:textfield name="sessionTest.dateDebut" label="Début"/>
		<s:textfield name="sessionTest.dateFin" label="Fin"/>
		<s:textfield name="sessionTest.duree" label="Durée"/>
		<s:textfield name="sessionTest.horaire" label="Horaires"/>
		<s:select name="sessionTest.utiId" label="Formateur" list="utilisateurs" />
	</s:div>
	<s:div layout="table" cols="4">
		<s:textfield name="sessionTest.nbPersonne" label="Nombre de personnes maximum"/>
	</s:div>
</s:form>

<h3>Liste des personnes inscrites</h3>
	 <display:table  name="inscriptions" class="tableau" id="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:setProperty name="export.csv.filename">inscriptions.csv</display:setProperty>
		<%--<display:column  property="insId" title="Nom" sortable="true"/> --%>
		<display:column  property="nom" title="Nom" sortable="true"/>
		<display:column  property="prenom" title="Prénom" sortable="true"/>
	</display:table>
		
<s:form>
<div class="button-bar">
	<div class="right">
		<s:if test="%{isManager()}">
			<s:a action="Utilisateur" cssClass="creer">INSCRIPTION
				<s:param name="sesId" value="sessionTest.sesId"/>
			</s:a>
		</s:if>
		<s:else>
			<s:submit action="inscrireVenirDetail" cssClass="enregistrer" value="S'INSCRIRE" />
		</s:else>
	</div>
</div>
</s:form>
<s:include value="/jsp/include/footer.jsp"/>