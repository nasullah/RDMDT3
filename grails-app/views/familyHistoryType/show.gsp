
<%@ page import="rdmdt.FamilyHistoryType" %>
<!DOCTYPE html>
<html>

<head>
	<meta name="layout" content="main" />
	<h2><center>Show Family History Type</center></h2>
</head>

<body>

<section id="show-familyHistoryType" class="first">

	<table class="table">
		<tbody>
		
			<tr class="prop">
				<td valign="top" class="name"><g:message code="familyHistoryType.familyHistoryTypeName.label" default="Family History Type Name" /></td>
				
				<td valign="top" class="value">${fieldValue(bean: familyHistoryTypeInstance, field: "familyHistoryTypeName")}</td>
				
			</tr>
		
		</tbody>
	</table>
</section>

</body>

</html>
