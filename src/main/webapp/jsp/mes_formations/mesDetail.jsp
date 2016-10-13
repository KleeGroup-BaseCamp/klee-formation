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
	<s:select name="sessionTest.utiId" label="Formateur" list="utilisateurs" theme="xhtml_read"/>
	</div>
	<div class="row">
		<div class="span3">
		</div>
		<div class="span3">
		<s:textfield name="sessionTest.nbPersonne" label="Nombre de personnes maximum" theme="xhtml_read"/>
			<s:textfield name="sessionTest.dateDebut" label="Début" theme="xhtml_read" />
				</div>	
			<div class="span1">
		</div>
		<div class="span2">
			<s:textfield name="sessionTest.duree" label="Durée(jours) " theme="xhtml_read"/>
			</br>
			<s:textfield name="sessionTest.dateFin" label="Fin" theme="xhtml_read" />
			
			</div>
						
	</div>		
		</br>
			<display:table  name="horaires" class="tableau" id="item" export="false" requestURI="#" defaultsort="0">
			 <display:setProperty name="basic.msg.empty_list" value=""/>
				<display:setProperty name="paging.banner.all_items_found" value=""/>
			<display:column property="jour" title="Jour" sortable="true"/>
			<display:column title="Début" sortable="true">
			<s:textfield name="%{util.contextKey(#attr.item)}.debut"/>		
			</display:column>
			<display:column title="Fin" sortable="true">
			<s:textfield name="%{util.contextKey(#attr.item)}.fin"/>		
			</display:column>
			<display:column title="Début" sortable="true">
			<s:textfield name="%{util.contextKey(#attr.item)}.debutAprem"/>		
			</display:column>
			<display:column title="Fin" sortable="true">
			<s:textfield name="%{util.contextKey(#attr.item)}.finAprem"/>		
			</display:column>
			</display:table>
			</br>
			</br>

</s:form>

	<h5>Liste des personnes inscritent</h5>
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