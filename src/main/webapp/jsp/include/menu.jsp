<%@ taglib prefix="s" uri="/struts-tags"%>

<div id="menu">
	<%--   classe="list-group"
	<span class="ui-menuitem-text">Mes DA en cours</span>--%>
	
	<ul>
	<li><a class="list-group-item" href="./MesList.do" style="text-decoration:none"><i class="fa fa-refresh"></i> 
	Mes formations</a></li>
	<li><a href="./VenirList.do" style="text-decoration:none"><i class="fa fa-calendar-o"></i>
 Formations à venir</a></li>

	<li><a href="./FormationsList.do" style="text-decoration:none"><i class="fa fa-folder-open"></i> Suivi RH</a></li>
	<li><a href="./SessionList.do" style="text-decoration:none"><i class="fa fa-pencil-square-o"></i> Gestions</a></li>
	<li><a href="./CatalogueList.do" style="text-decoration:none"><i class="fa fa-list" aria-hidden="true"></i> Catalogue</a></li>
	
	
	<li><a href="./UtilisateurList.do" style="text-decoration:none"><i class="fa fa-cog"></i> Administration</a></li>
	</ul>

	
	
	<%-- 
	./Formation_à_traité.do
	
	./Formation_administration.do
	
	<ul>	
	<s:iterator value="menuItems" var="menu">
		<s:if test="#menu.viewMenu()">
		<li>
		<span class="menu">
			<s:if test="#menu.isAddressEmpty()">
				<s:property value="#menu.getLabel()"/>
			</s:if> mes DA 
			<s:else>
				<s:set var="address" value="#menu.getAddress()"/>
				<s:a href="%{address}"><s:property value="#menu.getLabel()"/></s:a>
			</s:else>
		</span>
		
		<s:if test="#menu.hasChildren()">
			<ul class="subMenu">
			<s:iterator var="subMenu" value="#menu.getSubItems()">
				<s:if test="#subMenu.isAddressEmpty()==false">
					<li class="nowrap">
					<s:set var="address" value="#subMenu.getAddress()"/>
					<s:a href="%{address}"><s:property value="#subMenu.getLabel()"/></s:a>
					</li>
				</s:if>
			</s:iterator>
			</ul>
		</s:if>
		</li>
		</s:if> 
	</s:iterator>
	</ul>
	--%>
	
</div><!-- Fin menu principal -->

