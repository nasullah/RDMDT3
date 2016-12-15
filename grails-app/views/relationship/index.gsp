
<%@ page import="rdmdt.Relationship" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'relationship.label', default: 'Relationship')}" />
	<title><g:message code="default.index.label" args="[entityName]" /></title>
</head>

<body>

<section id="index-relationship" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<th><g:message code="relationship.relationshipType.label" default="Relationship Type" /></th>
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${relationshipInstanceList}" status="i" var="relationshipInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${relationshipInstance.id}">${fieldValue(bean: relationshipInstance, field: "relationshipType")}</g:link></td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${relationshipInstanceCount}" />
	</div>
</section>

</body>

</html>
