<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:include value="/jsp/include/header.jsp">
	<s:param name="subtitle">${pageName}</s:param>
</s:include>

<s:form>
	<!--   mode création / modification ----------------------------------------------------------------------------------- -->
	<s:div layout="table" cols="3">
		<s:textfield name="formation.intitule" label="default" />
		<tr><td colspan="2">&nbsp;</td></tr>
		<s:textarea name="formation.commentaire" label="default"/>
		<s:select name="formation.nivCode" label="default" list="niveaux"/>					
	</s:div>
		
	<div class="button-bar">
		<s:if test="%{modeReadOnly}">
			<s:hidden name="forId" value="%{formation.forId}" /> 
			<s:submit action="deleteFormationDetail" value="SUPPRIMER" cssClass="enregistrer" onclick='return confirmAction(this, "Etes vous sur de vouloir supprimer cette formation ?")' />
			<s:submit action="editFormationDetail" value="MODIFIER" cssClass="enregistrer" />
		</s:if>
		<s:elseif test="%{modeEdit}" >
			<s:a action="FormationDetail" cssClass="annuler">Annuler
			<s:param name="forId" value="formation.forId"/></s:a>
		</s:elseif>
		<div class="right">
			<s:if test="%{modeReadOnly}">
				<s:form>
			 		<div class="button-bar">
						<div class="right">
							<s:a action="SessionDetail" cssClass="creer">NOUVELLE SESSION
								<s:param name="forId" value="formation.forId"/>
							</s:a>
							<s:a action="SessionList" cssClass="rechercher"> SESSIONS</s:a>
						</div>
					</div>
				</s:form>
			</s:if>
			<s:else>
				<s:submit action="saveFormationDetail" value="ENREGISTRER" cssClass="enregistrer" />
			</s:else>
		</div> 	
	</div>
</s:form>

<s:include value="/jsp/include/footer.jsp"/>