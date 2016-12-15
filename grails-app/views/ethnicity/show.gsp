
<%@ page import="rdmdt.Ethnicity" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Ethnicity</center></h2>
</head>

<body>

<section id="show-ethnicity" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="ethnicity.ethnicityName.label" default="Ethnicity Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: ethnicityInstance, field: "ethnicityName")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
