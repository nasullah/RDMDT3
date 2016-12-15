
<%@ page import="rdmdt.Relationship" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<g:set var="entityName" value="${message(code: 'relationship.label', default: 'Relationship')}" />
	<title><g:message code="default.show.label" args="[entityName]" /></title>
</head>

<body>

<section id="show-relationship" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="relationship.relationshipType.label" default="Relationship Type" /></td>
				
				<td valign="top" class="value"><g:link controller="relationshipType" action="show" id="${relationshipInstance?.relationshipType?.id}">${relationshipInstance?.relationshipType?.encodeAsHTML()}</g:link></td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="relationship.relatedFrom.label" default="Related From" /></td>
				
				<td valign="top" style="text-align: left;" class="value">
					<ul>
					<g:each in="${relationshipInstance.relatedFrom}" var="r">
						<li><g:link controller="patient" action="show" id="${r.id}">${r?.encodeAsHTML()}</g:link></li>
					</g:each>
					</ul>
				</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
