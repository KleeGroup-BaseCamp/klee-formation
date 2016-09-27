<%@ taglib prefix="s" uri="/struts-tags"%>

<s:include value="/jsp/include/pageHeader.jsp">
	<s:param name="subtitle" value="param.subtitle" />
</s:include>
<div id="header">
	<s:a action="Accueil" id="logo">
		<img src="./static/images/logo2.jpg" alt="Image" />
	</s:a>
	
	<p style="line-height:65px;">
 	 <h1>L'application de gestions de formations</h1>
	</p>	
	 
</div>
<hr/>
<%@include file="/jsp/include/menu.jsp" %>
<div class="row-fluid">
	<div class="panel span12">
		<% boolean pageManagedMessages = Boolean.parseBoolean(request.getParameter("pageManagedMessages")); %>
		<% if (!pageManagedMessages) { %>
			<s:actionmessage/>
		<% } %>
			