<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="v" uri="/vertigo-tags"%>
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
	<%-- <v:div layout="table" cols="4">--%>
	
	<s:if test="%{!modeCreate}">
	<div class="row">
	<div class="span1">
	</div>
	<v:div cssClass="span2 %{formation.nivCode == 'DEBUT' ? 'debutant': formation.nivCode == 'INTER' ?  'intermediaire' : 'expert'}">
		<s:select name="formation.nivCode" list="niveaux" label="" theme="xhtml_read" />
	</v:div>
	<div class="span6">
	<s:textfield name="formation.intitule" label="" cssClass="h3" theme="xhtml_read"/>
	</br>
	<s:textfield name="formation.commentaire" label="" theme="xhtml_read"/>
	</div>
</div>
	</s:if>
	<v:div layout="table" cols="4">
		<tr><td colspan="2">&nbsp;</td></tr>
	 <s:if test="%{modeCreate}" >
		<sj:autocompleter list="formations" listKey="forId"  listValue="intitule"
				label="Nom de la formation" name="sessionTest.forId" headerKey="" headerValue=""
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
		</v:div>
		<s:if test="%{modeReadOnly}">
		<div class="row">
		<div class="span3">
		</div>
		<s:select name="sessionTest.utiId" label="Formateur" list="utilisateurs" />
	</div>
		</s:if>
		 <v:div layout="table" cols="4">
		  <s:textfield name="sessionTest.lieux" label="Lieu"/>
			<s:textfield name="sessionTest.nbPersonne" label="Nombre de personnes maximum"/>
			
			<s:if test="%{modeReadOnly}">
			<s:textfield name="sessionTest.duree" label="Durée(jours)"/>
			</s:if>
		</v:div>
			<v:div layout="table" cols="4">		
			<sj:datepicker name="sessionTest.dateDebut" label="Début" displayFormat="dd/mm/yy" changeMonth="true" changeYear="true" showOn="button"/>
		    <sj:datepicker name="sessionTest.dateFin" label="Fin" displayFormat="dd/mm/yy"  changeMonth="true" changeYear="true" showOn="button"/>
		  </v:div>
		 
		  <div class="row">
				<div class="span10">
				</div>
			<s:if test="%{modeCreate}">
			</br>
			    <s:submit action="HoraireSessionDetail" value="Horaires" cssClass="add" />
			    </br>
			</s:if>
			<s:if test="%{modeEdit}">
			</br>
			    <s:submit action="DatemodifSessionDetail" value="modifier les dates" cssClass="add" />
			 </br>
			</s:if>
			</div>
			 </br>
			<display:table  name="horaires" class="tableau" id="item" export="false" requestURI="#" defaultsort="1" defaultorder="ascending">
				<display:setProperty name="basic.msg.empty_list" value=""/>
				<display:setProperty name="paging.banner.all_items_found" value=""/>
				<display:column property="jour" title="Jour" sortable="true"/>
				<display:column title="Début" sortable="true">
					<s:textfield name="%{util.contextKey(#attr.item)}.debut"/>		
				</display:column>
				<display:column title="Fin" sortable="true">
					<s:textfield name="%{util.contextKey(#attr.item)}.fin"/>
				 	<%--<sj:datepicker name="%{util.contextKey(#attr.item)}.fin" timepickerFormat="HH:mm" timepicker="true" timepickerOnly="true"/>--%>	
				</display:column>
				<display:column title="Début" sortable="true">
					<s:textfield name="%{util.contextKey(#attr.item)}.debutAprem"/>		
				</display:column>
				<display:column title="Fin" sortable="true">
					<s:textfield name="%{util.contextKey(#attr.item)}.finAprem"/>		
				</display:column>
			</display:table>
		
		<s:if test="%{!modeCreate}">
		<h5>Liste des personnes inscrites</h5>
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
			
			<div class="right">
			<s:submit action="deleteSessionDetail" value="SUPPRIMER" cssClass="supprimer" onclick='return confirmAction(this, "Etes vous sur de vouloir supprimer cette session ?")' />
				<s:submit action="editSessionDetail" value="MODIFIER" cssClass="enregistrer" />
			</div>
			</s:if>
			<s:else>
			<div class="right">
				<s:submit action="saveSessionDetail" value="ENREGISTRER" cssClass="supprimer" />
				<s:submit action="publishSessionDetail" value="PUBLIER" cssClass="enregistrer" />
			</div>

			</s:else>
		</div> 	
</s:form>

<s:include value="/jsp/include/footer.jsp"/>