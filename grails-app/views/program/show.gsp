
<%@ page import="rdmdt.Program" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Program</center></h2>
</head>

<body>

<section id="show-program" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="program.name.label" default="Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: programInstance, field: "name")}</td>
				
			</tr>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="program.description.label" default="Description" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: programInstance, field: "description")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
