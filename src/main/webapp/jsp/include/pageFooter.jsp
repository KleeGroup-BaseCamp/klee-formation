<%@ taglib prefix="s" uri="/struts-tags"%>
		<hr/>
		<div id="footer">			
			<s:a href="MentionsLegales">Mentions légales</s:a> |
			<s:a href="Contact">Contact</s:a> | 		
	</div><%-- div container --%>
	
	<s:include value="/jsp/include/modalePopin.jsp"/>
	<!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
	<script src="static/js/bootstrap.js"></script>
    <script src="static/js/kasper-ajax.js"></script>
    <script src="static/js/jquery.placeholder.js"></script>
    <script src="static/js/kasper-dhtml.js"></script>
    <script src="static/js/kasper-autocomplete.js"></script>    
    <script src="static/js/popin.js"></script>
    ${param.scripts}
</body>
</html>