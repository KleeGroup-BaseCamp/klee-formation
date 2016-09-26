<%@page import="kasper.ui.controller.*,com.kleegroup.formation.ui.controller.*,com.kleegroup.formation.ui.controller.tutorial2.*, KleeFormation.model.produit.*,   java.util.*"%>
<%@taglib uri="/WEB-INF/kasper_KleeFormation.tld" prefix="KleeFormation" %>
<style type="text/css" media="all">
	@import	"../static/css/tuto.css";
</style>
<kasper:page bodycss="tuto" controllerclass="com.kleegroup.formation.ui.controller.tutorial.TutoController" title="Tutoriel - Accueil">
<table>
<tr><%@include file="menu.inc" %>
<td>
<div>
Choisissez un composant dans la liste de gauche.<br/>
Vous trouverez : <br/>
- Description<br/>
- API<br/>
	- jsp<br/>
	- bean<br/>
- Usage<br/>
- Exemple<br/>
	- simple<br/>
	- complexe<br/>
</div>
</td></tr></table>

</kasper:page>
