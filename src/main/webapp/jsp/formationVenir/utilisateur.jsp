<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>


<s:form >
	<div class="button-bar">
		<div>
			<s:submit action="inscrireMoiUtilisateur" cssClass="enregistrer" value="MOI" />
		</div>
	<div class="right">
		<sj:autocompleter list="utilisateurs" listKey="utiId" listValue="nomComplet"
			label="Utilisateur" name="inscription.utiId" headerKey="" headerValue=""
			href="ListAutocomplete.do" />
	</div>
	</br>
	<s:submit action="inscrireUtilisateur" cssClass="enregistrer" value="OK" />
	</div>
</s:form>
<s:include value="/jsp/include/footer.jsp" />

	
	