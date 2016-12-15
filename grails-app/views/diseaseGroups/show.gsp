
<%@ page import="rdmdt.DiseaseGroups" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'diseaseGroups.label', default: 'DiseaseGroups')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-diseaseGroups" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-diseaseGroups" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list diseaseGroups">
			
				<g:if test="${diseaseGroupsInstance?.name}">
				<li class="fieldcontain">
					<span id="name-label" class="property-label"><g:message code="diseaseGroups.name.label" default="Name" /></span>
					
						<span class="property-value" aria-labelledby="name-label"><g:fieldValue bean="${diseaseGroupsInstance}" field="name"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${diseaseGroupsInstance?.originalId}">
				<li class="fieldcontain">
					<span id="originalId-label" class="property-label"><g:message code="diseaseGroups.originalId.label" default="Original Id" /></span>
					
						<span class="property-value" aria-labelledby="originalId-label"><g:fieldValue bean="${diseaseGroupsInstance}" field="originalId"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${diseaseGroupsInstance?.subGroups}">
				<li class="fieldcontain">
					<span id="subGroups-label" class="property-label"><g:message code="diseaseGroups.subGroups.label" default="Sub Groups" /></span>
					
						<g:each in="${diseaseGroupsInstance.subGroups}" var="s">
						<span class="property-value" aria-labelledby="subGroups-label"><g:link controller="subGroups" action="show" id="${s.id}">${s?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:diseaseGroupsInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${diseaseGroupsInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
