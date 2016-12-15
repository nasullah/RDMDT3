
<%@ page import="rdmdt.RoleType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Role Type</center></h2>
</head>

<body>

<section id="show-roleType" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="roleType.roleTypeName.label" default="Role Type Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: roleTypeInstance, field: "roleTypeName")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
