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
	<s:if test="%{!modeCreate}">
	<s:textfield name="formation.intitule" label="Nom" theme="xhtml_read" />
	<s:select name="formation.nivCode" list="niveaux" label="Niveau" theme="xhtml_read"/>
	<s:textfield name="formation.commentaire" label="Descriptif" theme="xhtml_read"/>
	</s:if>
		<tr><td colspan="2">&nbsp;</td></tr>
	 <s:if test="%{modeCreate}" >
		<sj:autocompleter list="formations" listKey="forId" listValue="intitule"
				label="Nom" name="sessionTest.forId" headerKey="" headerValue=""
				href="ListAutocomplete.do" />	 
		<sj:autocompleter list="formatteurs" listKey="utiId" listValue="nomComplet"
				label="Formateur" name="sessionTest.utiId" headerKey="" headerValue=""
				href="ListAutocomplete.do" />
		</s:if>
		<s:if test="%{modeEdit}">
			<sj:autocompleter list="formatteurs" listKey="utiId" listValue="nomComplet"
				label="Formateur" name="sessionTest.utiId" headerKey="" headerValue=""
				href="ListAutocomplete.do" />
		</s:if>
		<s:if test="%{modeReadOnly}">
			<s:select name="sessionTest.utiId" label="Formateur" list="utilisateurs" />
		</s:if>
		<s:if test="%{modeCreate}">
			<display:table  name="horaires" class="tableau" id="item" export="false" requestURI="#" pagesize="20">
				<display:column  property="debut" title="Début"/>
				<display:column  property="fin" title="Fin"/>
			</display:table>
			</br>
			</br>
			<sj:datepicker name="horaire.debut" label="Début"  displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" showOn="button" timepicker="true"/>
		    <sj:datepicker name="horaire.fin" label="Fin" displayFormat="dd/mm/yy"  changeMonth="true" changeYear="true" showOn="button" timepicker="true"/>
		</s:if>
		<s:if test="%{!modeCreate}">
			<s:textfield name="sessionTest.dateDebut" label="Début" theme="xhtml_read" />
			<s:textfield name="sessionTest.dateFin" label="Fin" theme="xhtml_read" />
			<s:textfield name="sessionTest.horaire" label="Horaires" theme="xhtml_read" />
			</s:if>
</s:div>
		<s:if test="%{modeEdit}">
			<display:table  name="horaires" class="tableau" id="item" export="false" requestURI="#" pagesize="20">
				<display:column  property="debut" title="Début"/>
				<display:column  property="fin" title="Fin"/>
			</display:table>
			</br>
			</br>
			<s:div layout="table" cols="4">
			<sj:datepicker name="horaire.debut" label="Début"  displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" showOn="button" timepicker="true"/>
		    <sj:datepicker name="horaire.fin" label="Fin" displayFormat="dd/mm/yy"  changeMonth="true" changeYear="true" showOn="button" timepicker="true"/>
		    	</s:div>
		</s:if>
	
		<div class="button-bar">
			<s:if test="%{!modeReadOnly}">
				<div class="right">
		
					<s:submit action="addSessionDetail" value="+" cssClass="enregistrer" />
					
				</div>
				<s:submit action="removeSessionDetail" value="-" cssClass="enregistrer" />
			</s:if>
		</div>
		<s:div layout="table" cols="2">
		  <s:if test="%{modeReadOnly}">
			<s:textfield name="sessionTest.duree" label="Durée(jours)"/>
			</s:if>
			<s:textfield name="sessionTest.nbPersonne" label="Nombre de personnes maximum"/>
		</s:div>
		<s:if test="%{!modeCreate}">
		<h3>Liste des personnes inscrites</h3>
	<display:table  name="inscriptions" class="tableau" uid="item" export="true" sort="list" requestURI="#" pagesize="20">
		<display:setProperty name="basic.msg.empty_list">Aucune inscription.</display:setProperty>
		<display:setProperty name="export.csv.filename">inscriptions.csv</display:setProperty>
		<display:column  property="nom" title="Nom" sortable="true"/>
		<display:column  property="prenom" title="Prénom" sortable="true"/>
	</display:table>
	</s:if>
	<div class="button-bar">
		<s:elseif test="%{modeEdit}" >
			<s:a action="SessionDetail" cssClass="annuler">Annuler
			<s:param name="sesId" value="sessionTest.sesId"/></s:a>
		</s:elseif>
			<s:if test="%{modeReadOnly}">
			<s:submit action="deleteSessionDetail" value="SUPPRIMER" cssClass="enregistrer" onclick='return confirmAction(this, "Etes vous sur de vouloir supprimer cette session ?")' />
			<div class="right">
				<s:submit action="editSessionDetail" value="MODIFIER" cssClass="enregistrer" />
			</div>
			</s:if>
			<s:else>
			<div class="right">
				<s:submit action="saveSessionDetail" value="ENREGISTRER" cssClass="enregistrer" />
				<s:submit action="publishSessionDetail" value="PUBLIER" cssClass="enregistrer" />
			</div>

			</s:else>
		</div> 	
</s:form>

<s:include value="/jsp/include/footer.jsp"/>