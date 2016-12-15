
<%@ page import="rdmdt.RelationshipType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Relationship Type</center></h2>
</head>

<body>

<section id="index-relationshipType" class="first">

	<table class="table table-bordered margin-top-medium">
		<thead>
			<tr>
			
				<g:sortableColumn property="relationshipTypeName" title="${message(code: 'relationshipType.relationshipTypeName.label', default: 'Relationship Type Name')}" />
			
				<g:sortableColumn property="relationshipTypeInverse" title="${message(code: 'relationshipType.relationshipTypeInverse.label', default: 'Relationship Type Inverse')}" />
			
			</tr>
		</thead>
		<tbody>
		<g:each in="${relationshipTypeInstanceList}" status="i" var="relationshipTypeInstance">
			<tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
			
				<td><g:link action="show" id="${relationshipTypeInstance.id}">${fieldValue(bean: relationshipTypeInstance, field: "relationshipTypeName")}</g:link></td>
			
				<td>${fieldValue(bean: relationshipTypeInstance, field: "relationshipTypeInverse")}</td>
			
			</tr>
		</g:each>
		</tbody>
	</table>
	<div class="pagination">
		<g:paginate total="${relationshipTypeInstanceCount}" />
	</div>
</section>

</body>

</html>
