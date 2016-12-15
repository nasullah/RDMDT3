
<%@ page import="rdmdt.RelationshipType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Relationship Type</center></h2>
</head>

<body>

<section id="show-relationshipType" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="relationshipType.relationshipTypeName.label" default="Relationship Type Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: relationshipTypeInstance, field: "relationshipTypeName")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="relationshipType.relationshipTypeInverse.label" default="Relationship Type Inverse" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: relationshipTypeInstance, field: "relationshipTypeInverse")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
