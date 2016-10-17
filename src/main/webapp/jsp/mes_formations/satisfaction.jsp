<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>	

<s:form>

<h3>Evaluation de la satisfaction </h3>

	<s:div layout="table">
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="La durée du stage( temps) est-elle suffisante?" name="statistique.duree"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="le thème est-il adapté?" name="statistique.theme"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="contenu de thème est-il suffisant?" name="statistique.contenu"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="la progression est-elle adaptée?" name="statistique.progression"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="le stage a-t-il répondu à vos attentes?" name="statistique.attentes"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="Bénéfice retiré de cette formation?" name="statistique.benefices"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="faut-il approfondir cette formation?" name="statistique.approfondir"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="contact satisfaisant?" name="statistique.contact"
						listKey="key" listValue="value" />
		<s:radio list="#{'1':'1', '2':'2', '3':'3', '4':'4', '5':'5'}" label="explications claires et compréhensibles?" name="statistique.explication"
						listKey="key" listValue="value" />
	</s:div>
	
<s:if test="%{!modeReadOnly}">
	<s:div cssClass="button-bar">
		<div class="right">
			<s:submit action="saveSatisfaction" cssClass="enregistrer" value="ENREGISTRER" />
		</div>
	</s:div>
	</s:if>
</s:form>	

	
<%@include file="/jsp/include/footer.jsp"%>